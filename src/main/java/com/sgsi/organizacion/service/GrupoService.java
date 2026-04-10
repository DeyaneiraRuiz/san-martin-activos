package com.sgsi.organizacion.service;

import org.springframework.lang.NonNull;

import com.sgsi.organizacion.entity.Grupo;
import com.sgsi.organizacion.repository.GrupoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GrupoService {

    private final GrupoRepository repository;

    public List<Grupo> findAll() {
        return repository.findAll();
    }

    public Optional<Grupo> findById(@NonNull Integer id) {
        return repository.findById(id);
    }

    public Grupo save(@NonNull Grupo entity) {
        return repository.save(entity);
    }

    public void deleteById(@NonNull Integer id) {
        repository.deleteById(id);
    }
}
