package com.e.basevisual.Objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pregunta {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("num_test")
    @Expose
    private int num_test;
    @SerializedName("num_foto")
    @Expose
    private int num_foto;
    @SerializedName("num_preg")
    @Expose
    private int num_preg;
    @SerializedName("pregunta_cat")
    @Expose
    private String pregunta_cat;
    @SerializedName("resposta1_cat")
    @Expose
    private String resposta1_cat;
    @SerializedName("resposta2_cat")
    @Expose
    private String resposta2_cat;
    @SerializedName("resposta3_cat")
    @Expose
    private String resposta3_cat;
    @SerializedName("resposta4_cat")
    @Expose
    private String resposta4_cat;
    @SerializedName("pregunta_esp")
    @Expose
    private String pregunta_esp;
    @SerializedName("resposta1_esp")
    @Expose
    private String resposta1_esp;
    @SerializedName("resposta2_esp")
    @Expose
    private String resposta2_esp;
    @SerializedName("resposta3_esp")
    @Expose
    private String resposta3_esp;
    @SerializedName("resposta4_esp")
    @Expose
    private String resposta4_esp;
    @SerializedName("pregunta_ing")
    @Expose
    private String pregunta_ing;
    @SerializedName("resposta1_ing")
    @Expose
    private String resposta1_ing;
    @SerializedName("resposta2_ing")
    @Expose
    private String resposta2_ing;
    @SerializedName("resposta3_ing")
    @Expose
    private String resposta3_ing;
    @SerializedName("resposta4_ing")
    @Expose
    private String resposta4_ing;
    @SerializedName("resposta")
    @Expose
    private int resposta;

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

    public int getNum_foto() {
        return num_foto;
    }

    public void setNum_foto(int num_foto) {
        this.num_foto = num_foto;
    }

    public int getNum_preg() {
        return num_preg;
    }

    public void setNum_preg(int num_preg) {
        this.num_preg = num_preg;
    }

    public String getPregunta_cat() {
        return pregunta_cat;
    }

    public void setPregunta_cat(String pregunta_cat) {
        this.pregunta_cat = pregunta_cat;
    }

    public String getResposta1_cat() {
        return resposta1_cat;
    }

    public void setResposta1_cat(String resposta1_cat) {
        this.resposta1_cat = resposta1_cat;
    }

    public String getResposta2_cat() {
        return resposta2_cat;
    }

    public void setResposta2_cat(String resposta2_cat) {
        this.resposta2_cat = resposta2_cat;
    }

    public String getResposta3_cat() {
        return resposta3_cat;
    }

    public void setResposta3_cat(String resposta3_cat) {
        this.resposta3_cat = resposta3_cat;
    }

    public String getResposta4_cat() {
        return resposta4_cat;
    }

    public void setResposta4_cat(String resposta4_cat) {
        this.resposta4_cat = resposta4_cat;
    }

    public String getPregunta_esp() {
        return pregunta_esp;
    }

    public void setPregunta_esp(String pregunta_esp) {
        this.pregunta_esp = pregunta_esp;
    }

    public String getResposta1_esp() {
        return resposta1_esp;
    }

    public void setResposta1_esp(String resposta1_esp) {
        this.resposta1_esp = resposta1_esp;
    }

    public String getResposta2_esp() {
        return resposta2_esp;
    }

    public void setResposta2_esp(String resposta2_esp) {
        this.resposta2_esp = resposta2_esp;
    }

    public String getResposta3_esp() {
        return resposta3_esp;
    }

    public void setResposta3_esp(String resposta3_esp) {
        this.resposta3_esp = resposta3_esp;
    }

    public String getResposta4_esp() {
        return resposta4_esp;
    }

    public void setResposta4_esp(String resposta4_esp) {
        this.resposta4_esp = resposta4_esp;
    }

    public String getPregunta_ing() {
        return pregunta_ing;
    }

    public void setPregunta_ing(String pregunta_ing) {
        this.pregunta_ing = pregunta_ing;
    }

    public String getResposta1_ing() {
        return resposta1_ing;
    }

    public void setResposta1_ing(String resposta1_ing) {
        this.resposta1_ing = resposta1_ing;
    }

    public String getResposta2_ing() {
        return resposta2_ing;
    }

    public void setResposta2_ing(String resposta2_ing) {
        this.resposta2_ing = resposta2_ing;
    }

    public String getResposta3_ing() {
        return resposta3_ing;
    }

    public void setResposta3_ing(String resposta3_ing) {
        this.resposta3_ing = resposta3_ing;
    }

    public String getResposta4_ing() {
        return resposta4_ing;
    }

    public void setResposta4_ing(String resposta4_ing) {
        this.resposta4_ing = resposta4_ing;
    }

    public int getResposta() {
        return resposta;
    }

    public void setResposta(int resposta) {
        this.resposta = resposta;
    }

}

