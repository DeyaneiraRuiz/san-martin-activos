package com.sgsi.auditoria.service;

import org.springframework.lang.NonNull;

import com.sgsi.auditoria.entity.Auditoria;
import com.sgsi.auditoria.repository.AuditoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuditoriaService {

    private final AuditoriaRepository repository;

    public List<Auditoria> findAll() {
        return repository.findAll();
    }

    public Optional<Auditoria> findById(@NonNull Integer id) {
        return repository.findById(id);
    }

    public Auditoria save(@NonNull Auditoria entity) {
        return repository.save(entity);
    }

    public void deleteById(@NonNull Integer id) {
        repository.deleteById(id);
    }
}
