package com.sgsi.security.service;

import org.springframework.lang.NonNull;

import com.sgsi.security.entity.Rol;
import com.sgsi.security.repository.RolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RolService {

    private final RolRepository repository;

    public List<Rol> findAll() {
        return repository.findAll();
    }

    public Optional<Rol> findById(@NonNull Integer id) {
        return repository.findById(id);
    }

    public Rol save(@NonNull Rol entity) {
        return repository.save(entity);
    }

    public void deleteById(@NonNull Integer id) {
        repository.deleteById(id);
    }
}
