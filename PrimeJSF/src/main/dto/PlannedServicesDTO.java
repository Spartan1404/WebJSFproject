package com.example.primejsf.dto;

import java.io.Serializable;
import java.time.LocalTime;

public class PlannedServicesDTO implements Serializable {
    private int id;
    private String name;
    private int application_number;
    private String pick_up;
    private LocalTime time;
    private int gru;

    public PlannedServicesDTO(){}

    public PlannedServicesDTO(int idd, String nam, int appN, String address, LocalTime t, int id_gru) {
        // TODO Auto-generated constructor stub
        id = idd;
        name = nam;
        application_number = appN;
        pick_up = address;
        time = t;
        gru = id_gru;
    }

    public PlannedServicesDTO(String nam, int appN, String address, LocalTime t, int id_gru) {
        // TODO Auto-generated constructor stub
        name = nam;
        application_number = appN;
        pick_up = address;
        time = t;
        gru = id_gru;
    }

    @Override
    public String toString() {
        return "plannedServicesDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", application_number=" + application_number +
                ", pick_up='" + pick_up + '\'' +
                ", time=" + time +
                ", gru=" + gru +
                '}';
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

    public int getApplication_number() {
        return application_number;
    }

    public void setApplication_number(int application_number) {
        this.application_number = application_number;
    }

    public String getPick_up() {
        return pick_up;
    }

    public void setPick_up(String pick_up) {
        this.pick_up = pick_up;
    }

    public int getGru() {
        return gru;
    }

    public void setGru(int gru) {
        this.gru = gru;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
}
