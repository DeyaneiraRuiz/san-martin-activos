package com.cooperativasanmartinrl.activos.controller;

import com.cooperativasanmartinrl.activos.entity.ProcesoDocumento;
import com.cooperativasanmartinrl.activos.repository.ProcesoDocumentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proceso-documento")
@RequiredArgsConstructor
public class ProcesoDocumentoController {

    private final ProcesoDocumentoRepository repo;

    @GetMapping
    public ResponseEntity<List<ProcesoDocumento>> listar() {
        return ResponseEntity.ok(repo.findAll());
    }

    @PostMapping
    public ResponseEntity<ProcesoDocumento> crear(@RequestBody ProcesoDocumento pd) {
        return ResponseEntity.ok(repo.save(pd));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}