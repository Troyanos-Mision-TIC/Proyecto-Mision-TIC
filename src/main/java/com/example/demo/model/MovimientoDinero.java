package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import javax.persistence.*;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "transactions")
@EntityListeners(AuditingEntityListener.class)
public class MovimientoDinero implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "amount", nullable = false)
    private float monto;

    @Column(name = "concept")
    private String concepto;

    @JsonIgnoreProperties(value = {"transacciones", "empresa"})
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Empleado usuarioEncargado;

    @JsonIncludeProperties(value = {"id", "nombre", "nit"})
    @ManyToOne
    @JoinColumn(name = "enterprise")
    private Empresa empresa;

    @CreatedDate
    @Column(name = "created_at")
    @Temporal(TemporalType.DATE)
    private Date createdDate;

    @LastModifiedDate
    @Column(name = "modified_at")
    @Temporal(TemporalType.DATE)
    private Date lastModifiedDate;
    
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

    public Empresa getEmpresa() {
        return empresa;
    }
    
    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
