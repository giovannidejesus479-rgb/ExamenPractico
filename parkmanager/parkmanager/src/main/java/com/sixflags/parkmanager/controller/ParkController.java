package com.sixflags.parkmanager.controller;

import com.sixflags.parkmanager.dto.CompraRequest;
import com.sixflags.parkmanager.dto.CompraResponse;
import com.sixflags.parkmanager.dto.DescuentoRequest;
import com.sixflags.parkmanager.dto.DescuentoResponse;
import com.sixflags.parkmanager.service.ParkService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ParkController {

    private final ParkService parkService;

    public ParkController(ParkService parkService) {
        this.parkService = parkService;
    }

    @PutMapping("/descuentos/{tipo}")
    public ResponseEntity<DescuentoResponse> configurarDescuento(
            @PathVariable String tipo,
            @Valid @RequestBody DescuentoRequest request) {
        DescuentoResponse response = parkService.configurarDescuento(tipo, request.getPorcentaje());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/descuentos/{tipo}")
    public ResponseEntity<Void> removerDescuento(@PathVariable String tipo) {
        parkService.removerDescuento(tipo);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/compras")
    public ResponseEntity<CompraResponse> registrarCompra(@Valid @RequestBody CompraRequest request) {
        CompraResponse response = parkService.registrarCompra(request.getTipo(), request.getCantidad());
        return ResponseEntity.ok(response);
    }
}