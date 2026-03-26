package com.cooperativasanmartinrl.activos.controller;

import com.cooperativasanmartinrl.activos.entity.ActivoProceso;  // Ajusta el paquete si es diferente
import com.cooperativasanmartinrl.activos.service.ActivoProcesoService;  // Ajusta el paquete si es diferente
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activo-proceso")
@RequiredArgsConstructor
public class ActivoProcesoController {

    private final ActivoProcesoService service;

    @GetMapping
    public ResponseEntity<List<ActivoProceso>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @PostMapping
    public ResponseEntity<ActivoProceso> crear(@RequestBody ActivoProceso ap) {
        return ResponseEntity.ok(service.guardar(ap));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}