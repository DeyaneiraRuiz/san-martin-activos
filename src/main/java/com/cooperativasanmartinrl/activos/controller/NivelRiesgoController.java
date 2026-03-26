package com.cooperativasanmartinrl.activos.controller;

import com.cooperativasanmartinrl.activos.entity.NivelRiesgo;
import com.cooperativasanmartinrl.activos.service.NivelRiesgoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/niveles-riesgo")
@RequiredArgsConstructor
public class NivelRiesgoController {

    private final NivelRiesgoService service;

    @GetMapping
    public ResponseEntity<List<NivelRiesgo>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NivelRiesgo> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtener(id));
    }

    @PostMapping
    public ResponseEntity<NivelRiesgo> crear(@RequestBody NivelRiesgo n) {
        return ResponseEntity.ok(service.guardar(n));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NivelRiesgo> actualizar(@PathVariable Long id, @RequestBody NivelRiesgo n) {
        return ResponseEntity.ok(service.actualizar(id, n));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}