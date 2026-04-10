package com.sgsi.procesos.service;

import org.springframework.lang.NonNull;

import com.sgsi.procesos.entity.ProcesoControl;
import com.sgsi.procesos.repository.ProcesoControlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProcesoControlService {

    private final ProcesoControlRepository repository;

    public List<ProcesoControl> findAll() { return repository.findAll(); }
    public Optional<ProcesoControl> findById(@NonNull Integer id) { return repository.findById(id); }
    public ProcesoControl save(@NonNull ProcesoControl entity) { return repository.save(entity); }
    public void deleteById(@NonNull Integer id) { repository.deleteById(id); }
}
