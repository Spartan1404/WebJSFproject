package com.example.primejsf.dto;

import java.io.Serializable;

public class UsersDTO implements Serializable {

    private int id;
    private String password;
    private String name;
    private String privilege;

    public UsersDTO(){

    }
    public UsersDTO(String name, String password, String privilege) {
        this.name = name;
        this.password = password;
        this.privilege = privilege;
    }

    public UsersDTO(int id, String name, String password, String privilege) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.privilege = privilege;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

}
