package com.cooperativasanmartinrl.activos.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "control")
public class Control {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;
    private String tipo;
}