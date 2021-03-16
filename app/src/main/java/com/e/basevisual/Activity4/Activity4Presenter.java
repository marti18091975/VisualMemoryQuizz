package com.e.basevisual.Activity4;

import com.e.basevisual.Objects.Usuari;

import java.util.List;

import androidx.annotation.Nullable;

public class Activity4Presenter implements Activity4MVP.presenter {
    @Nullable
    private Activity4MVP.view view;
    private Activity4MVP.model model;
    public Activity4Presenter(Activity4MVP.model model){
        this.model=model;
    }



    @Override
    public void setView(Activity4MVP.view view,Usuari usuari) {
        this.view=view;
        model.obtenir_classificacio(new Activity4Model.detallsCallback() {
            @Override
            public void onSuccess(Usuari usuari, List<Usuari> usuaris) {
                view.omplir_recycler(usuaris,usuari);
            }

            @Override
            public void onError() {
                view.mostrar_error();
            }
        }, usuari);
    }
/*
    @Override
    public void onFinished(List<Usuari> classificacio, Usuari usuari) {
        view.omplir_recycler(classificacio,usuari);

    }

    @Override
    public void onFailure(Throwable t) {
        view.mostrar_error();

    }*/
}
