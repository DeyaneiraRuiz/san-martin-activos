package com.sgsi.riesgos.service;

import org.springframework.lang.NonNull;

import com.sgsi.riesgos.entity.EvaluacionRiesgo;
import com.sgsi.riesgos.repository.EvaluacionRiesgoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EvaluacionRiesgoService {

    private final EvaluacionRiesgoRepository repository;

    public List<EvaluacionRiesgo> findAll() { return repository.findAll(); }
    public Optional<EvaluacionRiesgo> findById(@NonNull Integer id) { return repository.findById(id); }
    public EvaluacionRiesgo save(@NonNull EvaluacionRiesgo entity) { return repository.save(entity); }
    public void deleteById(@NonNull Integer id) { repository.deleteById(id); }
}
