package com.cooperativasanmartinrl.activos.controller;

import com.cooperativasanmartinrl.activos.entity.Activo;
import com.cooperativasanmartinrl.activos.repository.ActivoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/activos")
@RequiredArgsConstructor
public class ActivoController {

    private final ActivoRepository repo;

    @GetMapping
    public ResponseEntity<List<Activo>> listar() {
        return ResponseEntity.ok(repo.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Activo> obtener(@PathVariable Long id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Activo> crear(@RequestBody Activo activo) {
        activo.setCreatedAt(LocalDateTime.now());
        return ResponseEntity.ok(repo.save(activo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Activo> actualizar(@PathVariable Long id, @RequestBody Activo data) {
        return repo.findById(id)
                .map(a -> {
                    a.setNombre(data.getNombre());
                    a.setDescripcion(data.getDescripcion());
                    a.setMedio(data.getMedio());
                    a.setOrigen(data.getOrigen());
                    a.setTipoActivo(data.getTipoActivo());
                    a.setArea(data.getArea());
                    a.setPropietario(data.getPropietario());
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