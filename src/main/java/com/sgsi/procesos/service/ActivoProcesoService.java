package com.sgsi.procesos.service;

import org.springframework.lang.NonNull;

import com.sgsi.procesos.entity.ActivoProceso;
import com.sgsi.procesos.repository.ActivoProcesoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ActivoProcesoService {

    private final ActivoProcesoRepository repository;

    public List<ActivoProceso> findAll() { return repository.findAll(); }
    public Optional<ActivoProceso> findById(@NonNull Integer id) { return repository.findById(id); }
    public ActivoProceso save(@NonNull ActivoProceso entity) { return repository.save(entity); }
    public void deleteById(@NonNull Integer id) { repository.deleteById(id); }
}
