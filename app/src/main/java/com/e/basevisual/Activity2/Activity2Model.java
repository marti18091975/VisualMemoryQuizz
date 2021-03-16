package com.e.basevisual.Activity2;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.e.basevisual.Objects.Usuari;
import com.e.basevisual.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Signature;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class Activity2Model extends Activity implements Activity2MVP.model {

    private final Context context;

    @Inject
    Activity2MVP.presenter presenter;

    public Activity2Model(Context context) {
        this.context = context;
    }
    @Override
    public void iniciar_descarrega(OnFinishListener onFinishListener, int numTest, int numIm, Usuari usuari) {


        String url = numTest + "_" + numIm;
        SimpleTarget<Bitmap> target=new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                onFinishListener.onFinished(resource, numIm, numTest, usuari);
            }
            @Override
            public void onLoadFailed(@Nullable Drawable errorDrawable) {
                super.onLoadFailed(errorDrawable);
                onFinishListener.onFailure("error");
            }
        };
        android.os.Handler handler=new android.os.Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Glide.with(context).asBitmap().load("http://gimcana.esy.es/fotosVisual/foto" + url + ".jpg").into(target);
            }
        },200);




    }
}

