package com.e.basevisual.Activity4;

import android.util.Log;

import com.e.basevisual.Objects.Pregunta;
import com.e.basevisual.Objects.Usuari;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity4Model implements Activity4MVP.model {
    Activity4API activity4API;

    public Activity4Model(Activity4API activity4API){
        this.activity4API=activity4API;}
    @Override
    public void obtenir_classificacio(detallsCallback callback,Usuari usuari) {
        activity4API.ordenar_usuaris()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Usuari>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("Martí","subscrit!");
                    }

                    @Override
                    public void onNext(List<Usuari> usuaris) {
                        Log.d("Martí","onNext");
                        callback.onSuccess(usuari,usuaris);

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

       /* call.enqueue(new Callback<List<Usuari>>() {
            @Override
            public void onResponse(Call <List<Usuari>> call, Response<List<Usuari>> response) {
                onfinishListener.onFinished(response.body(),usuari);
            }

            @Override
            public void onFailure(Call<List<Usuari>>call, Throwable t) {
                onfinishListener.onFailure(t);

            }
        });*/

    }
    public interface detallsCallback{
        public void onSuccess(Usuari usuari,List<Usuari> usuaris);
        public void onError();
    }
}
