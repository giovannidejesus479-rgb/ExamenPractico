package com.sixflags.parkmanager.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class DescuentoRequest {
    @NotNull(message = "El campo porcentaje es obligatorio")
    @Min(value = 0, message = "El porcentaje debe estar entre 0 y 100")
    @Max(value = 100, message = "El porcentaje debe estar entre 0 y 100")
    private Double porcentaje;

    public Double getPorcentaje() { return porcentaje; }
    public void setPorcentaje(Double porcentaje) { this.porcentaje = porcentaje; }
}