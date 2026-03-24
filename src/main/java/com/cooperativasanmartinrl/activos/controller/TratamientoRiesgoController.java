package com.cooperativasanmartinrl.activos.controller;

import com.cooperativasanmartinrl.activos.entity.TratamientoRiesgo;
import com.cooperativasanmartinrl.activos.repository.TratamientoRiesgoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tratamientos")
@RequiredArgsConstructor
public class TratamientoRiesgoController {

    private final TratamientoRiesgoRepository repo;

    @GetMapping
    public ResponseEntity<List<TratamientoRiesgo>> listar() {
        return ResponseEntity.ok(repo.findAll());
    }

    @PostMapping
    public ResponseEntity<TratamientoRiesgo> crear(@RequestBody TratamientoRiesgo t) {
        return ResponseEntity.ok(repo.save(t));
    }
}