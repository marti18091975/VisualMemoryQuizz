package com.e.basevisual.root;

import android.app.Application;
import android.content.Context;

import com.e.basevisual.Activity1.Activity1Module;
import com.e.basevisual.Activity2.Activity2Module;
import com.e.basevisual.Activity3.Activity3Module;
import com.e.basevisual.Activity4.Activity4Module;
import com.squareup.leakcanary.LeakCanary;


public class App extends Application {
    private ApplicationComponent component;



    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);

        component= DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .activity2Module(new Activity2Module())
                .activity1Module(new Activity1Module())
                .activity3Module(new Activity3Module())
                .activity4Module(new Activity4Module())
                .build();
    }
    public ApplicationComponent getComponent(){
        return component;
    }
    public static App get(Context context) {
        return (App) context.getApplicationContext();
    }

}
