package com.e.basevisual.Activity4;

import com.e.basevisual.Objects.Usuari;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Activity4API {
    @GET("ordenar_usuaris.php?")
    Observable<List<Usuari>>
    ordenar_usuaris();
}
