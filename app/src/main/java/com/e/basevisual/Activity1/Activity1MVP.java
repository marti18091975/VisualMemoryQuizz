package com.e.basevisual.Activity1;

import com.e.basevisual.Objects.Usuari;
import com.e.basevisual.utils.SharedPrefsUtil;

import org.reactivestreams.Subscription;

import java.util.List;

import io.reactivex.Observable;

public interface Activity1MVP {
     interface view{


         void mostrar_tv2(List<Usuari> usuaris, String nick);
         void mostrar_missatge_ok(String nick);
         void mostrar_missatge_nick_usat();
         void missatge_error(Throwable t);
         void navegar_2Act(int id,String nick,String ADN,int num_part,float punt_mitj);
         void navegar_fragment_info();




    }
    public interface presenter{
        void setView(Activity1MVP.view view);
        void comprovar_registre(SharedPrefsUtil sharedPrefsUtil);
        void obtenir_Usuari_dades(String nick);
        void crear_nou_usuari(String nick);


        void onDetachView();
        void registrar_a_Model(SharedPrefsUtil sharedPrefsUtil,String nick);


       // void retorn_usuari(Usuari usuari);
        //void onDestroy();




    }
    public interface model{
         Boolean fer_comprovacio_preferences(SharedPrefsUtil sharedPrefsUtil);
         String get_nick_preferences(SharedPrefsUtil sharedPrefsUtil);
         void set_nick_preferences(SharedPrefsUtil sharedPrefsUtil,String nick);
         void comprovar_nick_existent(Activitiy1Model.detallsCallback2 callback,String nick);
         void rebre_detalls_usu(Activitiy1Model.detallsCallback callback,String nick);
         void crear_nou_usuari(Activitiy1Model.detallsCallback2 callback,String nick);
         /*interface OnfinishListener{
             void onFinished(Usuari usuari, String cas);
             void onFailure(Throwable t);
         }*/
        // void navegarWeb();


    }

}
