package com.e.basevisual.Activity1;

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
public class Activity1Module {


    Gson gson=new GsonBuilder().setLenient().create();


    @Provides
    public Activity1MVP.presenter provideFragment1Presenter(Activity1MVP.model frag1Model){
        return new Activity1Presenter(frag1Model);
    }
    @Singleton
    @Provides
    public Activity1MVP.model providesActivity1Model(Activity1API activity1API){
        return new Activitiy1Model(activity1API);
    }
    public final  String BASE_URL="http://gimcana.esy.es";
     @Provides
     public OkHttpClient provideHttpClient(){
         HttpLoggingInterceptor interceptor= new HttpLoggingInterceptor();
         interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
         return new OkHttpClient.Builder().addInterceptor(interceptor).build();
     };
    @Provides
    public  Retrofit provideRetrofit(String baseURL){
        return new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(provideHttpClient())
                .build();
    }
    @Provides
    public Activity1API provideVisualService(){
        return provideRetrofit(BASE_URL).create(Activity1API.class);
    }
}

