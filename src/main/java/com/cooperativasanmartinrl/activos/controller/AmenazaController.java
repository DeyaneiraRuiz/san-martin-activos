package com.cooperativasanmartinrl.activos.controller;

import com.cooperativasanmartinrl.activos.entity.Amenaza;
import com.cooperativasanmartinrl.activos.service.AmenazaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/amenazas")
@RequiredArgsConstructor
public class AmenazaController {

    private final AmenazaService service;

    @GetMapping
    public ResponseEntity<List<Amenaza>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Amenaza> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtener(id));
    }

    @PostMapping
    public ResponseEntity<Amenaza> crear(@RequestBody Amenaza a) {
        return ResponseEntity.ok(service.guardar(a));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Amenaza> actualizar(@PathVariable Long id, @RequestBody Amenaza a) {
        return ResponseEntity.ok(service.actualizar(id, a));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}