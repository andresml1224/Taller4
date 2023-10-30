package com.andresmontoya.domain;

public class Jugadores {
    private String nombre;
    private int dinero;

    public Jugadores(String nombre, int dinero) {
        this.nombre = nombre;
        this.dinero = dinero;
    }

    public String getNombre() {
        return nombre;
    }

    public int getDinero() {
        return dinero;
    }

    public void setDinero(int dinero) {
        this.dinero = dinero;
    }
}
