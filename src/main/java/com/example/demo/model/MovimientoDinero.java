package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "transactions")
public class MovimientoDinero implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "amount", nullable = false)
    private float monto;

    @Column(name = "concept")
    private String concepto;

    @JsonIgnoreProperties(value = {"transacciones"})
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Empleado usuarioEncargado;

    public MovimientoDinero() {}

    public MovimientoDinero(float monto, String concepto, Empleado usuarioEncargado) {
        this.monto = monto;
        this.concepto = concepto;
        this.usuarioEncargado = usuarioEncargado;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public Empleado getUsuarioEncargado() {
        return usuarioEncargado;
    }

    public void setUsuarioEncargado(Empleado usuarioEncargado) {
        this.usuarioEncargado = usuarioEncargado;
    }

    @Override
    public String toString() {
        return "Movimiento{monto=" + monto + ", concepto=" + concepto + ", empleado=" + usuarioEncargado+ "}";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
