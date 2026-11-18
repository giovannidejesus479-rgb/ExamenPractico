package com.sixflags.parkmanager.model;

public abstract class Boleto {
    protected String tipo;
    protected double precioBase;

    public Boleto(String tipo, double precioBase) {
        this.tipo = tipo;
        this.precioBase = precioBase;
    }

    public String getTipo() {
        return tipo;
    }

    public double getPrecioBase() {
        return precioBase;
    }
}