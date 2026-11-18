package com.sixflags.parkmanager.dto;

public class DescuentoResponse {
    private String tipo;
    private double porcentaje;
    private String mensaje;

    public DescuentoResponse(String tipo, double porcentaje, String mensaje) {
        this.tipo = tipo;
        this.porcentaje = porcentaje;
        this.mensaje = mensaje;
    }

    public String getTipo() { return tipo; }
    public double getPorcentaje() { return porcentaje; }
    public String getMensaje() { return mensaje; }
}