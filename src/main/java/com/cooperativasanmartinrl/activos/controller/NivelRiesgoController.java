package com.cooperativasanmartinrl.activos.controller;

import com.cooperativasanmartinrl.activos.entity.NivelRiesgo;
import com.cooperativasanmartinrl.activos.repository.NivelRiesgoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/niveles-riesgo")
@RequiredArgsConstructor
public class NivelRiesgoController {

    private final NivelRiesgoRepository repo;

    @GetMapping
    public ResponseEntity<List<NivelRiesgo>> listar() {
        return ResponseEntity.ok(repo.findAll());
    }

    @PostMapping
    public ResponseEntity<NivelRiesgo> crear(@RequestBody NivelRiesgo n) {
        return ResponseEntity.ok(repo.save(n));
    }
}