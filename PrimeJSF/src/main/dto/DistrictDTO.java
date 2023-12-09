package com.example.primejsf.dto;

import java.io.Serializable;

public class DistrictDTO implements Serializable {
    private int id;
    private String name;

    public DistrictDTO(){}

    public DistrictDTO(int i, String n) {
        // TODO Auto-generated constructor stub
        id = i;
        name = n;
    }

    public DistrictDTO(String n) {
        // TODO Auto-generated constructor stub
        name = n;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        DistrictDTO that = (DistrictDTO) o;

        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return name;
    }
}
