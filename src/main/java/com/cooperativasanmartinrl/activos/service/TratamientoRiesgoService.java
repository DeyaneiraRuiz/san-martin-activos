package com.cooperativasanmartinrl.activos.service;

import com.cooperativasanmartinrl.activos.entity.TratamientoRiesgo;
import java.util.List;

public interface TratamientoRiesgoService {

    List<TratamientoRiesgo> listar();

    TratamientoRiesgo obtener(Long id);

    TratamientoRiesgo guardar(TratamientoRiesgo tratamiento);

    TratamientoRiesgo actualizar(Long id, TratamientoRiesgo tratamiento);

    void eliminar(Long id);
}