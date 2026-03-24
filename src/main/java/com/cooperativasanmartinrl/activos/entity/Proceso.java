package com.cooperativasanmartinrl.activos.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "proceso")
public class Proceso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;
    private String periodicidad;
    private String nivelAutomatizacion;
    private String gradoDescentralizacion;
    private String tiempoProceso;
    private Boolean esCritico;
    private String productoServicio;
}