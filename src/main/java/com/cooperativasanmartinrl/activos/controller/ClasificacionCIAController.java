package com.cooperativasanmartinrl.activos.controller;

import com.cooperativasanmartinrl.activos.entity.ClasificacionCIA;
import com.cooperativasanmartinrl.activos.service.ClasificacionCIAService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clasificacion-cia")
@RequiredArgsConstructor
public class ClasificacionCIAController {

    private final ClasificacionCIAService service;

    @GetMapping
    public ResponseEntity<List<ClasificacionCIA>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClasificacionCIA> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtener(id));
    }

    @PostMapping
    public ResponseEntity<ClasificacionCIA> crear(@RequestBody ClasificacionCIA c) {
        return ResponseEntity.ok(service.guardar(c));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClasificacionCIA> actualizar(@PathVariable Long id, @RequestBody ClasificacionCIA c) {
        return ResponseEntity.ok(service.actualizar(id, c));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}