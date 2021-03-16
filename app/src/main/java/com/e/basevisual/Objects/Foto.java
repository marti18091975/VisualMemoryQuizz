package com.e.basevisual.Objects;

import android.graphics.Bitmap;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Foto {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("bitmap")
    @Expose
    private Bitmap bitmap;
    @SerializedName("número")
    @Expose
    private int número;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public int getNúmero() {
        return número;
    }

    public void setNúmero(int número) {
        this.número = número;
    }




}
