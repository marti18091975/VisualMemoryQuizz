package com.e.basevisual.Objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Singleton;

public class Usuari {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("nick")
    @Expose
    private String nick;
    @SerializedName("ADN")
    @Expose
    private String ADN;
    @SerializedName("numero_partides")
    @Expose
    private int numero_partides;
    @SerializedName("puntuacio_mitjana")
    @Expose
    private float puntuacio_mitjana;
    @SerializedName("num_test")
    @Expose
    private int num_test;
    @SerializedName("ranking")
    @Expose
    private int ranking;

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public float getPuntuacio() {
        return puntuacio;
    }

    public void setPuntuacio(float puntuacio) {
        this.puntuacio = puntuacio;
    }

    @SerializedName("puntuacio")
    @Expose
    private float puntuacio;

    public Usuari(int id, String nick, String ADN, int numero_partides, float puntuacio_mitjana,int num_test,float puntuacio,int ranking) {
        this.id = id;
        this.nick = nick;
        this.ADN = ADN;
        this.numero_partides = numero_partides;
        this.puntuacio_mitjana = puntuacio_mitjana;
        this.num_test=num_test;
        this.puntuacio=puntuacio;
        this.ranking=ranking;
    }
    public Usuari(){};

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum_test() {
        return num_test;
    }

    public void setNum_test(int num_test) {
        this.num_test = num_test;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getADN() {
        return ADN;
    }

    public void setADN(String ADN) {
        this.ADN = ADN;
    }

    public int getNumero_partides() {
        return numero_partides;
    }

    public void setNumero_partides(int numero_partides) {
        this.numero_partides = numero_partides;
    }

    public float getPuntuacio_mitjana() {
        return puntuacio_mitjana;
    }

    public void setPuntuacio_mitjana(float puntuacio_mitjana) {
        this.puntuacio_mitjana = puntuacio_mitjana;
    }
}
