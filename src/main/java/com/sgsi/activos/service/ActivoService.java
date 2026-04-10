package com.sgsi.activos.service;

import org.springframework.lang.NonNull;

import com.sgsi.activos.entity.Activo;
import com.sgsi.activos.repository.ActivoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ActivoService {

    private final ActivoRepository repository;

    public List<Activo> findAll() {
        return repository.findAll();
    }

    public Optional<Activo> findById(@NonNull Integer id) {
        return repository.findById(id);
    }

    public Activo save(@NonNull Activo entity) {
        return repository.save(entity);
    }

    public void deleteById(@NonNull Integer id) {
        repository.deleteById(id);
    }
}
