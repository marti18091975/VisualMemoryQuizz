package com.e.basevisual.Activity2;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class Activity2Module {
    Gson gson=new GsonBuilder().setLenient().create();
    @Provides
    public Activity2MVP.presenter provideActivity2Presenter(Activity2MVP.model act2Model){
        return new Activity2Presenter(act2Model);
    }
    @Singleton
    @Provides
    public Activity2MVP.model providesActivity2Model(Context context){
        return new Activity2Model(context);
    }
}
