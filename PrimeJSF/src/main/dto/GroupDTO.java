package com.example.primejsf.dto;

import java.io.Serializable;

public class GroupDTO implements Serializable {
    private int id;
    private String pais;
    private String nombre;

    public GroupDTO(){}

    public GroupDTO(int i, String p, String n) {
        // TODO Auto-generated constructor stub
        id = i;
        pais = p;
        nombre = n;
    }

    public GroupDTO(String n, String p) {
        // TODO Auto-generated constructor stub
        nombre = n;
        pais = p;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return nombre;
    }
}
