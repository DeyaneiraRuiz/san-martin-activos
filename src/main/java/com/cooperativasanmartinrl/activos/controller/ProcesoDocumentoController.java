package com.cooperativasanmartinrl.activos.controller;

import com.cooperativasanmartinrl.activos.entity.ProcesoDocumento;  // Ajusta el paquete si es diferente
import com.cooperativasanmartinrl.activos.service.ProcesoDocumentoService;  // Ajusta el paquete si es diferente
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proceso-documento")
@RequiredArgsConstructor
public class ProcesoDocumentoController {

    private final ProcesoDocumentoService service;

    @GetMapping
    public ResponseEntity<List<ProcesoDocumento>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @PostMapping
    public ResponseEntity<ProcesoDocumento> crear(@RequestBody ProcesoDocumento pd) {
        return ResponseEntity.ok(service.guardar(pd));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}