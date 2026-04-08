package com.sgsi.procesos.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "proceso_control")
public class ProcesoControl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proceso_id")
    private Proceso proceso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "control_id")
    private Control control;
}
