package com.example.primejsf.dto;

import java.io.Serializable;

public class BrandDTO implements Serializable {
    private int id;
    private String name;
    private int seats;
    private String tipo_comb;
    private double consumo_comb;

    public BrandDTO() {
    }

    public BrandDTO(int code, String nombre, int asi, String tipo, double consu) {
        // TODO Auto-generated constructor stub
        id = code;
        name = nombre;
        seats = asi;
        tipo_comb = tipo;
        consumo_comb = consu;
    }

    public BrandDTO(String nombre, int asi, String tipo, double consu) {
        // TODO Auto-generated constructor stub
        name = nombre;
        seats = asi;
        tipo_comb = tipo;
        consumo_comb = consu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public String getTipo_comb() {
        return tipo_comb;
    }

    public void setTipo_comb(String tipo_comb) {
        this.tipo_comb = tipo_comb;
    }

    public double getConsumo_comb() {
        return consumo_comb;
    }

    public void setConsumo_comb(double consumo_comb) {
        this.consumo_comb = consumo_comb;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return name;
    }
}
