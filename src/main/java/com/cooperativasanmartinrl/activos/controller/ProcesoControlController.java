package com.cooperativasanmartinrl.activos.controller;

import com.cooperativasanmartinrl.activos.entity.ProcesoControl;  // Ajusta el paquete si es diferente
import com.cooperativasanmartinrl.activos.service.ProcesoControlService;  // Ajusta el paquete si es diferente
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proceso-control")
@RequiredArgsConstructor
public class ProcesoControlController {

    private final ProcesoControlService service;

    @GetMapping
    public ResponseEntity<List<ProcesoControl>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @PostMapping
    public ResponseEntity<ProcesoControl> crear(@RequestBody ProcesoControl pc) {
        return ResponseEntity.ok(service.guardar(pc));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}