package com.e.basevisual.Activity3;

import com.e.basevisual.Objects.Pregunta;
import com.e.basevisual.Objects.Usuari;

import java.util.List;


import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Activity3API {
    @GET("obtenir_preguntes.php?")
    Observable<List<Pregunta>>
    obtenir_preguntes(@Query("num_test") int num_test);
    @POST("actualitzar_usuari_visual.php?")
    @FormUrlEncoded
    Observable<Integer>
    actualitzar(@Field("id") int id,@Field("ADN") String ADN,@Field("numero_partides")int num_partides,@Field("puntuacio_mitjana") float puntuacio_mitjana);

}
