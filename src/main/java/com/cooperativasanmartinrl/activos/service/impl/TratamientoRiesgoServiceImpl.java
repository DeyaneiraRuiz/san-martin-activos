package com.cooperativasanmartinrl.activos.service.impl;

import com.cooperativasanmartinrl.activos.entity.TratamientoRiesgo;
import com.cooperativasanmartinrl.activos.repository.TratamientoRiesgoRepository;
import com.cooperativasanmartinrl.activos.service.TratamientoRiesgoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TratamientoRiesgoServiceImpl implements TratamientoRiesgoService {

    private final TratamientoRiesgoRepository repo;

    public List<TratamientoRiesgo> listar() { return repo.findAll(); }

    public TratamientoRiesgo obtener(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Tratamiento no encontrado"));
    }

    public TratamientoRiesgo guardar(TratamientoRiesgo t) { return repo.save(t); }

    public TratamientoRiesgo actualizar(Long id, TratamientoRiesgo data) {
        TratamientoRiesgo t = obtener(id);
        t.setDescripcion(data.getDescripcion());
        return repo.save(t);
    }

    public void eliminar(Long id) { repo.deleteById(id); }
}