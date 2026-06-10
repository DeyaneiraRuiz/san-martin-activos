package com.sgsi.incidentes.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tipo_incidente")
public class TipoIncidente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 30)
    private String codigo;

    @Column(length = 150)
    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id")
    private CategoriaIncidente categoria;

    @Column(length = 30)
    private String estado = "activo";

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private LocalDateTime updatedAt;

    @Column(name = "created_by", length = 100)
    private String creadoPor;

    @Column(name = "updated_by", length = 100)
    private String modificadoPor;
}