package com.e.basevisual.Activity4;

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
public class Activity4Module {
    Gson gson=new GsonBuilder().setLenient().create();
    @Provides
    public Activity4MVP.presenter provideActivity4Presenter(Activity4MVP.model act4Model){
        return new Activity4Presenter(act4Model);
    }
    @Singleton
    @Provides
    public Activity4MVP.model providesActivity4Model(Activity4API activity4API){
        return new Activity4Model(activity4API);
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
    public Activity4API provideVisualService(){
        return provideRetrofit(BASE_URL).create(Activity4API.class);
    }
}
