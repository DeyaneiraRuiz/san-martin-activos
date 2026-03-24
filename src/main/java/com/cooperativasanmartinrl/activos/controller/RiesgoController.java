package com.cooperativasanmartinrl.activos.controller;

import com.cooperativasanmartinrl.activos.entity.Riesgo;
import com.cooperativasanmartinrl.activos.repository.RiesgoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/riesgos")
@RequiredArgsConstructor
public class RiesgoController {

    private final RiesgoRepository repo;

    @GetMapping
    public ResponseEntity<List<Riesgo>> listar() {
        return ResponseEntity.ok(repo.findAll());
    }

    @PostMapping
    public ResponseEntity<Riesgo> crear(@RequestBody Riesgo r) {
        return ResponseEntity.ok(repo.save(r));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}