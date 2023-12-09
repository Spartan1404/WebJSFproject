package com.example.primejsf.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class PlanDTO implements Serializable {
    private int id;
    private double km_planif;
    private double comb_planif;
    private int carro;
    // solo nos interesa el mes y el año
    private LocalDate fecha;

    public PlanDTO(){}

    public PlanDTO(int idd, double km, double comb, int idc, LocalDate f){
        // TODO Auto-generated constructor stub
        id = idd;
        km_planif = km;
        comb_planif = comb;
        carro = idc;
        fecha = f;
    }

    public PlanDTO(double km, double comb, int idc, LocalDate f){
        // TODO Auto-generated constructor stub
        km_planif = km;
        comb_planif = comb;
        carro = idc;
        fecha = f;
    }

    public PlanDTO(double km, int idc, LocalDate f){
        // El combustible es calculable segun la marca del carro y los km
        km_planif = km;
        comb_planif = -1;
        carro = idc;
        fecha = f;
    }

    @Override
    public String toString() {
        return "planDTO{" +
                "km_planif=" + km_planif +
                ", comb_planif=" + comb_planif +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getKm_planif() {
        return km_planif;
    }

    public void setKm_planif(double km_planif) {
        this.km_planif = km_planif;
    }

    public double getComb_planif() {
        return comb_planif;
    }

    public void setComb_planif(double comb_planif) {
        this.comb_planif = comb_planif;
    }

    public int getCarro() {
        return carro;
    }

    public void setCarro(int carro) {
        this.carro = carro;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
