package com.cooperativasanmartinrl.activos.controller;

import com.cooperativasanmartinrl.activos.entity.Control;
import com.cooperativasanmartinrl.activos.service.ControlService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/controles")
@RequiredArgsConstructor
public class ControlController {

    private final ControlService service;

    @GetMapping
    public ResponseEntity<List<Control>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Control> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtener(id));
    }

    @PostMapping
    public ResponseEntity<Control> crear(@RequestBody Control c) {
        return ResponseEntity.ok(service.guardar(c));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Control> actualizar(@PathVariable Long id, @RequestBody Control c) {
        return ResponseEntity.ok(service.actualizar(id, c));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}