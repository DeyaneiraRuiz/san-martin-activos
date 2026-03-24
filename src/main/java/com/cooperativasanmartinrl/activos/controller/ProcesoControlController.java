package com.cooperativasanmartinrl.activos.controller;

import com.cooperativasanmartinrl.activos.entity.ProcesoControl;
import com.cooperativasanmartinrl.activos.repository.ProcesoControlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proceso-control")
@RequiredArgsConstructor
public class ProcesoControlController {

    private final ProcesoControlRepository repo;

    @GetMapping
    public ResponseEntity<List<ProcesoControl>> listar() {
        return ResponseEntity.ok(repo.findAll());
    }

    @PostMapping
    public ResponseEntity<ProcesoControl> crear(@RequestBody ProcesoControl pc) {
        return ResponseEntity.ok(repo.save(pc));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}