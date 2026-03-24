package com.cooperativasanmartinrl.activos.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "proceso_control")
public class ProcesoControl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "proceso_id")
    private Proceso proceso;

    @ManyToOne
    @JoinColumn(name = "control_id")
    private Control control;
}