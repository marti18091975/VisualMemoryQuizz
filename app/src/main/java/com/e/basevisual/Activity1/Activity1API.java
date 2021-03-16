package com.e.basevisual.Activity1;

import com.e.basevisual.Objects.Usuari;

import java.util.List;

import io.reactivex.Observable;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Activity1API {
    @GET("/visual/comprovar_nick_existent.php?")
    Observable<Usuari>
    rebre_usuari(@Query("nick") String nick);

    @GET("/visual/ordenar_usuaris.php?")
    Observable<List<Usuari>>
    rebre_ranking(@Query("nick") String nick);

    @POST("/visual/insertUsuariVisual_retornar_id.php?")
    @FormUrlEncoded
    Observable<Usuari>
    registrar_nou_usuari(@Field("nick") String nick);
}
