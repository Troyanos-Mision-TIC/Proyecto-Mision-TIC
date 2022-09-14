package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "enterprises")
public class Empresa implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name", unique = true)
    private String nombre;

    @Column(name = "address")
    private String direccion;

    @Column(name = "phone")
    private String telefono;

    @Column(name = "document", unique = true)
    private String nit;
    
    @JsonIgnoreProperties(value = "empresa")
    @OneToMany
    @JoinColumn(name = "enterprise")
    private Set<Empleado> empleados;

    public Empresa() {}

    public Empresa(String nombre, String direccion, String telefono, String nit) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.nit = nit;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public Set<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(Set<Empleado> empleados) {
        this.empleados = empleados;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
