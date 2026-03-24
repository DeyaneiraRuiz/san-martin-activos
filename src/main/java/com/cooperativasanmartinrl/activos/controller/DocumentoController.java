package com.cooperativasanmartinrl.activos.controller;

import com.cooperativasanmartinrl.activos.entity.Documento;
import com.cooperativasanmartinrl.activos.repository.DocumentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/documentos")
@RequiredArgsConstructor
public class DocumentoController {

    private final DocumentoRepository repo;

    @GetMapping
    public ResponseEntity<List<Documento>> listar() {
        return ResponseEntity.ok(repo.findAll());
    }

    @PostMapping
    public ResponseEntity<Documento> crear(@RequestBody Documento d) {
        return ResponseEntity.ok(repo.save(d));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Documento> actualizar(@PathVariable Long id, @RequestBody Documento data) {
        return repo.findById(id)
                .map(d -> {
                    d.setNombre(data.getNombre());
                    d.setUrl(data.getUrl());
                    return ResponseEntity.ok(repo.save(d));
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