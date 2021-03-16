package com.e.basevisual.Activity1;

import android.widget.Toast;

import com.e.basevisual.Objects.Usuari;
import com.e.basevisual.utils.SharedPrefsUtil;

import androidx.annotation.Nullable;

import org.reactivestreams.Subscription;

import java.util.List;

import io.reactivex.internal.subscriptions.ArrayCompositeSubscription;


public class Activity1Presenter implements Activity1MVP.presenter {
    @Nullable
    private Activity1MVP.view view;
    private Activity1MVP.model model;
    public Activity1Presenter(Activity1MVP.model model){
        this.model=model;
    }
    @Override
    public void setView(Activity1MVP.view view) {
        this.view=view;
    }

    @Override
    public void comprovar_registre(SharedPrefsUtil sharedPrefsUtil) {

        if(model.fer_comprovacio_preferences(sharedPrefsUtil)){
            String nick=model.get_nick_preferences(sharedPrefsUtil);
            obtenir_Usuari_dades(nick);
        }

    }

    @Override
    public void obtenir_Usuari_dades(String nick) {
        model.rebre_detalls_usu(new Activitiy1Model.detallsCallback() {
            @Override
            public void onSuccess(List<Usuari> usuaris) {
                try{
                view.mostrar_tv2(usuaris, nick);}catch (NullPointerException E){
                   try {
                       view.mostrar_tv2(usuaris,"Pepito");
                   }catch (NullPointerException F){
                      view.missatge_error(F);
                   }
                }
            }
            @Override
            public void onError() {

            }
        },nick);


    }



    @Override
    public void onDetachView() {
        this.view=null;

    }


    @Override
    public void registrar_a_Model(SharedPrefsUtil sharedPrefsUtil,String nick) {
        model.set_nick_preferences(sharedPrefsUtil,nick);
       model.comprovar_nick_existent(new Activitiy1Model.detallsCallback2() {
           @Override
           public void onSuccess(Usuari usuari) {
               try{
               if(usuari.getId()>0){
                   view.mostrar_missatge_nick_usat();
               }else{
                   crear_nou_usuari(nick);
                   view.mostrar_missatge_ok(usuari.getNick());

               }}catch (NullPointerException E){
                   crear_nou_usuari("Ramoncete");
                   view.mostrar_missatge_ok(usuari.getNick());
               }
           }
           @Override
           public void onError() {

           }
       },nick);
    }
    @Override
    public void crear_nou_usuari(String nick){
        model.crear_nou_usuari(new Activitiy1Model.detallsCallback2() {
            @Override
            public void onSuccess(Usuari usuari) {
                view.navegar_2Act(usuari.getId(),usuari.getNick(),usuari.getADN(),usuari.getNumero_partides(),usuari.getPuntuacio_mitjana());
            }

            @Override
            public void onError() {

            }
        }, nick);
    }

}
