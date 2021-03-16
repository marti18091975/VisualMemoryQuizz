package com.e.basevisual.Activity2;

import android.graphics.Bitmap;
import android.view.View;

import com.e.basevisual.Objects.Usuari;

import okhttp3.ResponseBody;

public interface Activity2MVP {
    interface view{
        void onAttach(int numTest,int numIm,Usuari usuari);

      void mostrarProgressBar();
      void mostrarFoto(Bitmap bitmap,int numFoto,int num_test,Usuari usuari);
      void navegar_3Act(Usuari usuari,int num_test);
      void mostrarError();


    }
    interface presenter{
        void attachView(int numTest,int numIm,Usuari usuari);
        void setView(Activity2MVP.view view);
        void conta_enrere(int num_foto,int num_test,Usuari usuari);
        void començar_descarrega(int num_foto,int num_test,Usuari usuari);

        void obtenir_num_test(Usuari usuari);


    }
    interface model{
        void iniciar_descarrega(OnFinishListener onFinishListener,int numTest,int numIm,Usuari usuari);
        interface OnFinishListener{
            void onFinished(Bitmap resource,int num_foto,int num_test,Usuari usuari);
            void onFailure(String t);
        }

    }

}
