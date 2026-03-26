package com.cooperativasanmartinrl.activos.service;

import com.cooperativasanmartinrl.activos.entity.ActivoProceso;

import java.util.List;

public interface ActivoProcesoService {

    List<ActivoProceso> listar();

    ActivoProceso obtener(Long id);
    ActivoProceso guardar(ActivoProceso pd);
    ActivoProceso actualizar(Long id, ActivoProceso pd);

    void eliminar(Long id);
}