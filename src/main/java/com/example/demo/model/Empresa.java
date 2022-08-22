package com.example.demo.model;

public class Empresa {
    private String nombre;
    private String direccion;
    private long telefono;
    private long NIT;

    public Empresa(String nombre, String direccion, long teléfono, long NIT) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.NIT = NIT;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    public long getNIT() {
        return NIT;
    }

    public void setNIT(long NIT) {
        this.NIT = NIT;
    }
}
