package com.e.basevisual.Activity2;

import android.graphics.Bitmap;

import com.e.basevisual.Objects.Usuari;

import androidx.annotation.Nullable;

public class Activity2Presenter implements Activity2MVP.presenter,Activity2MVP.model.OnFinishListener {
    @Nullable
    private Activity2MVP.view view;
    private Activity2MVP.model model;
    String ADN;




    //creem un costructor presenter passant per paràmetre el model, de manera que Dagger ja injecta el model al injectar el presenter
    public Activity2Presenter(Activity2MVP.model model){
        this.model=model;
    }

    @Override
    public void attachView(int numTest, int numIm, Usuari usuari) {
        view.onAttach(numTest,numIm,usuari);
    }

    @Override
    public void setView(Activity2MVP.view view) {
        this.view=view;
    }

    @Override
    public void conta_enrere(int num_foto,int num_test,Usuari usuari) {
        final int num_fotoB=num_foto+1;

        //final int num_fotoB=4;
        android.os.Handler handler=new android.os.Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (num_fotoB < 4) {
                    començar_descarrega(num_fotoB, num_test, usuari);
                    view.mostrarProgressBar();
                } else {
                    view.navegar_3Act(usuari, num_test);
                }
            }
        },12000);
    }

    @Override
    public void començar_descarrega(int num_foto, int num_test,Usuari usuari) {
        model.iniciar_descarrega(this,num_test,num_foto,usuari);
    }


    @Override
    public void obtenir_num_test(Usuari usuari) {
        int num;
        if(usuari.getADN().equals(null)){
          usuari.setADN("0000000000");
            }
        ADN=usuari.getADN();
        int resultat=ADN.indexOf("0");
        if(resultat==-1){
            ADN = "1000000000";
            num=0;
        }else{
            do{ num=(int)(Math.random()*10);
                }while(ADN.startsWith("1",(num)));
            char[] chars3 = ADN.toCharArray();
            chars3[num] = '1';
            ADN=new String(chars3);
        }
        usuari.setADN(ADN);
        int numTest=num+1;
        model.iniciar_descarrega(this,numTest,1,usuari);
        view.mostrarProgressBar();
    }

    @Override
    public void onFinished(Bitmap resource,int num_foto,int num_test,Usuari usuari) {
        view.mostrarFoto(resource,num_foto,num_test,usuari);
    }

    @Override
    public void onFailure(String t) {
        view.mostrarError();

    }
}
