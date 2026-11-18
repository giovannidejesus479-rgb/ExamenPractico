package com.sixflags.parkmanager.service;

import com.sixflags.parkmanager.dto.CompraResponse;
import com.sixflags.parkmanager.dto.DescuentoResponse;

public interface ParkService {
    DescuentoResponse configurarDescuento(String tipo, double porcentaje);
    void removerDescuento(String tipo);
    CompraResponse registrarCompra(String tipo, int cantidad);
}