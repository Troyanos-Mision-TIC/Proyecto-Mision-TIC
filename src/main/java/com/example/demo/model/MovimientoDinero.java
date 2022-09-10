package com.example.demo.model;

public class MovimientoDinero {

    private double monto;
    private String concepto;
    private Empleado usuarioEncargado;

    public MovimientoDinero() {}

    public MovimientoDinero(double monto, String concepto, Empleado usuarioEncargado) {
        this.monto = monto;
        this.concepto = concepto;
        this.usuarioEncargado = usuarioEncargado;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
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
}
