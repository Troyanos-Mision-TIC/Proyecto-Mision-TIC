package com.example.demo.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "employee")
public class Empleado implements Serializable {

    public enum EnumRolName {
        ADMIN,
        OPERARIO;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "name")
    private String nombre;

    @Column(name = "email", nullable = false, unique = true)
    private String correo;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Empresa empresa;

    @Column(name = "role")
    private EnumRolName rol;

    @OneToOne
    @JoinColumn(referencedColumnName = "id")
    private Profile profile;

    @OneToMany
    @JoinColumn(name = "id")
    private List<Transaction> transations;

    @Column(name = "createdAt")
    private Date createdAt;

    @Column(name = "updateAt")
    private Date updateAt;

    public Empleado() {}

    public Empleado(String nombre, String correo, Empresa empresa, EnumRolName rol, Profile profile) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.empresa = empresa;
        this.rol = rol;
        this.profile = profile;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public EnumRolName getRol() {
        return rol;
    }

    public void setRol(EnumRolName rol) {
        this.rol = rol;
    }

    public long getId() {
        return id;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public List<Transaction> getTransations() {
        return transations;
    }

    public void setTransations(List<Transaction> transations) {
        this.transations = transations;
    }

    @Override
    public String toString() {
        return "Empleado{nombre=" + nombre + ", correo=" + correo + ", empresa=" + empresa + ", rol=" + rol + "}";
    }
}
