package com.e.basevisual.Activity1;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.e.basevisual.Objects.Usuari;
import com.e.basevisual.R;
import com.e.basevisual.root.App;
import com.e.basevisual.utils.Navigator;
import com.e.basevisual.utils.SharedPrefsUtil;
import com.e.basevisual.utils.clickButton;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements Activity1MVP.view,fragmentInfo.OnCommit{
    private InterstitialAd mInterstitialAd;
    @Inject
    public SharedPrefsUtil sharedPrefsUtil;
    @Inject
    protected Navigator navigator;
    @Inject
    @NonNull
    public Activity1MVP.presenter presenter;
    Usuari usuari=new Usuari();
    private int cas;
    private MediaPlayer mp;
    @BindView(R.id.etNickName)
    EditText etNickName;
    @BindView(R.id.tvInici)
    TextView tvInici;
    @BindView(R.id.imageView2)
    ImageView tvInici2;
    @BindView(R.id.imageView3)
    ImageView tvInici3;
    @BindView(R.id.btinfo)
    ImageButton btinfo;
    @BindView(R.id.webButton)
    ImageButton btweb;
    @OnClick(R.id.webButton)
    public void Btweb(View view){
        clickButton.click(this);
        mp.stop();
        navigator.navegarWeb(this);
    }
    @BindView(R.id.bt_ok)
    Button bt_ok;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick(R.id.bt_ok)
    public void OkButton(View view){


        clickButton.click(this);
        if(cas==1){

            if(etNickName.getText().length()==0){
                Toast.makeText(this, R.string.text1_11, Toast.LENGTH_SHORT).show();
            }else{
                    presenter.registrar_a_Model(sharedPrefsUtil,etNickName.getText().toString().trim());}
            bt_ok.setBackground(getDrawable(R.drawable.boto2press));
            Handler handler2=new Handler();
            handler2.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        bt_ok.setBackground(getDrawable(R.drawable.boto2));
                    }

                }
            },200);
        }

        else{}
    }
    @Override
    public void onBackPressed() {

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
            setContentView(R.layout.activity_main);
        }catch (OutOfMemoryError E){
            setContentView(R.layout.activity_main_poor);
        }

        ButterKnife.bind(this);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        ((App) getApplication()).getComponent().inject(this);}

    @Override
    public void mostrarBoto() {
        bt_ok.setVisibility(View.VISIBLE);

    }

    @Override
    protected void onResume(){
        super.onResume();
        cas=1;
        mp=MediaPlayer.create(this,R.raw.intro);
        mp.start();
        presenter.setView(this);
        presenter.comprovar_registre(sharedPrefsUtil);
        bt_ok.setVisibility(View.VISIBLE);

        btinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton.click(getApplicationContext());



               if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    btinfo.setBackground(getDrawable(R.drawable.boto2press));
                }
                Handler handler2=new Handler();
                    handler2.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                btinfo.setBackground(getDrawable(R.drawable.boto2));
                            }
                            bt_ok.setVisibility(View.INVISIBLE);
                            mp.stop();
                            navegar_fragment_info();
                        }
                    },200);
                }

            });
    }


    @Override
    public void onStop() {
        super.onStop();
        presenter.onDetachView();
    }
    @Override
    public void mostrar_tv2(List<Usuari> usuaris,String nick) {
        cas = 2;
        int ranking = 0;

        etNickName.setVisibility(View.INVISIBLE);
        tvInici.setVisibility(View.VISIBLE);
        tvInici2.setVisibility(View.INVISIBLE);
        tvInici3.setVisibility(View.INVISIBLE);
        for (int i = 0; i < usuaris.size(); i++) {
            if (usuaris.get(i).getNick().equals(nick)) {
                ranking = i + 1;
                usuari = usuaris.get(i);
            }
        }


        String puntMitj = String.format("%.2f", usuari.getPuntuacio_mitjana());
        tvInici.setText(getString(R.string.textad, nick, puntMitj, ranking));
        if (usuari.getADN().length() == 6) {
            usuari.setADN(usuari.getADN() + "0000");
        }


        bt_ok.setText(R.string.text1_4);
        bt_ok.setOnClickListener(v -> {
            clickButton.click(getApplicationContext());
            mp.stop();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                bt_ok.setBackground(getDrawable(R.drawable.boto2press));
            }
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
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                bt_ok.setBackground(getDrawable(R.drawable.boto2));
                            }
                            navegar_2Act(usuari.getId(), usuari.getNick(), usuari.getADN(), usuari.getNumero_partides(), usuari.getPuntuacio_mitjana());

                        }
                    }, 200);
                }
            });
        });
    }


    @Override
    public void mostrar_missatge_ok(String nick) {
        Toast.makeText(getApplicationContext(), R.string.text1_8, Toast.LENGTH_SHORT).show();

        cas=2;

    }

    @Override
    public void mostrar_missatge_nick_usat() {
        Toast.makeText(getApplicationContext(), R.string.text1_9, Toast.LENGTH_SHORT).show();

    }


    @Override
    public void missatge_error(Throwable t) {
        Toast.makeText(getApplicationContext(), R.string.text1_10 , Toast.LENGTH_SHORT).show();
        tvInici.setText(t.toString().trim());
    }

    @Override
    public void navegar_2Act(int id,String nick,String ADN,int num_part,float punt_mitj) {
        mp.stop();
        navigator.navegar_2Act(this,id,nick,ADN,num_part,punt_mitj);
    }

    @Override
    public void navegar_fragment_info() {

        navigator.navegarFragInfo(MainActivity.this);
    }
}
