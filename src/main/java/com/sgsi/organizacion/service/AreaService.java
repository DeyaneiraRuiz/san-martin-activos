package com.sgsi.organizacion.service;

import org.springframework.lang.NonNull;

import com.sgsi.organizacion.entity.Area;
import com.sgsi.organizacion.repository.AreaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AreaService {

    private final AreaRepository repository;

    public List<Area> findAll() {
        return repository.findAll();
    }

    public Optional<Area> findById(@NonNull Integer id) {
        return repository.findById(id);
    }

    public Area save(@NonNull Area entity) {
        return repository.save(entity);
    }

    public void deleteById(@NonNull Integer id) {
        repository.deleteById(id);
    }
}
