package com.sgsi.riesgos.service;

import org.springframework.lang.NonNull;

import com.sgsi.riesgos.entity.TratamientoRiesgo;
import com.sgsi.riesgos.repository.TratamientoRiesgoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TratamientoRiesgoService {

    private final TratamientoRiesgoRepository repository;

    public List<TratamientoRiesgo> findAll() { return repository.findAll(); }
    public Optional<TratamientoRiesgo> findById(@NonNull Integer id) { return repository.findById(id); }
    public TratamientoRiesgo save(@NonNull TratamientoRiesgo entity) { return repository.save(entity); }
    public void deleteById(@NonNull Integer id) { repository.deleteById(id); }
}
