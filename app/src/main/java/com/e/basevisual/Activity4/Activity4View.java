package com.e.basevisual.Activity4;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.e.basevisual.Objects.Usuari;
import com.e.basevisual.R;
import com.e.basevisual.root.App;
import com.e.basevisual.utils.Navigator;
import com.e.basevisual.utils.RecyclerAdapter;
import com.e.basevisual.utils.clickButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Activity4View extends AppCompatActivity implements Activity4MVP.view {
    Usuari usuari;
    int respostaUsuari,ranking;
    private List<Usuari> classificacio2=new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;
    private MediaPlayer mp4;

    @Inject
    @NonNull
    Activity4MVP.presenter presenter4;
    @Inject
    Navigator navigator4;
    @BindView(R.id.root_view_group)
    ViewGroup viewGroup;
    @BindView(R.id.recyclerView)
    protected RecyclerView recycler;
    @BindView(R.id.textView2)
    TextView resultatFinal;
    @BindView(R.id.textView4)
    TextView títol;
    @BindView(R.id.floatingActionButton)
    FloatingActionButton btSortir;
    @BindView(R.id.floatingActionButton2)
    FloatingActionButton btTornar;
    @OnClick(R.id.floatingActionButton)
    public void btSortir(View view){
        clickButton.click(getApplicationContext());
        finish();
        Intent intent2 = new Intent(Intent.ACTION_MAIN);
        intent2.addCategory(Intent.CATEGORY_HOME);
        intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent2);
        mp4.stop();
    }
    @OnClick(R.id.floatingActionButton2)
    public void btTornar(View view){
        clickButton.click(getApplicationContext());
        navigator4.navegar_2Act(this,usuari.getId(),usuari.getNick(),usuari.getADN(),usuari.getNumero_partides(),usuari.getPuntuacio_mitjana());
        mp4.stop();
    }
    private RecyclerAdapter adapter;
    @Override
    public void onBackPressed() {

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity4_view);
        ButterKnife.bind(this);
        ((App) getApplication()).getComponent().inject(this);

    }
    @Override
    protected void onResume() {

        super.onResume();
        mp4=MediaPlayer.create(this,R.raw.intro);
        mp4.start();
        Intent intent=getIntent();
        usuari=new Usuari();
        navigator4=new Navigator();
        usuari=navigator4.rebuda_4Act(intent);
        presenter4.setView(this,usuari);
    }
    @Override
    public void omplir_recycler(List<Usuari>classificacio,Usuari usuari){
        for(int i=0;i<classificacio.size();i++){
            if(classificacio.get(i).getId()==usuari.getId()){
                ranking=(i+1);
            }
        }
        String puntuacioV=String.format("%.2f",usuari.getPuntuacio());
        String puntuaciomitjanaV=String.format("%.2f",usuari.getPuntuacio_mitjana());
        resultatFinal.setText(getString(R.string.textdb,puntuacioV,puntuaciomitjanaV,ranking));
        for(int i=0;i<10;i++){
            classificacio2.add(classificacio.get(i));
        }
        adapter = new RecyclerAdapter(classificacio2);
        layoutManager=new LinearLayoutManager(this);
        recycler.setAdapter(adapter);
        recycler.addItemDecoration(new DividerItemDecoration(this,0));
        recycler.setItemAnimator(new DefaultItemAnimator());
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(layoutManager);
    }

    @Override
    public void mostrar_error(){
        resultatFinal.setText(R.string.text4_5);
    }




}
