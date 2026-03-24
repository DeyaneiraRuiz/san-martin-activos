package com.cooperativasanmartinrl.activos.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "clasificacion_cia")
public class ClasificacionCIA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int confidencialidad;
    private int integridad;
    private int disponibilidad;
    private int criticidad;

    @OneToOne
    @JoinColumn(name = "activo_id")
    private Activo activo;
}