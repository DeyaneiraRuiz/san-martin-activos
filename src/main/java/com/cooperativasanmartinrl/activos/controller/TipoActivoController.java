package com.cooperativasanmartinrl.activos.controller;

import com.cooperativasanmartinrl.activos.entity.TipoActivo;
import com.cooperativasanmartinrl.activos.repository.TipoActivoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipos-activo")
@RequiredArgsConstructor
public class TipoActivoController {

    private final TipoActivoRepository repo;

    @GetMapping
    public ResponseEntity<List<TipoActivo>> listar() {
        return ResponseEntity.ok(repo.findAll());
    }

    @PostMapping
    public ResponseEntity<TipoActivo> crear(@RequestBody TipoActivo t) {
        return ResponseEntity.ok(repo.save(t));
    }
}