package com.example.primejsf.dto;

import java.io.Serializable;

public class CarDTO implements Serializable {
    private int id;
    private String placa;
    private int flo;
    private int bra;
    private int dri1;
    private int dri2;

    public CarDTO(){}

    public CarDTO(int idd, int floc, int mar, String pla, int cho1, int cho2) {
        // TODO Auto-generated constructor stub
        id = idd;
        flo = floc;
        bra = mar;
        placa = pla;
        dri1 = cho1;
        dri2 = cho2;

    }

    public CarDTO(int floc, int mar, String pla, int cho1, int cho2) {
        // TODO Auto-generated constructor stub
        flo = floc;
        bra = mar;
        placa = pla;
        dri1 = cho1;
        dri2 = cho2;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getPlaca() {
        return placa;
    }
    public void setPlaca(String placa) {
        this.placa = placa;
    }
    public int getFlo() {
        return flo;
    }
    public void setFlo(int flo) {
        this.flo = flo;
    }
    public int getBra() {
        return bra;
    }
    public void setBra(int bra) {
        this.bra = bra;
    }
    public int getDri1() {
        return dri1;
    }
    public void setDri1(int dri1) {
        this.dri1 = dri1;
    }
    public int getDri2() {
        return dri2;
    }
    public void setDri2(int dri2) {
        this.dri2 = dri2;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return getPlaca();
    }
}
