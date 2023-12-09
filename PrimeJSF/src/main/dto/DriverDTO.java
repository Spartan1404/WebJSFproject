package com.example.primejsf.dto;

import java.io.Serializable;

public class DriverDTO implements Serializable {
    private int id;
    private String name;
    private String direccion;
    private String dni;
    private int dist;
    private String phone;

    public DriverDTO(){}

    public DriverDTO(int idd, String na, String add, String car, int dis, String movil) {
        // TODO Auto-generated constructor stub
        id = idd;
        name = na;
        direccion = add;
        dni = car;
        dist = dis;//ServiceLocator.getDistrictService().loadSingleDistrict(id_dist);
        phone = movil;
    }

    public DriverDTO(String na, String add, String car, int dis, String movil) {
        // TODO Auto-generated constructor stub
        name = na;
        direccion = add;
        dni = car;
        dist =dis;// ServiceLocator.getDistrictService().loadSingleDistrict(id_dist);
        phone = movil;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getDist() {
        return dist;
    }

    public void setDist(int dist) {
        this.dist = dist;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
