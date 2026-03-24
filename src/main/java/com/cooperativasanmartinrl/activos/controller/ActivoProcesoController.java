package com.cooperativasanmartinrl.activos.controller;

import com.cooperativasanmartinrl.activos.entity.ActivoProceso;
import com.cooperativasanmartinrl.activos.repository.ActivoProcesoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activo-proceso")
@RequiredArgsConstructor
public class ActivoProcesoController {

    private final ActivoProcesoRepository repo;

    @GetMapping
    public ResponseEntity<List<ActivoProceso>> listar() {
        return ResponseEntity.ok(repo.findAll());
    }

    @PostMapping
    public ResponseEntity<ActivoProceso> crear(@RequestBody ActivoProceso ap) {
        return ResponseEntity.ok(repo.save(ap));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}