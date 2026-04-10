package com.sgsi.organizacion.service;

import org.springframework.lang.NonNull;

import com.sgsi.organizacion.entity.Propietario;
import com.sgsi.organizacion.repository.PropietarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PropietarioService {

    private final PropietarioRepository repository;

    public List<Propietario> findAll() {
        return repository.findAll();
    }

    public Optional<Propietario> findById(@NonNull Integer id) {
        return repository.findById(id);
    }

    public Propietario save(@NonNull Propietario entity) {
        return repository.save(entity);
    }

    public void deleteById(@NonNull Integer id) {
        repository.deleteById(id);
    }
}
