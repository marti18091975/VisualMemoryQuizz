package com.e.basevisual.Activity1;



import android.util.Log;

import com.e.basevisual.Objects.Usuari;
import com.e.basevisual.utils.SharedPrefsUtil;

import org.reactivestreams.Subscription;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Activitiy1Model implements Activity1MVP.model {
    private Activity1API activity1API;
    Usuari usuari1 = new Usuari();


    public Activitiy1Model(Activity1API activity1API) {
        this.activity1API = activity1API;
    }

    @Override
    public Boolean fer_comprovacio_preferences(SharedPrefsUtil sharedPrefsUtil) {
        if (!(sharedPrefsUtil.get(SharedPrefsUtil.PREF_KEY, "").equals(""))) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String get_nick_preferences(SharedPrefsUtil sharedPrefsUtil) {
        return sharedPrefsUtil.get(SharedPrefsUtil.PREF_KEY, "");
    }

    @Override
    public void set_nick_preferences(SharedPrefsUtil sharedPrefsUtil, String nick) {
        if (nick.equals("")) {
            sharedPrefsUtil.put(SharedPrefsUtil.PREF_KEY, "usuari");
        } else {
            sharedPrefsUtil.put(SharedPrefsUtil.PREF_KEY, nick);
        }

    }


    @Override
    public void comprovar_nick_existent(final  detallsCallback2 callback,String nick) {


         activity1API.rebre_usuari(nick)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Usuari>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("Martí","subscrit!");
            }

            @Override
            public void onNext(Usuari usuari) {
                Log.d("Martí","onNext");
                callback.onSuccess(usuari);

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
        }



    @Override
    public void rebre_detalls_usu(final detallsCallback callback,String nick) {

        activity1API.rebre_ranking(nick)
                     .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<List<Usuari>>() {
            @Override
            public void onSubscribe(Disposable d) {

               Log.d("Martí","subscrit!");
            }

            @Override
            public void onNext(List<Usuari> usuaris) {
                callback.onSuccess(usuaris);
                Log.d("Martí","onNext");

            }

            @Override
            public void onError(Throwable e) {
                callback.onError();
                Log.d("Martí","error");
            }
            @Override
            public void onComplete() {
                Log.d("Martí","finalitzat");
                    }
                });

    }

    @Override
    public void crear_nou_usuari(final detallsCallback2 callback,String nick) {

        activity1API.registrar_nou_usuari(nick)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Usuari>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                        Log.d("Martí","subscrit!");
                    }

                    @Override
                    public void onNext(Usuari usuari) {
                        usuari.setNick(nick);
                        callback.onSuccess(usuari);
                        Log.d("Martí","onNext");

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError();
                        Log.d("Martí","error");
                    }
                    @Override
                    public void onComplete() {
                        Log.d("Martí","finalitzat");
                    }
                });

    }

       /* call.enqueue(new Callback<Usuari>() {
            @Override
            public void onResponse(Call<Usuari> call, Response<Usuari> response) {
                onfinishListener.onFinished(response.body(),"crear_nou_usuari");
            }

            @Override
            public void onFailure(Call<Usuari> call, Throwable t) {
                onfinishListener.onFailure(t);

            }
        });*/



    public interface detallsCallback{
       void onSuccess(List<Usuari> usuaris);
       void onError();
    }
    public interface detallsCallback2{
        void onSuccess(Usuari usuari);
        void onError();
    }




}
