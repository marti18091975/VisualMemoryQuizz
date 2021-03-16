package com.e.basevisual.Activity3;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class Activity3Module {
    Gson gson=new GsonBuilder().setLenient().create();
    @Provides
    public Activity3MVP.presenter provideActivity3Presenter(Activity3MVP.model act3Model){
        return new Activity3Presenter(act3Model);
    }
    @Singleton
    @Provides
    public Activity3MVP.model providesActivity3Model(Activity3API activity3API){
        return new Activity3Model(activity3API);
    }
    public final  String BASE_URL="http://gimcana.esy.es/visual/";
    @Provides
    public OkHttpClient provideHttpClient(){
        HttpLoggingInterceptor interceptor= new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder().addInterceptor(interceptor).build();
    };
    @Provides
    public Retrofit provideRetrofit(String baseURL){
        return new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(provideHttpClient())
                .build();
    }
    @Provides
    public Activity3API provideVisualService(){
        return provideRetrofit(BASE_URL).create(Activity3API.class);
    }
}
