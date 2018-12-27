package com.example.josem.inscripcion111mil;

public class Escuela {
    private int id;
    private String nombre;
    private int provincia;

    public Escuela() {
    }

    public Escuela(int id, String nombre, int provincia) {
        this.id = id;
        this.nombre = nombre;
        this.provincia = provincia;
    }

    //Getters bRO
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getProvincia() {
        return provincia;
    }

    //Setters bRO

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setProvincia(int provincia) {
        this.provincia = provincia;
    }
    @Override
    public String toString () {
        return nombre;
    }

}
