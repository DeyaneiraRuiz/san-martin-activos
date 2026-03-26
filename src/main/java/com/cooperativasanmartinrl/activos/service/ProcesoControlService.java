package com.cooperativasanmartinrl.activos.service;

import com.cooperativasanmartinrl.activos.entity.ProcesoControl;

import java.util.List;

public interface ProcesoControlService {

    List<ProcesoControl> listar();

    ProcesoControl obtener(Long id);
    ProcesoControl guardar(ProcesoControl pc);
    ProcesoControl actualizar(Long id, ProcesoControl pc);

    void eliminar(Long id);
}