package com.sixflags.parkmanager.dto;

public class CompraResponse {
    private String tipo;
    private int cantidad;
    private double precioBase;
    private double descuentoAplicado;
    private double precioUnitarioFinal;
    private double total;
    private double ingresosAcumulados;

    public CompraResponse(String tipo, int cantidad, double precioBase, double descuentoAplicado, 
                          double precioUnitarioFinal, double total, double ingresosAcumulados) {
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.precioBase = precioBase;
        this.descuentoAplicado = descuentoAplicado;
        this.precioUnitarioFinal = precioUnitarioFinal;
        this.total = total;
        this.ingresosAcumulados = ingresosAcumulados;
    }

    public String getTipo() { return tipo; }
    public int getCantidad() { return cantidad; }
    public double getPrecioBase() { return precioBase; }
    public double getDescuentoAplicado() { return descuentoAplicado; }
    public double getPrecioUnitarioFinal() { return precioUnitarioFinal; }
    public double getTotal() { return total; }
    public double getIngresosAcumulados() { return ingresosAcumulados; }
}