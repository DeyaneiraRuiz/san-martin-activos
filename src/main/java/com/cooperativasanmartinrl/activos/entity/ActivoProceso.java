package com.cooperativasanmartinrl.activos.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "activo_proceso")
public class ActivoProceso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "activo_id")
    private Activo activo;

    @ManyToOne
    @JoinColumn(name = "proceso_id")
    private Proceso proceso;
}