package com.sgsi.procesos.service;

import org.springframework.lang.NonNull;

import com.sgsi.procesos.entity.Control;
import com.sgsi.procesos.repository.ControlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ControlService {

    private final ControlRepository repository;

    public List<Control> findAll() {
        return repository.findAll();
    }

    public Optional<Control> findById(@NonNull Integer id) {
        return repository.findById(id);
    }

    public Control save(@NonNull Control entity) {
        return repository.save(entity);
    }

    public void deleteById(@NonNull Integer id) {
        repository.deleteById(id);
    }
}
