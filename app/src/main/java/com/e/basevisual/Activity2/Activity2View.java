package com.e.basevisual.Activity2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.e.basevisual.Objects.Usuari;
import com.e.basevisual.R;
import com.e.basevisual.root.App;
import com.e.basevisual.utils.Navigator;
import com.e.basevisual.utils.clickButton;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import javax.inject.Inject;

public class Activity2View extends AppCompatActivity implements Activity2MVP.view {
    private InterstitialAd mInterstitialAd;
    @Inject
    @NonNull
    Activity2MVP.presenter presenter2;
    @Inject
    Navigator navigator2;
    @BindView(R.id.tv2act)
    TextView tv2act;
    @BindView(R.id.image)
    ImageView imageView;
    @BindView(R.id.bt2act)
    Button bt2act;
    @BindView(R.id.tvEspera)
    TextView tvEspera;
    @BindView(R.id.progressBarEspera)
    ProgressBar progressBar;
    @BindView(R.id.tvNumFoto)
    TextView tvNumFoto;
    @BindView(R.id.contador)
    TextView contador;
    Animation a= new AlphaAnimation(1.00f,0.00f);
    @OnClick (R.id.bt2act)
    public void onClick(){
        clickButton.click(getApplicationContext());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            bt2act.setBackground(getDrawable(R.drawable.boto1press));
        }
        android.os.Handler handler=new android.os.Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    bt2act.setBackground(getDrawable(R.drawable.boto1));
                    presenter2.obtenir_num_test(usuari);
                }
            }
        },200);
    };
    Bitmap bm;
    Usuari usuari;
    int j=12;



    private int cas,id,num_part,punt_mitj;
    private String nick,ADN;
    @Override
    public void onBackPressed() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_activity2_view);
        ButterKnife.bind(this);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        ((App) getApplication()).getComponent().inject(this);
    }
    @Override
    protected void onResume(){
        super.onResume();
        Intent intent=getIntent();
        usuari=new Usuari();
        navigator2=new Navigator();
        usuari=navigator2.rebuda_2Act(intent);
        presenter2.setView(this);
        a.setDuration(1000);


    }



    @Override
    public void mostrarProgressBar() {
        tv2act.setVisibility(View.INVISIBLE);
        bt2act.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        tvEspera.setVisibility(View.VISIBLE);
        imageView.setVisibility(View.INVISIBLE);


    }
    @Override
    public void onAttach(int numTest,int numIm,Usuari usuari) {
        LayoutInflater vi = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = vi.inflate(R.layout.content_activity2_view, null);
        presenter2.començar_descarrega(numIm,numTest,usuari);

    }

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    public void mostrarFoto(Bitmap resource,int num_foto,int num_test,Usuari usuari) {

        progressBar.setVisibility(View.INVISIBLE);
        tvEspera.setVisibility(View.INVISIBLE);
        imageView.setVisibility(View.INVISIBLE);
        tvNumFoto.setText(getString(R.string.textbd,num_foto));
        tvNumFoto.setVisibility(View.VISIBLE);
        android.os.Handler handler=new android.os.Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
               imageView.setVisibility(View.VISIBLE);
               tvNumFoto.setVisibility(View.INVISIBLE);
               presenter2.conta_enrere(num_foto,num_test,usuari);
               mostrar_contador(12);
            }},2000);
        try{
            int w=resource.getWidth();
            int h=resource.getHeight();
            if(w>h){
                imageView.setImageBitmap(resource);

                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            }else{
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                imageView.setImageBitmap(resource);

            }

        }catch(Resources.NotFoundException E){
            Toast.makeText(this, "error loading image, trying again", Toast.LENGTH_SHORT).show();
            presenter2.obtenir_num_test(usuari);

        }



    }

    @Override
    public void navegar_3Act(Usuari usuari, int num_test) {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.");
        }
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                android.os.Handler handler = new android.os.Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        navigator2.navegar_3Act(getApplicationContext(), usuari.getId(), usuari.getNick(), usuari.getADN(), usuari.getNumero_partides(), usuari.getPuntuacio_mitjana(), num_test);
                    }
                },500);
            }
        });
    }
    @Override
    public void mostrarError(){
        tv2act.setVisibility(View.VISIBLE);
        tv2act.setText(R.string.text2_5);
    }
    public void mostrar_contador(int i){
        contador.setText(String.valueOf(i));
        contador.setVisibility(View.VISIBLE);
        contador.startAnimation(a);
        j=i;
        android.os.Handler handler1=new android.os.Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                contador.setVisibility(View.INVISIBLE);
                android.os.Handler handler2=new android.os.Handler();
                handler2.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        j--;
                        if(j>0){
                            mostrar_contador(j);
                        }else{
                            return;
                        }
                    }
                },200);
            }
        },800);
    }
}



