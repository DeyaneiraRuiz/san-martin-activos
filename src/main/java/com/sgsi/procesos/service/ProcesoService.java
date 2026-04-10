package com.sgsi.procesos.service;

import org.springframework.lang.NonNull;

import com.sgsi.procesos.entity.Proceso;
import com.sgsi.procesos.repository.ProcesoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProcesoService {

    private final ProcesoRepository repository;

    public List<Proceso> findAll() {
        return repository.findAll();
    }

    public Optional<Proceso> findById(@NonNull Integer id) {
        return repository.findById(id);
    }

    public Proceso save(@NonNull Proceso entity) {
        return repository.save(entity);
    }

    public void deleteById(@NonNull Integer id) {
        repository.deleteById(id);
    }
}
