package com.e.basevisual.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.e.basevisual.Activity1.MainActivity;
import com.e.basevisual.Activity1.fragmentInfo;
import com.e.basevisual.Activity2.Activity2View;
import com.e.basevisual.Activity3.Activity3View;
import com.e.basevisual.Activity4.Activity4View;
import com.e.basevisual.Objects.Usuari;
import com.e.basevisual.R;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

@Singleton
public class Navigator {
    Intent intent2,intent3;
    Context context;

    @Inject
    public Navigator() {
        }

    public void navegar_2Act(Context context,int id,String nick,String ADN,int num_part,float punt_mitj) {
        Intent intent = new Intent(context, Activity2View.class);
        Bundle bundle=new Bundle();
        intent.putExtra("id",id);
        intent.putExtra("nick",nick);
        intent.putExtra("ADN",ADN);
        intent.putExtra("num_part",num_part);
        intent.putExtra("punt_mitj",punt_mitj);
        intent.putExtras(bundle);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        }
    public Usuari rebuda_2Act(Intent intent){
        Usuari usuari=new Usuari();
        this.intent2=intent;
        intent2.getExtras();
        usuari.setId(intent2.getIntExtra("id",0));
        usuari.setNick(intent2.getStringExtra("nick"));
        usuari.setADN(intent2.getStringExtra("ADN"));
        usuari.setNumero_partides(intent2.getIntExtra("num_part",0));
        usuari.setPuntuacio_mitjana(intent2.getFloatExtra("punt_mitj",0.0f));
        return usuari;
    }
    public void navegar_3Act(Context context2,int id,String nick,String ADN,int num_part,float punt_mitj,int num_test) {
        Intent intent4 = new Intent(context2, Activity3View.class);
        Bundle bundle2=new Bundle();
        intent4.putExtra("id",id);
        intent4.putExtra("nick",nick);
        intent4.putExtra("ADN",ADN);
        intent4.putExtra("num_part",num_part);
        intent4.putExtra("punt_mitj",punt_mitj);
        intent4.putExtra("num_test",num_test);
        intent4.putExtras(bundle2);
        intent4.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context2.startActivity(intent4);
    }
    public Usuari rebuda_3Act(Intent intent){
        Usuari usuari=new Usuari();
        this.intent3=intent;
        intent3.getExtras();
        usuari.setId(intent3.getIntExtra("id",0));
        usuari.setNick(intent3.getStringExtra("nick"));
        usuari.setADN(intent3.getStringExtra("ADN"));
        usuari.setNumero_partides(intent3.getIntExtra("num_part",0));
        usuari.setPuntuacio_mitjana(intent3.getFloatExtra("punt_mitj",0.0f));
        usuari.setNum_test(intent3.getIntExtra("num_test",0));
        return usuari;

    }
    public void navegarFragInfo(MainActivity activity){

        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentInfo fragment = new fragmentInfo();
        fragmentTransaction.add(R.id.constraint_lay, fragment);
        fragmentTransaction.commit();

    }
    public void navegarWeb(Context context3){
        String url="http://gimcana.esy.es";
        Intent i=new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context3.startActivity(i);
    }

    public void navegar_4Act(Context context4,Usuari usuari) {
        Intent intent5 = new Intent(context4, Activity4View.class);
        Bundle bundle3=new Bundle();
        intent5.putExtra("id",usuari.getId());
        intent5.putExtra("nick",usuari.getNick());
        intent5.putExtra("ADN",usuari.getADN());
        intent5.putExtra("num_part",usuari.getNumero_partides());
        intent5.putExtra("punt_mitj",usuari.getPuntuacio_mitjana());
        intent5.putExtra("puntuacio",usuari.getPuntuacio());
        intent5.putExtras(bundle3);
        intent5.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context4.startActivity(intent5);
    }
    public Usuari rebuda_4Act(Intent intent){
        Usuari usuari=new Usuari();
        this.intent3=intent;
        intent3.getExtras();
        usuari.setId(intent3.getIntExtra("id",0));
        usuari.setNick(intent3.getStringExtra("nick"));
        usuari.setADN(intent3.getStringExtra("ADN"));
        usuari.setNumero_partides(intent3.getIntExtra("num_part",0));
        usuari.setPuntuacio_mitjana(intent3.getFloatExtra("punt_mitj",0.0f));
        usuari.setPuntuacio(intent3.getFloatExtra("puntuacio",0.0f));
        return usuari;

    }
}
