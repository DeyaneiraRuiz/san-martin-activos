package com.sgsi.incidentes.entity;

import com.sgsi.security.entity.Usuario;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
@Table(name = "incidente_seguimiento")
public class IncidenteSeguimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "incidente_id", unique = true)
    private Incidente incidente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asignado_a_id")
    private Usuario asignadoA;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asignado_por_id")
    private Usuario asignadoPor;

    @Column(name = "fecha_asignacion")
    private LocalDateTime fechaAsignacion;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "incidente_seguimiento_observador",
            joinColumns = @JoinColumn(name = "seguimiento_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    private Set<Usuario> observadores;
}