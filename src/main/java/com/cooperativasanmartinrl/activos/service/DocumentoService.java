package com.cooperativasanmartinrl.activos.service;

import com.cooperativasanmartinrl.activos.entity.Documento;
import java.util.List;

public interface DocumentoService {

    List<Documento> listar();

    Documento obtener(Long id);

    Documento guardar(Documento documento);

    Documento actualizar(Long id, Documento documento);

    void eliminar(Long id);
}