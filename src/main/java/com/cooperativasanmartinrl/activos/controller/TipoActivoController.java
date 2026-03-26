package com.cooperativasanmartinrl.activos.controller;

import com.cooperativasanmartinrl.activos.entity.TipoActivo;
import com.cooperativasanmartinrl.activos.service.TipoActivoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipos-activo")
@RequiredArgsConstructor
public class TipoActivoController {

    private final TipoActivoService service;

    @GetMapping
    public ResponseEntity<List<TipoActivo>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoActivo> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtener(id));
    }

    @PostMapping
    public ResponseEntity<TipoActivo> crear(@RequestBody TipoActivo t) {
        return ResponseEntity.ok(service.guardar(t));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoActivo> actualizar(@PathVariable Long id, @RequestBody TipoActivo t) {
        return ResponseEntity.ok(service.actualizar(id, t));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}