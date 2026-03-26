package com.cooperativasanmartinrl.activos.service;

import com.cooperativasanmartinrl.activos.entity.Control;
import java.util.List;

public interface ControlService {

    List<Control> listar();

    Control obtener(Long id);

    Control guardar(Control control);

    Control actualizar(Long id, Control control);

    void eliminar(Long id);
}