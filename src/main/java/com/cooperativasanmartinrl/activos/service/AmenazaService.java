package com.cooperativasanmartinrl.activos.service;

import com.cooperativasanmartinrl.activos.entity.Amenaza;
import java.util.List;

public interface AmenazaService {

    List<Amenaza> listar();

    Amenaza obtener(Long id);

    Amenaza guardar(Amenaza amenaza);

    Amenaza actualizar(Long id, Amenaza amenaza);

    void eliminar(Long id);
}