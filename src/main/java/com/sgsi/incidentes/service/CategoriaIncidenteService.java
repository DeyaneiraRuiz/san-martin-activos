package com.sgsi.incidentes.service;

import org.springframework.lang.NonNull;

import com.sgsi.incidentes.entity.CategoriaIncidente;
import com.sgsi.incidentes.repository.CategoriaIncidenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoriaIncidenteService {

    private final CategoriaIncidenteRepository repository;

    public List<CategoriaIncidente> findAll() {
        return repository.findAll();
    }

    public Optional<CategoriaIncidente> findById(@NonNull Integer id) {
        return repository.findById(id);
    }

    public CategoriaIncidente save(@NonNull CategoriaIncidente entity) {
        return repository.save(entity);
    }

    public void deleteById(@NonNull Integer id) {
        repository.deleteById(id);
    }
}
