package com.example.primejsf.dto;

import java.io.Serializable;

public class FleetDTO implements Serializable {
    private int id;
    private int num;

    public FleetDTO(){}

    public FleetDTO(int idd, int n) {
        // TODO Auto-generated constructor stub
        id = idd;
        num = n;
    }
    public FleetDTO(int n) {
        // TODO Auto-generated constructor stub
        num = n;
    }

    @Override
    public String toString() {
        return String.valueOf(num);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
