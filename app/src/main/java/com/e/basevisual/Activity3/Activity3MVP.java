package com.e.basevisual.Activity3;

import android.view.View;

import com.e.basevisual.Objects.Pregunta;
import com.e.basevisual.Objects.Usuari;

import java.util.List;

public interface Activity3MVP {
    interface view{
        void mostrar_error();
        void mostrar_pregunta(List<Pregunta> preguntes,String pregunta,String opcio1,String opcio2,String opcio3,String opcio4,int resposta,Usuari usuari,int num_foto,int num_preg,float puntuacio);
       // void mostrar_resultat(List<Usuari> classificacio,float ResultatAcR,Usuari usuari);
        void navegar4Act(Usuari usuari);

    }
    interface presenter{
     void setView(Activity3MVP.view view);
     void obtenir_preguntes(Usuari usuari);
     void obtenir_resultat(int num_pre,float puntuacio,Usuari usuari,List<Pregunta> preguntes,float puntuacioAc);
     void preparar_pregunta(int num_pre, float puntuacioAcR, Usuari usuari, List<Pregunta> preguntes);


    }
    interface model{
        void obtenir_preguntes(Activity3Model.detallsCallback callback, Usuari usuari);

        /*interface OnfinishListener{
            void onFinished(List<Pregunta> preguntes,Usuari usuari);
            void onFailure(Throwable t);
        }*/
        void guardar_resultats(Activity3Model.detallsCallback2 callback,float resultat,Usuari usuari);
       /* interface OnfinishListener2{
            void onFinished(float puntuacio_final,Usuari usuari);
            void onFailure(Throwable t);
        }*/

    }
}
