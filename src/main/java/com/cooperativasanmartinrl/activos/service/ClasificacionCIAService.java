package com.cooperativasanmartinrl.activos.service;

import com.cooperativasanmartinrl.activos.entity.ClasificacionCIA;
import java.util.List;

public interface ClasificacionCIAService {

    List<ClasificacionCIA> listar();

    ClasificacionCIA obtener(Long id);

    ClasificacionCIA guardar(ClasificacionCIA clasificacion);

    ClasificacionCIA actualizar(Long id, ClasificacionCIA clasificacion);

    void eliminar(Long id);
}