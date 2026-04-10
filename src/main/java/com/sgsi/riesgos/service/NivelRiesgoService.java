package com.sgsi.riesgos.service;

import org.springframework.lang.NonNull;

import com.sgsi.riesgos.entity.NivelRiesgo;
import com.sgsi.riesgos.repository.NivelRiesgoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NivelRiesgoService {

    private final NivelRiesgoRepository repository;

    public List<NivelRiesgo> findAll() { return repository.findAll(); }
    public Optional<NivelRiesgo> findById(@NonNull Integer id) { return repository.findById(id); }
    public NivelRiesgo save(@NonNull NivelRiesgo entity) { return repository.save(entity); }
    public void deleteById(@NonNull Integer id) { repository.deleteById(id); }
}
