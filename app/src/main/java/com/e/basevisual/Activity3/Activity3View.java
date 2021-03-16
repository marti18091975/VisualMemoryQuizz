package com.e.basevisual.Activity3;

//import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.ClipDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.e.basevisual.Objects.Pregunta;
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

import java.util.List;
import javax.inject.Inject;

public class Activity3View extends AppCompatActivity implements Activity3MVP.view{
    private InterstitialAd mInterstitialAd;
    Usuari usuari;
    private MediaPlayer mpCorrect,mpWrong,mpRiures,mpAplausos;
    int respostaUsuari;
    float puntuacio;
    String[] opcions;
    private ClipDrawable mImageDrawable;
    private int fromLevel = 3333;
    private int toLevel = 0;
    public static final int MAX_LEVEL = 15;
    int encertsConsec,errorsConsec;




    @Inject
   // @NonNull
    Activity3MVP.presenter presenter3;
    @Inject
    Navigator navigator3;
    @BindView(R.id.tv3act) TextView tv;


    @BindView(R.id.tv3act2) TextView tv2;
    @BindView(R.id.nova_imatge) TextView novaImatge;
    @BindView(R.id.tv3act3) TextView tv3;
    @BindView(R.id.frame)
    FrameLayout frame;


    @BindView(R.id.bt3act)
    Button bt_ok2;
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.imageView3_1)
    ImageView imView1;
    Animation a=new AlphaAnimation(1.00f,0.00f);


    @Override
    public void onBackPressed() {

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity3_view);
        ButterKnife.bind(this);
        mImageDrawable = (ClipDrawable) imView1.getDrawable();
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

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onResume() {
        super.onResume();
        Intent intent=getIntent();
        mpCorrect=MediaPlayer.create(this,R.raw.correct);
        mpWrong=MediaPlayer.create(this,R.raw.wrong);
        mpRiures=MediaPlayer.create(this,R.raw.riures);
        mpAplausos=MediaPlayer.create(this,R.raw.aplaudiments);
        usuari=new Usuari();
        navigator3=new Navigator();
        usuari=navigator3.rebuda_3Act(intent);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        presenter3.setView(this);
        mImageDrawable.setVisible(true,true);
        mImageDrawable.setLevel(3333);
        encertsConsec=0;
        errorsConsec=0;
        bt_ok2.setOnClickListener(v-> {
            clickButton.click(getApplicationContext());
            if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.LOLLIPOP)

            { bt_ok2.setBackground(getDrawable(R.drawable.boto1press));}
            android.os.Handler handler2 = new android.os.Handler();
            handler2.postDelayed(() -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    bt_ok2.setBackground(getDrawable(R.drawable.boto1));
                }
                presenter3.obtenir_preguntes(usuari);

            },200);
          });
        opcions=new String[5];
    }

    @Override
    public void mostrar_error() {
        Toast.makeText(getApplicationContext(), R.string.text4_5, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void mostrar_pregunta(List<Pregunta> preguntes, String pregunta, String opcio1, String opcio2, String opcio3, String opcio4, int resposta, Usuari usuari, int num_foto, int num_preg,float puntuacioAc) {
        opcions[0] = opcio1;
        opcions[1] = opcio2;
        opcions[2] = opcio3;
        opcions[3] = opcio4;
        opcions[4] = getString(R.string.text3_4);
        bt_ok2.setVisibility(View.INVISIBLE);
        tv.setVisibility(View.INVISIBLE);
        tv2.setVisibility(View.VISIBLE);
        tv2.setText(pregunta);
        tv3.setVisibility(View.VISIBLE);
        a.setDuration(2000);
        if(num_preg==0||num_preg==3||num_preg==6){
        novaImatge.setVisibility(View.VISIBLE);
        novaImatge.setText(getString(R.string.textcc,num_foto));
        novaImatge.startAnimation(a);
        android.os.Handler handler2=new android.os.Handler();
        handler2.postDelayed(() -> novaImatge.setVisibility(View.INVISIBLE),2000);
        }
        tv3.setText(getString(R.string.textcc,num_foto));
        frame.setVisibility(View.VISIBLE);
        if(preguntes.get(0).toString().equals(pregunta)){
            mImageDrawable.setVisible(true,true);
            mImageDrawable.setLevel(3333);

        }


        listView.setChoiceMode(ListView.OVER_SCROLL_IF_CONTENT_SCROLLS);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            clickButton.click(getApplicationContext());
            listView.setEnabled(false);
            Handler handler=new Handler();
            handler.postDelayed(() -> {
                respostaUsuari = position + 1;
                if (respostaUsuari==resposta){
                    errorsConsec=0;
                    encertsConsec++;
                    if(encertsConsec==3){
                        encertsConsec=0;
                        mpAplausos.start();
                    }else{
                    mpCorrect.start();}
                    puntuacio += 1.12;
                }else if(respostaUsuari==5){
                    encertsConsec=0;
                }else{
                    errorsConsec++;
                    encertsConsec=0;
                    if(errorsConsec==3){
                        errorsConsec=0;
                        mpRiures.start();
                    }else{
                    mpWrong.start();}
                    puntuacio -=0.5;
                }

                toLevel = (int)((puntuacio+5) * 10000) / MAX_LEVEL;
                if (!(toLevel == fromLevel)) {

                    mImageDrawable.setLevel(toLevel);
                    fromLevel = toLevel;}
                listView.setEnabled(true);
                presenter3.obtenir_resultat(num_preg,puntuacio,usuari,preguntes, puntuacioAc);
            },200);

        });
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_checked, opcions);
        listView.setAdapter(arrayAdapter);

        listView.setVisibility(View.VISIBLE);
    }

    @Override
    public void navegar4Act(Usuari usuari) {
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
                        navigator3.navegar_4Act(getApplicationContext(),usuari);
                          }
                },500);
            }
        });

    }





}
