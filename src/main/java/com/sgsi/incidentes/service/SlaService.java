package com.sgsi.incidentes.service;

import org.springframework.lang.NonNull;

import com.sgsi.incidentes.entity.Sla;
import com.sgsi.incidentes.repository.SlaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SlaService {

    private final SlaRepository repository;

    public List<Sla> findAll() {
        return repository.findAll();
    }

    public Optional<Sla> findById(@NonNull Integer id) {
        return repository.findById(id);
    }

    public Sla save(@NonNull Sla entity) {
        return repository.save(entity);
    }

    public void deleteById(@NonNull Integer id) {
        repository.deleteById(id);
    }
}
