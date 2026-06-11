package com.sgsi.incidentes.entity;

import com.sgsi.organizacion.entity.Grupo;
import com.sgsi.security.entity.Usuario;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "categoria_incidente")
public class CategoriaIncidente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 30, unique = true)
    private String codigo;

    @Column(length = 100)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(length = 30)
    private String estado = "activo";

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "responsable_id")
    private Usuario responsable;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "grupo_id")
    private Grupo grupo;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;
}
