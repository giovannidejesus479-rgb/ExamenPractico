package com.sixflags.parkmanager.service;

import com.sixflags.parkmanager.dto.CompraResponse;
import com.sixflags.parkmanager.dto.DescuentoResponse;
import com.sixflags.parkmanager.exception.BusinessException;
import com.sixflags.parkmanager.model.*;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

@Service
public class ParkServiceImpl implements ParkService {

    // Registro polimórfico de fábricas de boletos para evitar switch / if-else / instanceof
    private static final Map<String, Supplier<Boleto>> BOLETO_FACTORIES = Map.of(
        "GENERAL", BoletoGeneral::new,
        "PASE_ANUAL", BoletoPaseAnual::new,
        "ALUMNO", BoletoAlumno::new,
        "PROFESOR", BoletoProfesor::new
    );

    // Almacenamiento concurrente de descuentos e ingresos acumulados
    private final Map<String, Double> descuentos = new ConcurrentHashMap<>();
    private double ingresosAcumulados = 0.0;

    private void validarTipoBoleto(String tipo) {
        if (tipo == null || !BOLETO_FACTORIES.containsKey(tipo)) {
            throw new BusinessException("TIPO_BOLETO_INVALIDO", "El tipo de boleto especificado no existe");
        }
    }

    @Override
    public DescuentoResponse configurarDescuento(String tipo, double porcentaje) {
        validarTipoBoleto(tipo);
        if (porcentaje < 0 || porcentaje > 100) {
            throw new BusinessException("DESCUENTO_INVALIDO", "El porcentaje debe estar entre 0 y 100");
        }
        descuentos.put(tipo, porcentaje);
        return new DescuentoResponse(tipo, porcentaje, "Descuento configurado");
    }

    @Override
    public void removerDescuento(String tipo) {
        validarTipoBoleto(tipo);
        descuentos.remove(tipo); // Operación idempotente
    }

    @Override
    public synchronized CompraResponse registrarCompra(String tipo, int cantidad) {
        validarTipoBoleto(tipo);

        if (cantidad < 1 || cantidad > 10) {
            throw new BusinessException("CANTIDAD_INVALIDA", "La cantidad de boletos debe estar entre 1 y 10");
        }

        // Obtención polimórfica del boleto y sus atributos base
        Boleto boleto = BOLETO_FACTORIES.get(tipo).get();
        double precioBase = boleto.getPrecioBase();
        double descuentoAplicado = descuentos.getOrDefault(tipo, 0.0);

        // Cálculo financiero con precisión de centavos
        double precioUnitarioFinal = Math.round((precioBase * (1 - (descuentoAplicado / 100.0))) * 100.0) / 100.0;
        double total = Math.round((precioUnitarioFinal * cantidad) * 100.0) / 100.0;

        // Actualización atómica de ingresos acumulados
        ingresosAcumulados = Math.round((ingresosAcumulados + total) * 100.0) / 100.0;

        return new CompraResponse(
            tipo,
            cantidad,
            precioBase,
            descuentoAplicado,
            precioUnitarioFinal,
            total,
            ingresosAcumulados
        );
    }
}