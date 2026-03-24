package com.cooperativasanmartinrl.activos.controller;

import com.cooperativasanmartinrl.activos.entity.EvaluacionRiesgo;
import com.cooperativasanmartinrl.activos.repository.EvaluacionRiesgoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/evaluaciones")
@RequiredArgsConstructor
public class EvaluacionRiesgoController {

    private final EvaluacionRiesgoRepository repo;

    @GetMapping
    public ResponseEntity<List<EvaluacionRiesgo>> listar() {
        return ResponseEntity.ok(repo.findAll());
    }

    @PostMapping
    public ResponseEntity<EvaluacionRiesgo> crear(@RequestBody EvaluacionRiesgo e) {
        e.setFecha(LocalDateTime.now());
        return ResponseEntity.ok(repo.save(e));
    }
}