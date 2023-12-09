package com.example.primejsf.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class ContractMonthDTO implements Serializable {
    private int id;
    private int contract;
    private float km;
    private float fuel;
    private float fee;
    private LocalDate date;
    private int feeID;

    public ContractMonthDTO(){}

    public ContractMonthDTO(int id, int contract, float km, float fuel, float fee, LocalDate date, int feeID) {
        this.id = id;
        this.contract = contract;
        this.km = km;
        this.fuel = fuel;
        this.fee = fee;
        this.date = date;
        this.feeID = feeID;
    }

    public ContractMonthDTO(int contract, float km, float fuel, float fee, LocalDate date, int feeID) {
        this.contract = contract;
        this.km = km;
        this.fuel = fuel;
        this.fee = fee;
        this.date = date;
        this.feeID = feeID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getContract() {
        return contract;
    }

    public void setContract(int contract) {
        this.contract = contract;
    }

    public float getKm() {
        return km;
    }

    public void setKm(float km) {
        this.km = km;
    }

    public float getFuel() {
        return fuel;
    }

    public void setFuel(float fuel) {
        this.fuel = fuel;
    }

    public float getFee() {
        return fee;
    }

    public void setFee(float fee) {
        this.fee = fee;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getFeeID() {
        return feeID;
    }

    public void setFeeID(int feeID) {
        this.feeID = feeID;
    }
}
