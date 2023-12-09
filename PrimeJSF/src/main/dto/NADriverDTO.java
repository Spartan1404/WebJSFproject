package com.example.primejsf.dto;

public class NADriverDTO {
    private int id_driver;
    private int id_marca;

    public int getId_driver() {
        return id_driver;
    }

    public void setId_driver(int id_driver) {
        this.id_driver = id_driver;
    }

    public int getId_marca() {
        return id_marca;
    }

    public void setId_marca(int id_marca) {
        this.id_marca = id_marca;
    }

    public NADriverDTO(int id_marca) {
        this.id_marca = id_marca;
    }

    public NADriverDTO(){

    }
    public NADriverDTO(int id_driver, int id_marca) {
        this.id_driver = id_driver;
        this.id_marca = id_marca;
    }
}
