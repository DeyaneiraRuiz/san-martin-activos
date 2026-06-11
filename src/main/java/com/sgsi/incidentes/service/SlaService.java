package com.sgsi.incidentes.service;

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

    public Optional<Sla> findById(Integer id) {
        return repository.findById(id);
    }

    public Sla crear(Sla entity) {
        return repository.save(entity);
    }

    public Sla actualizar(Sla entity) {
        return repository.save(entity);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}