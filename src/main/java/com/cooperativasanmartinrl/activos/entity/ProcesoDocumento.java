package com.cooperativasanmartinrl.activos.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "proceso_documento")
public class ProcesoDocumento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "proceso_id")
    private Proceso proceso;

    @ManyToOne
    @JoinColumn(name = "documento_id")
    private Documento documento;
}