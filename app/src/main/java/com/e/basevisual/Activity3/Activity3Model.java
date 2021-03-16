package com.e.basevisual.Activity3;

import android.util.Log;

import com.e.basevisual.Objects.Pregunta;
import com.e.basevisual.Objects.Usuari;

import java.util.List;
import java.util.Observable;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity3Model implements Activity3MVP.model {

    Activity3API activity3API;

    public Activity3Model(Activity3API activity3API){
        this.activity3API=activity3API;}

    @Override
    public void obtenir_preguntes(final Activity3Model.detallsCallback callback,Usuari usuari) {
        activity3API.obtenir_preguntes(usuari.getNum_test()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Pregunta>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("Martí","subscrit!");
                    }

                    @Override
                    public void onNext(List<Pregunta> preguntes) {
                        Log.d("Martí","onNext");
                        callback.onSuccess(preguntes);

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("Martí","error");
                        callback.onError();
                    }

                    @Override
                    public void onComplete() {
                        Log.d("Martí","finalitzat");
                    }
                });
        /*call.enqueue(new Callback<List<Pregunta>>() {
            @Override
            public void onResponse(Call<List<Pregunta>> call, Response<List<Pregunta>> response) {

                onfinishListener.onFinished(response.body(),usuari);
            }

            @Override
            public void onFailure(Call<List<Pregunta>> call,Throwable t) {
                onfinishListener.onFailure(t);
            }
        });
    }*/}



    @Override
    public void guardar_resultats(final detallsCallback2 callback,float resultat, Usuari usuari)
        {

            activity3API.actualitzar(usuari.getId(), usuari.getADN(), usuari.getNumero_partides(), usuari.getPuntuacio_mitjana())
                    .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("Martí","subscrit!");
                    }

                    @Override
                    public void onNext(Integer resposta) {
                        Log.d("Martí","onNext");
                        callback.onSuccess(usuari,resultat);

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("Martí","error");
                        callback.onError();
                    }

                    @Override
                    public void onComplete() {
                        Log.d("Martí","finalitzat");
                    }
                });
            /*call.enqueue(new Callback<Usuari>() {
                @Override
                public void onResponse(Call<Usuari> call, Response<Usuari> response) {
                    onfinishListener2.onFinished(resultat, usuari);
                }

                @Override
                public void onFailure(Call<Usuari> call, Throwable t) {
                    onfinishListener2.onFailure(t);

                }
            });*/

        }
    public interface detallsCallback{
        void onSuccess(List<Pregunta> preguntes);
        void onError();
    }
    public interface detallsCallback2{
        void onSuccess(Usuari usuari,float Resultat);
        void onError();
    }



}
