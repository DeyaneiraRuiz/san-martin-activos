package com.sgsi.incidentes.entity;

import com.sgsi.organizacion.entity.Grupo;
import com.sgsi.security.entity.Usuario;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "categoria_incidente")
public class CategoriaIncidente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100)
    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "responsable_id")
    private Usuario responsable;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "grupo_id")
    private Grupo grupo;
}
