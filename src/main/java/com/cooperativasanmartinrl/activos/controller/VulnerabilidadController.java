package com.cooperativasanmartinrl.activos.controller;

import com.cooperativasanmartinrl.activos.entity.Vulnerabilidad;
import com.cooperativasanmartinrl.activos.service.VulnerabilidadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vulnerabilidades")
@RequiredArgsConstructor
public class VulnerabilidadController {

    private final VulnerabilidadService service;

    @GetMapping
    public ResponseEntity<List<Vulnerabilidad>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vulnerabilidad> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtener(id));
    }

    @PostMapping
    public ResponseEntity<Vulnerabilidad> crear(@RequestBody Vulnerabilidad v) {
        return ResponseEntity.ok(service.guardar(v));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vulnerabilidad> actualizar(@PathVariable Long id, @RequestBody Vulnerabilidad v) {
        return ResponseEntity.ok(service.actualizar(id, v));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}