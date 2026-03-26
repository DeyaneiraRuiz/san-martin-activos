package com.cooperativasanmartinrl.activos.service;

import com.cooperativasanmartinrl.activos.entity.TipoActivo;
import java.util.List;

public interface TipoActivoService {

    List<TipoActivo> listar();

    TipoActivo obtener(Long id);

    TipoActivo guardar(TipoActivo tipoActivo);

    TipoActivo actualizar(Long id, TipoActivo tipoActivo);

    void eliminar(Long id);
}