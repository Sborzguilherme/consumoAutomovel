package com.example.guilhermesborz.consumoautomovel.Modelo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Micro Solution on 17/11/2016.
 */

public class Abastecimento implements Parcelable {

    private Float quilometragemAtual;
    private Float litrosAbastecidos;
    private String dataAbastecimento;
    private String postoEscolhido;

    public Abastecimento() {

    }

    public Abastecimento(Parcel in) {
        quilometragemAtual = in.readFloat();
        litrosAbastecidos = in.readFloat();
        dataAbastecimento = in.readString();
        postoEscolhido = in.readString();
    }

    public static final Creator<Abastecimento> CREATOR = new Creator<Abastecimento>() {
        @Override
        public Abastecimento createFromParcel(Parcel in) {
            return new Abastecimento(in);
        }

        @Override
        public Abastecimento[] newArray(int size) {
            return new Abastecimento[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }


    public Float getQuilometragemAtual() {
        return quilometragemAtual;
    }

    public void setQuilometragemAtual(Float quilometragemAtual) {
        this.quilometragemAtual = quilometragemAtual;
    }

    public Float getLitrosAbastecidos() {
        return litrosAbastecidos;
    }

    public void setLitrosAbastecidos(Float litrosAbastecidos) {
        this.litrosAbastecidos = litrosAbastecidos;
    }

    public String getDataAbastecimento() {
        return dataAbastecimento;
    }

    public void setDataAbastecimento(String dataAbastecimento) {
        this.dataAbastecimento = dataAbastecimento;
    }

    public String getPostoEscolhido() {
        return postoEscolhido;
    }

    public void setPostoEscolhido(String postoEscolhido) {
        this.postoEscolhido = postoEscolhido;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(quilometragemAtual);
        dest.writeFloat(litrosAbastecidos);
        dest.writeString(dataAbastecimento);
        dest.writeString(postoEscolhido);
    }
}
