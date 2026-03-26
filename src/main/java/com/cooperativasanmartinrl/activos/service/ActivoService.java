package com.cooperativasanmartinrl.activos.service;

import com.cooperativasanmartinrl.activos.entity.Activo;
import java.util.List;

public interface ActivoService {

    List<Activo> listar();

    Activo obtener(Long id);

    Activo guardar(Activo activo);

    Activo actualizar(Long id, Activo activo);

    void eliminar(Long id);
}