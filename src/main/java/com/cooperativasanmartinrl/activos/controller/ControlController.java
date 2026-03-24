package com.cooperativasanmartinrl.activos.controller;

import com.cooperativasanmartinrl.activos.entity.Control;
import com.cooperativasanmartinrl.activos.repository.ControlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/controles")
@RequiredArgsConstructor
public class ControlController {

    private final ControlRepository repo;

    @GetMapping
    public ResponseEntity<List<Control>> listar() {
        return ResponseEntity.ok(repo.findAll());
    }

    @PostMapping
    public ResponseEntity<Control> crear(@RequestBody Control c) {
        return ResponseEntity.ok(repo.save(c));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Control> actualizar(@PathVariable Long id, @RequestBody Control data) {
        return repo.findById(id)
                .map(c -> {
                    c.setDescripcion(data.getDescripcion());
                    c.setTipo(data.getTipo());
                    return ResponseEntity.ok(repo.save(c));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}