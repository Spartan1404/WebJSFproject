package com.example.primejsf.dto;

public class AutenUserDTO {

    private int id;
    private String password;
    private String name;
    private String privilege;
    private String token;

    public AutenUserDTO(){

    }
    public AutenUserDTO(String name, String password, String privilege, String to) {
        this.name = name;
        this.password = password;
        this.privilege = privilege;
        token = to;
    }

    public AutenUserDTO(int id, String name, String password, String privilege, String to) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.privilege = privilege;
        token = to;
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
