package com.sgsi.core;

import com.sgsi.catalogos.entity.*;
import com.sgsi.catalogos.repository.*;
import com.sgsi.organizacion.entity.Area;
import com.sgsi.organizacion.repository.AreaRepository;
import com.sgsi.riesgos.entity.Amenaza;
import com.sgsi.riesgos.entity.Vulnerabilidad;
import com.sgsi.riesgos.repository.AmenazaRepository;
import com.sgsi.riesgos.repository.VulnerabilidadRepository;
import com.sgsi.security.entity.Rol;
import com.sgsi.security.entity.Usuario;
import com.sgsi.security.repository.RolRepository;
import com.sgsi.security.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Order(1)
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final RolRepository rolRepository;
    private final UsuarioRepository usuarioRepository;
    private final CatTipoActivoRepository catTipoActivoRepository;
    private final CatEstadoActivoRepository catEstadoActivoRepository;
    private final CatEstadoIncidenteRepository catEstadoIncidenteRepository;
    private final CatTipoTicketRepository catTipoTicketRepository;
    private final CatPeriodicidadRepository catPeriodicidadRepository;
    private final CatNivelAutomatizacionRepository catNivelAutomatizacionRepository;
    private final AmenazaRepository amenazaRepository;
    private final VulnerabilidadRepository vulnerabilidadRepository;
    private final AreaRepository areaRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        seedRoles();
        seedAdminUser();
        seedCatTiposActivo();
        seedCatEstadosActivo();
        seedCatEstadosIncidente();
        seedCatTiposTicket();
        seedCatPeriodicidades();
        seedCatNivelesAutomatizacion();
        seedAmenazas();
        seedVulnerabilidades();
        seedAreasPorDefecto();
    }

    private void seedRoles() {
        List<String[]> roles = List.of(
            new String[]{"ADMINISTRADOR", "Administrador del sistema con acceso total"},
            new String[]{"RESPONSABLE_AREA", "Responsable de área con acceso a gestión de activos"},
            new String[]{"ANALISTA_SEGURIDAD", "Analista de seguridad con acceso a riesgos e incidentes"},
            new String[]{"TECNICO", "Técnico con acceso a gestión de incidentes"},
            new String[]{"USUARIO", "Usuario básico con acceso de lectura"}
        );
        for (String[] r : roles) {
            if (rolRepository.findByNombre(r[0]).isEmpty()) {
                Rol rol = new Rol();
                rol.setNombre(r[0]);
                rol.setDescripcion(r[1]);
                rolRepository.save(rol);
            }
        }
    }

    private void seedAdminUser() {
        if (!usuarioRepository.existsByUsername("admin")) {
            Rol rolAdmin = rolRepository.findByNombre("ADMINISTRADOR").orElseThrow();
            Usuario admin = new Usuario();
            admin.setUsername("admin");
            admin.setEmail("admin@sgsi.local");
            admin.setNombreCompleto("Administrador del Sistema");
            admin.setPasswordHash(passwordEncoder.encode("Admin1234!"));
            admin.setRol(rolAdmin);
            admin.setActivo(true);
            usuarioRepository.save(admin);
        }
    }

    private void seedCatTiposActivo() {
        List<String> tipos = List.of(
            "Hardware", "Software", "Servicios", "Datos", "Personas", "Instalaciones", "Redes"
        );
        for (String nombre : tipos) {
            if (catTipoActivoRepository.findAll().stream().noneMatch(e -> e.getNombre().equals(nombre))) {
                CatTipoActivo e = new CatTipoActivo();
                e.setNombre(nombre);
                catTipoActivoRepository.save(e);
            }
        }
    }

    private void seedCatEstadosActivo() {
        List<String> estados = List.of("Activo", "Inactivo", "En mantenimiento", "Dado de baja");
        for (String nombre : estados) {
            if (catEstadoActivoRepository.findAll().stream().noneMatch(e -> e.getNombre().equals(nombre))) {
                CatEstadoActivo e = new CatEstadoActivo();
                e.setNombre(nombre);
                catEstadoActivoRepository.save(e);
            }
        }
    }

    private void seedCatEstadosIncidente() {
        List<String> estados = List.of("Abierto", "En proceso", "Resuelto", "Cerrado", "Cancelado");
        for (String nombre : estados) {
            if (catEstadoIncidenteRepository.findAll().stream().noneMatch(e -> e.getNombre().equals(nombre))) {
                CatEstadoIncidente e = new CatEstadoIncidente();
                e.setNombre(nombre);
                catEstadoIncidenteRepository.save(e);
            }
        }
    }

    private void seedCatTiposTicket() {
        List<String> tipos = List.of("Incidente", "Solicitud de servicio", "Cambio", "Problema");
        for (String nombre : tipos) {
            if (catTipoTicketRepository.findAll().stream().noneMatch(e -> e.getNombre().equals(nombre))) {
                CatTipoTicket e = new CatTipoTicket();
                e.setNombre(nombre);
                catTipoTicketRepository.save(e);
            }
        }
    }

    private void seedCatPeriodicidades() {
        List<Object[]> periodicidades = List.of(
            new Object[]{"Diaria", 1},
            new Object[]{"Semanal", 7},
            new Object[]{"Mensual", 30},
            new Object[]{"Trimestral", 90},
            new Object[]{"Semestral", 180},
            new Object[]{"Anual", 365}
        );
        for (Object[] p : periodicidades) {
            String nombre = (String) p[0];
            if (catPeriodicidadRepository.findAll().stream().noneMatch(e -> e.getNombre().equals(nombre))) {
                CatPeriodicidad e = new CatPeriodicidad();
                e.setNombre(nombre);
                e.setDias((Integer) p[1]);
                catPeriodicidadRepository.save(e);
            }
        }
    }

    private void seedCatNivelesAutomatizacion() {
        List<String> niveles = List.of("Manual", "Semi-automatizado", "Automatizado");
        for (String nombre : niveles) {
            if (catNivelAutomatizacionRepository.findAll().stream().noneMatch(e -> e.getNombre().equals(nombre))) {
                CatNivelAutomatizacion e = new CatNivelAutomatizacion();
                e.setNombre(nombre);
                catNivelAutomatizacionRepository.save(e);
            }
        }
    }

    private void seedAmenazas() {
        List<Object[]> amenazas = List.of(
            new Object[]{"Malware", "Software malicioso que daña o interrumpe sistemas", "Cibernética"},
            new Object[]{"Phishing", "Engaño para obtener credenciales o información sensible", "Cibernética"},
            new Object[]{"Ransomware", "Cifrado malicioso de datos con demanda de rescate", "Cibernética"},
            new Object[]{"Acceso no autorizado", "Acceso a sistemas o datos sin permiso", "Lógica"},
            new Object[]{"Error humano", "Errores cometidos por personal interno", "Humana"},
            new Object[]{"Desastre natural", "Terremotos, inundaciones u otros eventos naturales", "Física"},
            new Object[]{"Fallo de hardware", "Fallo de equipos físicos", "Física"},
            new Object[]{"Denegación de servicio (DoS)", "Saturación de recursos para inhabilitar servicios", "Cibernética"}
        );
        for (Object[] a : amenazas) {
            String nombre = (String) a[0];
            if (amenazaRepository.findAll().stream().noneMatch(e -> e.getNombre().equals(nombre))) {
                Amenaza e = new Amenaza();
                e.setNombre(nombre);
                e.setDescripcion((String) a[1]);
                e.setTipo((String) a[2]);
                amenazaRepository.save(e);
            }
        }
    }

    private void seedVulnerabilidades() {
        List<Object[]> vulnerabilidades = List.of(
            new Object[]{"Contraseña débil", "Uso de contraseñas fáciles de adivinar o reutilizadas", "Autenticación"},
            new Object[]{"Software sin actualizar", "Sistemas con parches de seguridad pendientes", "Técnica"},
            new Object[]{"Falta de cifrado", "Datos sensibles transmitidos o almacenados sin cifrar", "Técnica"},
            new Object[]{"Sin respaldo", "Ausencia de copias de seguridad regulares", "Operativa"},
            new Object[]{"Permisos excesivos", "Usuarios con más privilegios de los necesarios", "Control de acceso"},
            new Object[]{"Configuración incorrecta", "Sistemas mal configurados que exponen información", "Técnica"},
            new Object[]{"Falta de capacitación", "Personal sin formación en seguridad", "Humana"}
        );
        for (Object[] v : vulnerabilidades) {
            String nombre = (String) v[0];
            if (vulnerabilidadRepository.findAll().stream().noneMatch(e -> e.getNombre().equals(nombre))) {
                Vulnerabilidad e = new Vulnerabilidad();
                e.setNombre(nombre);
                e.setDescripcion((String) v[1]);
                e.setTipo((String) v[2]);
                vulnerabilidadRepository.save(e);
            }
        }
    }

    private void seedAreasPorDefecto() {
        List<String> areas = List.of(
            "Tecnología de la Información", "Recursos Humanos", "Finanzas",
            "Operaciones", "Seguridad Informática", "Gerencia General"
        );
        for (String nombre : areas) {
            if (areaRepository.findAll().stream().noneMatch(e -> e.getNombre().equals(nombre))) {
                Area e = new Area();
                e.setNombre(nombre);
                areaRepository.save(e);
            }
        }
    }
}
