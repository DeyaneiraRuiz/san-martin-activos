package com.sgsi.incidentes.service;

import org.springframework.lang.NonNull;

import com.sgsi.incidentes.entity.Incidente;
import com.sgsi.incidentes.repository.IncidenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IncidenteService {

    private final IncidenteRepository repository;

    public List<Incidente> findAll() {
        return repository.findAll();
    }

    public Optional<Incidente> findById(@NonNull Integer id) {
        return repository.findById(id);
    }

    public Incidente save(@NonNull Incidente entity) {
        return repository.save(entity);
    }

    public void deleteById(@NonNull Integer id) {
        repository.deleteById(id);
    }
}
