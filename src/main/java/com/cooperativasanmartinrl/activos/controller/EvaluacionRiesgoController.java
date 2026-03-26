package com.cooperativasanmartinrl.activos.controller;

import com.cooperativasanmartinrl.activos.entity.EvaluacionRiesgo;
import com.cooperativasanmartinrl.activos.service.EvaluacionRiesgoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/evaluaciones-riesgo")
@RequiredArgsConstructor
public class EvaluacionRiesgoController {

    private final EvaluacionRiesgoService service;

    @GetMapping
    public ResponseEntity<List<EvaluacionRiesgo>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EvaluacionRiesgo> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtener(id));
    }

    @PostMapping
    public ResponseEntity<EvaluacionRiesgo> crear(@RequestBody EvaluacionRiesgo e) {
        return ResponseEntity.ok(service.guardar(e));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EvaluacionRiesgo> actualizar(@PathVariable Long id, @RequestBody EvaluacionRiesgo e) {
        return ResponseEntity.ok(service.actualizar(id, e));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}