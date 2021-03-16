package com.e.basevisual.utils;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.widget.AppCompatButton;

import com.e.basevisual.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class clickButton  {

    static MediaPlayer mp;

    public static void click(Context context){
        mp=MediaPlayer.create(context,R.raw.click);
        mp.start();}

}
