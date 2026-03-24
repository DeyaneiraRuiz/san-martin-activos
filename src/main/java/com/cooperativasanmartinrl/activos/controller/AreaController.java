package com.cooperativasanmartinrl.activos.controller;

import com.cooperativasanmartinrl.activos.entity.Area;
import com.cooperativasanmartinrl.activos.repository.AreaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/areas")
@RequiredArgsConstructor
public class AreaController {

    private final AreaRepository repo;

    @GetMapping
    public ResponseEntity<List<Area>> listar() {
        return ResponseEntity.ok(repo.findAll());
    }

    @PostMapping
    public ResponseEntity<Area> crear(@RequestBody Area area) {
        return ResponseEntity.ok(repo.save(area));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Area> actualizar(@PathVariable Long id, @RequestBody Area data) {
        return repo.findById(id)
                .map(a -> {
                    a.setNombre(data.getNombre());
                    return ResponseEntity.ok(repo.save(a));
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