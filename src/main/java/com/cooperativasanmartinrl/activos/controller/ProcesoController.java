package com.cooperativasanmartinrl.activos.controller;

import com.cooperativasanmartinrl.activos.entity.Proceso;
import com.cooperativasanmartinrl.activos.repository.ProcesoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/procesos")
@RequiredArgsConstructor
public class ProcesoController {

    private final ProcesoRepository repo;

    @GetMapping
    public ResponseEntity<List<Proceso>> listar() {
        return ResponseEntity.ok(repo.findAll());
    }

    @PostMapping
    public ResponseEntity<Proceso> crear(@RequestBody Proceso p) {
        return ResponseEntity.ok(repo.save(p));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Proceso> actualizar(@PathVariable Long id, @RequestBody Proceso data) {
        return repo.findById(id)
                .map(p -> {
                    p.setNombre(data.getNombre());
                    p.setDescripcion(data.getDescripcion());
                    p.setPeriodicidad(data.getPeriodicidad());
                    p.setNivelAutomatizacion(data.getNivelAutomatizacion());
                    p.setGradoDescentralizacion(data.getGradoDescentralizacion());
                    p.setTiempoProceso(data.getTiempoProceso());
                    p.setEsCritico(data.getEsCritico());
                    p.setProductoServicio(data.getProductoServicio());
                    return ResponseEntity.ok(repo.save(p));
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