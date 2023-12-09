package com.example.primejsf.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class ContractDTO implements Serializable {
    private int id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private int gru;
    private int car;

    public ContractDTO(){}

    public ContractDTO(int idd, String na, LocalDate st, LocalDate en, int id_gru, int id_car){
        // TODO Auto-generated constructor stub
        id = idd;
        name = na;
        startDate = st;
        endDate = en;
        gru = id_gru;
        car = id_car;
    }

    public ContractDTO(String na, LocalDate st, LocalDate en, int id_gru, int id_car){
        // TODO Auto-generated constructor stub
        name = na;
        startDate = st;
        endDate = en;
        gru = id_gru;
        car = id_car;
    }

    @Override
    public String toString() {
        return name;
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getGru() {
        return gru;
    }

    public void setGru(int gru) {
        this.gru = gru;
    }

    public int getCar() {
        return car;
    }

    public void setCar(int car) {
        this.car = car;
    }
}
