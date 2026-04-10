package com.sgsi.riesgos.service;

import org.springframework.lang.NonNull;

import com.sgsi.riesgos.entity.Riesgo;
import com.sgsi.riesgos.repository.RiesgoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RiesgoService {

    private final RiesgoRepository repository;

    public List<Riesgo> findAll() {
        return repository.findAll();
    }

    public Optional<Riesgo> findById(@NonNull Integer id) {
        return repository.findById(id);
    }

    public Riesgo save(@NonNull Riesgo entity) {
        return repository.save(entity);
    }

    public void deleteById(@NonNull Integer id) {
        repository.deleteById(id);
    }
}
