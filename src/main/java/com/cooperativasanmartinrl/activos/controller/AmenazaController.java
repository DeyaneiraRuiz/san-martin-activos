package com.cooperativasanmartinrl.activos.controller;

import com.cooperativasanmartinrl.activos.entity.Amenaza;
import com.cooperativasanmartinrl.activos.repository.AmenazaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/amenazas")
@RequiredArgsConstructor
public class AmenazaController {

    private final AmenazaRepository repo;

    @GetMapping
    public ResponseEntity<List<Amenaza>> listar() {
        return ResponseEntity.ok(repo.findAll());
    }

    @PostMapping
    public ResponseEntity<Amenaza> crear(@RequestBody Amenaza a) {
        return ResponseEntity.ok(repo.save(a));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}