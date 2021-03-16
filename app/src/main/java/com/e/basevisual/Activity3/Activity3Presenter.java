package com.e.basevisual.Activity3;

import com.e.basevisual.Objects.Pregunta;
import com.e.basevisual.Objects.Usuari;

import java.util.List;
import java.util.Locale;

import androidx.annotation.Nullable;

public class Activity3Presenter implements Activity3MVP.presenter {
    int puntuacioAc=0;
    List<Pregunta> preguntes;

    @Nullable
    private Activity3MVP.view view;
    private Activity3MVP.model model;
    public Activity3Presenter(Activity3MVP.model model){
        this.model=model;
    }
    @Override
    public void setView(Activity3MVP.view view) {
        this.view=view;
    }

    @Override
    public void obtenir_preguntes(Usuari usuari) {
        model.obtenir_preguntes(new Activity3Model.detallsCallback() {
            @Override
            public void onSuccess(List<Pregunta> preguntes) {
                preparar_pregunta(0,0,usuari,preguntes);
            }

            @Override
            public void onError() {

            }
        }, usuari);
    }

    @Override
    public void obtenir_resultat(int num_pre, float puntuacio, Usuari usuari, List<Pregunta> preguntes, float puntuacioAcR) {
        puntuacioAcR += puntuacio;
        if(num_pre>7){
            int num_part=usuari.getNumero_partides();
            num_part++;
            float punt_mitj=((usuari.getPuntuacio_mitjana()*usuari.getNumero_partides())+puntuacioAcR)/num_part;
            usuari.setNumero_partides(num_part);
            usuari.setPuntuacio_mitjana(punt_mitj);
            usuari.setPuntuacio(puntuacioAcR);
            model.guardar_resultats(new Activity3Model.detallsCallback2() {
                @Override
                public void onSuccess(Usuari usuari,float resultat) {
                    usuari.setPuntuacio(resultat);
                    view.navegar4Act(usuari);
                }

                @Override
                public void onError() {

                }
            }, puntuacioAcR, usuari);

      }else{
      num_pre++;

      preparar_pregunta(num_pre,puntuacioAcR,usuari,preguntes);}
    }

    @Override
    public void preparar_pregunta(int num_pre, float puntuacioAcR, Usuari usuari, List<Pregunta> preguntes){
        switch ((Locale.getDefault().getLanguage())){
            case ("ca"):
                view.mostrar_pregunta(preguntes,preguntes.get(num_pre).getPregunta_cat(),preguntes.get(num_pre).getResposta1_cat(),preguntes.get(num_pre).getResposta2_cat(),
                        preguntes.get(num_pre).getResposta3_cat(),preguntes.get(num_pre).getResposta4_cat(),preguntes.get(num_pre).getResposta(),usuari,preguntes.get(num_pre).getNum_foto(),num_pre,puntuacioAc);
                break;
            case ("es"):
                view.mostrar_pregunta(preguntes,preguntes.get(num_pre).getPregunta_esp(),preguntes.get(num_pre).getResposta1_esp(),preguntes.get(num_pre).getResposta2_esp(),
                        preguntes.get(num_pre).getResposta3_esp(),preguntes.get(num_pre).getResposta4_esp(),preguntes.get(num_pre).getResposta(),usuari,preguntes.get(num_pre).getNum_foto(),num_pre,puntuacioAc);
                break;
            default:
                view.mostrar_pregunta(preguntes,preguntes.get(num_pre).getPregunta_ing(),preguntes.get(num_pre).getResposta1_ing(),preguntes.get(num_pre).getResposta2_ing(),
                        preguntes.get(num_pre).getResposta3_ing(),preguntes.get(num_pre).getResposta4_ing(),preguntes.get(num_pre).getResposta(),usuari,preguntes.get(num_pre).getNum_foto(),num_pre,puntuacioAc);


        }
    }


    /*
    @Override
    public void onFinished(List<Pregunta> preguntes, Usuari usuari) {
        preparar_pregunta(0,0,usuari,preguntes);



    }

    @Override
    public void onFinished(float puntuacio_final, Usuari usuari) {
        view.navegar4Act(puntuacio_final,usuari);


    }

    @Override
    public void onFailure(Throwable t) {
        view.mostrar_error();
    }*/
}
