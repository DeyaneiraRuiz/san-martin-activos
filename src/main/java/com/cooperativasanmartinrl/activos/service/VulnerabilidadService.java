package com.cooperativasanmartinrl.activos.service;

import com.cooperativasanmartinrl.activos.entity.Vulnerabilidad;
import java.util.List;

public interface VulnerabilidadService {

    List<Vulnerabilidad> listar();

    Vulnerabilidad obtener(Long id);

    Vulnerabilidad guardar(Vulnerabilidad vulnerabilidad);

    Vulnerabilidad actualizar(Long id, Vulnerabilidad vulnerabilidad);

    void eliminar(Long id);
}