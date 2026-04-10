package com.sgsi.incidentes.service;

import org.springframework.lang.NonNull;

import com.sgsi.incidentes.entity.IncidenteVinculo;
import com.sgsi.incidentes.repository.IncidenteVinculoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IncidenteVinculoService {

    private final IncidenteVinculoRepository repository;

    public List<IncidenteVinculo> findAll() { return repository.findAll(); }
    public Optional<IncidenteVinculo> findById(@NonNull Integer id) { return repository.findById(id); }
    public IncidenteVinculo save(@NonNull IncidenteVinculo entity) { return repository.save(entity); }
    public void deleteById(@NonNull Integer id) { repository.deleteById(id); }
}
