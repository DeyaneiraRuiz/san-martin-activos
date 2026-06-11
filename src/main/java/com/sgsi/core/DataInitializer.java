package com.sgsi.core;

import com.sgsi.catalogos.entity.*;
import com.sgsi.catalogos.repository.*;
import com.sgsi.incidentes.entity.CategoriaIncidente;
import com.sgsi.incidentes.repository.CategoriaIncidenteRepository;
import com.sgsi.organizacion.entity.Area;
import com.sgsi.organizacion.repository.AreaRepository;
import com.sgsi.riesgos.entity.Amenaza;
import com.sgsi.riesgos.entity.Vulnerabilidad;
import com.sgsi.riesgos.repository.AmenazaRepository;
import com.sgsi.riesgos.repository.VulnerabilidadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Order(2)
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final CatTipoActivoRepository catTipoActivoRepository;
    private final CatEstadoActivoRepository catEstadoActivoRepository;
    private final CatEstadoIncidenteRepository catEstadoIncidenteRepository;
    private final CatTipoTicketRepository catTipoTicketRepository;
    private final CatPeriodicidadRepository catPeriodicidadRepository;
    private final CatNivelAutomatizacionRepository catNivelAutomatizacionRepository;
    private final AmenazaRepository amenazaRepository;
    private final VulnerabilidadRepository vulnerabilidadRepository;
    private final AreaRepository areaRepository;
    private final CategoriaIncidenteRepository categoriaIncidenteRepository;

    @Override
    public void run(String... args) {
        seedCatTiposActivo();
        seedCatEstadosActivo();
        seedCatEstadosIncidente();
        seedCatTiposTicket();
        seedCatPeriodicidades();
        seedCatNivelesAutomatizacion();
        seedAmenazas();
        seedVulnerabilidades();
        seedAreasPorDefecto();
        seedCategoriasIncidente();
    }

    private void seedCatTiposActivo() {
        List<String> tipos = List.of(
                "Hardware", "Software", "Servicios", "Datos", "Personas", "Instalaciones", "Redes");
        for (String nombre : tipos) {
            if (catTipoActivoRepository.findAll().stream().noneMatch(e -> nombre.equals(e.getNombre()))) {
                CatTipoActivo e = new CatTipoActivo();
                e.setNombre(nombre);
                catTipoActivoRepository.save(e);
            }
        }
    }

    
    private void seedCatEstadosActivo() {
        List<String> estados = List.of("Activo", "Inactivo", "En mantenimiento", "Dado de baja");
        for (String nombre : estados) {
            if (catEstadoActivoRepository.findAll().stream().noneMatch(e -> nombre.equals(e.getNombre()))) {
                CatEstadoActivo e = new CatEstadoActivo();
                e.setNombre(nombre);
                catEstadoActivoRepository.save(e);
            }
        }
    }

    private void seedCatEstadosIncidente() {
        List<String> estados = List.of("Abierto", "En proceso", "Resuelto", "Cerrado", "Cancelado");
        for (String nombre : estados) {
            if (catEstadoIncidenteRepository.findAll().stream().noneMatch(e -> nombre.equals(e.getNombre()))) {
                CatEstadoIncidente e = new CatEstadoIncidente();
                e.setNombre(nombre);
                catEstadoIncidenteRepository.save(e);
            }
        }
    }

    private void seedCatTiposTicket() {
        List<String> tipos = List.of("Incidente", "Solicitud de servicio", "Cambio", "Problema");
        for (String nombre : tipos) {
            if (catTipoTicketRepository.findAll().stream().noneMatch(e -> nombre.equals(e.getNombre()))) {
                CatTipoTicket e = new CatTipoTicket();
                e.setNombre(nombre);
                catTipoTicketRepository.save(e);
            }
        }
    }

    private void seedCatPeriodicidades() {
        List<Object[]> periodicidades = List.of(
                new Object[] { "Diaria", 1 },
                new Object[] { "Semanal", 7 },
                new Object[] { "Mensual", 30 },
                new Object[] { "Trimestral", 90 },
                new Object[] { "Semestral", 180 },
                new Object[] { "Anual", 365 });
        for (Object[] p : periodicidades) {
            String nombre = (String) p[0];
            if (catPeriodicidadRepository.findAll().stream().noneMatch(e -> nombre.equals(e.getNombre()))) {
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
            if (catNivelAutomatizacionRepository.findAll().stream().noneMatch(e -> nombre.equals(e.getNombre()))) {
                CatNivelAutomatizacion e = new CatNivelAutomatizacion();
                e.setNombre(nombre);
                catNivelAutomatizacionRepository.save(e);
            }
        }
    }

    private void seedAmenazas() {
        List<Object[]> amenazas = List.of(
                new Object[] { "Malware", "Software malicioso que daña o interrumpe sistemas", "Cibernética" },
                new Object[] { "Phishing", "Engaño para obtener credenciales o información sensible", "Cibernética" },
                new Object[] { "Ransomware", "Cifrado malicioso de datos con demanda de rescate", "Cibernética" },
                new Object[] { "Acceso no autorizado", "Acceso a sistemas o datos sin permiso", "Lógica" },
                new Object[] { "Error humano", "Errores cometidos por personal interno", "Humana" },
                new Object[] { "Desastre natural", "Terremotos, inundaciones u otros eventos naturales", "Física" },
                new Object[] { "Fallo de hardware", "Fallo de equipos físicos", "Física" },
                new Object[] { "Denegación de servicio", "Saturación de recursos para inhabilitar servicios",
                        "Cibernética" });
        for (Object[] a : amenazas) {
            String nombre = (String) a[0];
            if (amenazaRepository.findAll().stream().noneMatch(e -> nombre.equals(e.getNombre()))) {
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
                new Object[] { "Contraseña débil", "Uso de contraseñas fáciles de adivinar o reutilizadas",
                        "Autenticación" },
                new Object[] { "Software sin actualizar", "Sistemas con parches de seguridad pendientes", "Técnica" },
                new Object[] { "Falta de cifrado", "Datos sensibles transmitidos o almacenados sin cifrar", "Técnica" },
                new Object[] { "Sin respaldo", "Ausencia de copias de seguridad regulares", "Operativa" },
                new Object[] { "Permisos excesivos", "Usuarios con más privilegios de los necesarios",
                        "Control de acceso" },
                new Object[] { "Configuración incorrecta", "Sistemas mal configurados que exponen información",
                        "Técnica" },
                new Object[] { "Falta de capacitación", "Personal sin formación en seguridad", "Humana" });
        for (Object[] v : vulnerabilidades) {
            String nombre = (String) v[0];
            if (vulnerabilidadRepository.findAll().stream().noneMatch(e -> nombre.equals(e.getNombre()))) {
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
                "Operaciones", "Seguridad Informática", "Gerencia General");
        for (String nombre : areas) {
            if (areaRepository.findAll().stream().noneMatch(e -> nombre.equals(e.getNombre()))) {
                Area e = new Area();
                e.setNombre(nombre);
                areaRepository.save(e);
            }
        }
    }

    private void seedCategoriasIncidente() {
        List<CategoriaIncidente> categoriasIniciales = List.of(
                crearCategoria(
                        "C-INC-001",
                        "Incidentes de Seguridad de la Información",
                        "Esta categoría agrupa todos los eventos confirmados donde se ha comprometido la confidencialidad, integridad o disponibilidad de los datos. Representa un impacto real y materializado sobre los activos informáticos o físicos de la organización."),
                crearCategoria(
                        "C-INC-002",
                        "Vulnerabilidades",
                        "Esta categoría se refiere a la detección de debilidades, fallas o brechas de seguridad en los sistemas, procesos o infraestructuras. A diferencia de un incidente activo, representa un riesgo potencial; es decir, la puerta abierta que un atacante podría explotar si no se corrige a tiempo."),
                crearCategoria(
                        "C-INC-003",
                        "Categoría CIRO",
                        "Esta categoría clasifica los ciberincidentes de alto impacto, criticidad y reporte obligatorio. Agrupa aquellos eventos graves que interrumpen servicios esenciales, afectan infraestructuras críticas o comprometen la seguridad nacional, activando protocolos legales de respuesta inmediata.")

        );

        for (CategoriaIncidente categoria : categoriasIniciales) {
            if (categoriaIncidenteRepository.findAll().stream()
                    .noneMatch(e -> categoria.getNombre().equals(e.getNombre()))) {
                categoriaIncidenteRepository.save(categoria);
                System.out.println("✅ Categoría creada: " + categoria.getNombre());
            }
        }
    }

    private CategoriaIncidente crearCategoria(String codigo, String nombre, String descripcion) {
        CategoriaIncidente cat = new CategoriaIncidente();
        cat.setCodigo(codigo);
        cat.setNombre(nombre);
        cat.setDescripcion(descripcion);
        cat.setEstado("activo");
        return cat;
    }
}
