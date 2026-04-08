package com.sgsi.procesos.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "control")
public class Control {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "codigo_iso", length = 30)
    private String codigoIso;

    @Column(length = 150)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;
}
