package com.sgsi.procesos.entity;

import com.sgsi.activos.entity.Activo;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "activo_proceso")
public class ActivoProceso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activo_id")
    private Activo activo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proceso_id")
    private Proceso proceso;
}
