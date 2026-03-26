package com.cooperativasanmartinrl.activos.controller;

import com.cooperativasanmartinrl.activos.entity.Riesgo;
import com.cooperativasanmartinrl.activos.service.RiesgoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/riesgos")
@RequiredArgsConstructor
public class RiesgoController {

    private final RiesgoService service;

    @GetMapping
    public ResponseEntity<List<Riesgo>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Riesgo> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtener(id));
    }

    @PostMapping
    public ResponseEntity<Riesgo> crear(@RequestBody Riesgo r) {
        return ResponseEntity.ok(service.guardar(r));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Riesgo> actualizar(@PathVariable Long id, @RequestBody Riesgo r) {
        return ResponseEntity.ok(service.actualizar(id, r));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}