package com.sgsi.procesos.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "proceso_documento")
public class ProcesoDocumento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proceso_id")
    private Proceso proceso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "documento_id")
    private Documento documento;
}
