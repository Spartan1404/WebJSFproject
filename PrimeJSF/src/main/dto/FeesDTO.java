package com.example.primejsf.dto;

import java.io.Serializable;

public class FeesDTO implements Serializable {
    private int id;
    private int rango;
    private int rangoSup;
    private double tarifa;

    public FeesDTO(){}

    public FeesDTO(int idd, int r, int ra, double ta) {
        // TODO Auto-generated constructor stub
        id = idd;
        rango = r;
        rangoSup = ra;
        tarifa = ta;
    }

    public FeesDTO(int r, int ra, double ta) {
        // TODO Auto-generated constructor stub
        rango = r;
        rangoSup = ra;
        tarifa = ta;
    }

    @Override
    public String toString() {
        return "feesDTO{" +
                "rango=" + rango +
                ", rangoSup=" + rangoSup +
                ", tarifa=" + tarifa +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRango() {
        return rango;
    }

    public void setRango(int rango) {
        this.rango = rango;
    }

    public int getRangoSup() {
        return rangoSup;
    }

    public void setRangoSup(int rangoSup) {
        this.rangoSup = rangoSup;
    }

    public double getTarifa() {
        return tarifa;
    }

    public void setTarifa(double tarifa) {
        this.tarifa = tarifa;
    }
}
