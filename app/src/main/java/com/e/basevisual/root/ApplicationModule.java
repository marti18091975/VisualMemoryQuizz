package com.e.basevisual.root;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


    @Module
    public class ApplicationModule {
        private Application application;

        public ApplicationModule(Application application){
            this.application=application;
        }

        @Provides
        @Singleton
        public Context provideContext(){
            return application;
        }
        @Provides
        SharedPreferences provideSharedPrefs() {
            return application.getSharedPreferences("visual-prefs", Context.MODE_PRIVATE);
        }
    }

