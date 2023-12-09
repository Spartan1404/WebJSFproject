package com.example.primejsf.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class RouteSheetDTO implements Serializable {
    private int id;
    private double kmExit;
    private LocalDate fecha;
    private LocalTime hora;
    private int car;
    private int service;
    private int passengers;
    private double fuel;
    private double kmEnd;

    public RouteSheetDTO(){}

    public RouteSheetDTO(int id, double kmExit, LocalDate fecha, LocalTime hora, int car, int service, int passengers, double fuel, double kmEnd) {
        this.id = id;
        this.kmExit = kmExit;
        this.fecha = fecha;
        this.hora = hora;
        this.car = car;
        this.service = service;
        this.passengers = passengers;
        this.fuel = fuel;
        this.kmEnd = kmEnd;
    }

    public RouteSheetDTO(double kmExit, LocalDate fecha, LocalTime hora, int car, int service, int passengers, double fuel, double kmEnd) {
        this.kmExit = kmExit;
        this.fecha = fecha;
        this.hora = hora;
        this.car = car;
        this.service = service;
        this.passengers = passengers;
        this.fuel = fuel;
        this.kmEnd = kmEnd;
    }

    @Override
    public String toString() {
        return "routeSheetDTO{" +
                "id=" + id +
                ", kmExit=" + kmExit +
                ", fecha=" + fecha +
                ", hora=" + hora +
                ", car=" + car +
                ", service=" + service +
                ", passengers=" + passengers +
                ", fuel=" + fuel +
                ", kmEnd=" + kmEnd +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getKmExit() {
        return kmExit;
    }

    public void setKmExit(double kmExit) {
        this.kmExit = kmExit;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public int getCar() {
        return car;
    }

    public void setCar(int car) {
        this.car = car;
    }

    public int getService() {
        return service;
    }

    public void setService(int service) {
        this.service = service;
    }

    public int getPassengers() {
        return passengers;
    }

    public void setPassengers(int passengers) {
        this.passengers = passengers;
    }

    public double getFuel() {
        return fuel;
    }

    public void setFuel(double fuel) {
        this.fuel = fuel;
    }

    public double getKmEnd() {
        return kmEnd;
    }

    public void setKmEnd(double kmEnd) {
        this.kmEnd = kmEnd;
    }
}
