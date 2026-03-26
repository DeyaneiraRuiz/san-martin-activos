package com.cooperativasanmartinrl.activos.service.impl;

import com.cooperativasanmartinrl.activos.entity.Control;
import com.cooperativasanmartinrl.activos.repository.ControlRepository;
import com.cooperativasanmartinrl.activos.service.ControlService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ControlServiceImpl implements ControlService {

    private final ControlRepository repo;

    @Override
    public List<Control> listar() {
        return repo.findAll();
    }

    @Override
    public Control obtener(Long id) {
        return repo.findById(id)
            .orElseThrow(() -> new RuntimeException("Control no encontrado"));
    }

    @Override
    public Control guardar(Control c) {
        return repo.save(c);
    }

    @Override
    public Control actualizar(Long id, Control data) {
        Control c = obtener(id);
        c.setDescripcion(data.getDescripcion());
        c.setTipo(data.getTipo());
        return repo.save(c);
    }

    @Override
    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}