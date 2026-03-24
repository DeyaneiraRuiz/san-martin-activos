package com.cooperativasanmartinrl.activos.controller;

import com.cooperativasanmartinrl.activos.entity.Vulnerabilidad;
import com.cooperativasanmartinrl.activos.repository.VulnerabilidadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vulnerabilidades")
@RequiredArgsConstructor
public class VulnerabilidadController {

    private final VulnerabilidadRepository repo;

    @GetMapping
    public ResponseEntity<List<Vulnerabilidad>> listar() {
        return ResponseEntity.ok(repo.findAll());
    }

    @PostMapping
    public ResponseEntity<Vulnerabilidad> crear(@RequestBody Vulnerabilidad v) {
        return ResponseEntity.ok(repo.save(v));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}