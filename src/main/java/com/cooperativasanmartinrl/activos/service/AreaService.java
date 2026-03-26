package com.cooperativasanmartinrl.activos.service;

import com.cooperativasanmartinrl.activos.entity.Area;
import java.util.List;

public interface AreaService {

    List<Area> listar();

    Area obtener(Long id);

    Area guardar(Area area);

    Area actualizar(Long id, Area area);

    void eliminar(Long id);
}