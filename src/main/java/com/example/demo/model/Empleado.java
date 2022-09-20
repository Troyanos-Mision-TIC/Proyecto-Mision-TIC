package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.annotations.TypeDef;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.example.demo.PsqlEnum;
import com.example.demo.Role;
import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@TypeDef(name = "pgsql_enum", typeClass = PsqlEnum.class)
@Entity
@Table(name = "employees")
@EntityListeners(AuditingEntityListener.class)
public class Empleado implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name", nullable = false)
    private String nombre;

    @Column(name = "email", unique = true)
    private String correo;

    @JsonIgnoreProperties(value = {"empleados"})
    @ManyToOne
    @JoinColumn(name = "enterprise", nullable = false)
    private Empresa empresa;
    
    @org.hibernate.annotations.Type(type = "pgsql_enum")
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role rol;

    @JsonIgnoreProperties(value = {"usuarioEncargado"})
    @OneToMany
    @JoinColumn(name = "user_id")
    private Set<MovimientoDinero> transacciones;

    @CreatedDate
    @Column(name = "created_at")
    @Temporal(TemporalType.DATE)
    private Date createdTime;

    @LastModifiedDate
    @Column(name = "modified_at")
    @Temporal(TemporalType.DATE)
    private Date lastModifiedTime;

    public Empleado() {}

    public Empleado(String nombre, String correo, Empresa empresa, Role rol) {
        this.nombre = nombre;
        this.correo = correo;
        this.empresa = empresa;
        this.rol = rol;
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
    
    public Role getRol() {
        return rol;
    }
    
    public void setRol(Role rol) {
        this.rol = rol;
    }
    
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<MovimientoDinero> getTransacciones() {
        return transacciones;
    }

    public void setTransacciones(Set<MovimientoDinero> transacciones) {
        this.transacciones = transacciones;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public Date getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(Date lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    @Override
    public String toString() {
        return "Empleado{nombre=" + nombre + ", correo=" + correo + ", empresa=" + empresa + ", rol=" + rol + "}";
    }
}
