package com.example.demo.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "employee")
public class Empleado implements Serializable {

    public enum EnumRoleName {
        ADMIN,
        OPERARIO;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "email", nullable = false, unique = true)
    private String correo;

    @OneToOne
    @JoinColumn(name = "profile")
    private Profile profile;

    @Column(name = "role")
    private EnumRoleName role;

    @ManyToOne
    @JoinColumn(name = "enterprise")
    private Empresa empresa;

    @OneToMany
    @JoinColumn(name = "transations")
    private List<MovimientoDinero> transations;

    @CreatedDate
    @Column(name = "createdAt")
    private Date createdAt;

    @LastModifiedDate
    @Column(name = "updatedAt")
    private Date updatedAt;

    public Empleado() {}

    public Empleado(Integer id, String correo, Profile profile, EnumRoleName role, Empresa empresa, List<MovimientoDinero> transations, Date createdAt, Date updatedAt) {
        this.id = id;
        this.correo = correo;
        this.profile = profile;
        this.role = role;
        this.empresa = empresa;
        this.transations = transations;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public EnumRoleName getRole() {
        return role;
    }

    public void setRole(EnumRoleName role) {
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdateAt() {
        return updatedAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updatedAt = updateAt;
    }

    public List<MovimientoDinero> getTransations() {
        return transations;
    }

    public void setTransations(List<MovimientoDinero> transations) {
        this.transations = transations;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "id=" + id +
                ", correo='" + correo + '\'' +
                ", profile=" + profile +
                ", role=" + role +
                ", empresa=" + empresa +
                ", transations=" + transations +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
