# 🔐 SGSI — Sistema Integral de Gestión de Seguridad de la Información

Sistema web para la gestión integral de activos de información, riesgos y eventos de seguridad, desarrollado bajo los lineamientos de las normas **ISO/IEC 27001** e **ISO/IEC 27005**.

---

## 📋 Descripción

El SGSI integra dos subsistemas complementarios:
- **Sistema de Gestión de Activos de Información:** inventario, clasificación y custodia de activos institucionales.
- **Sistema de Gestión de Eventos e Incidentes de Seguridad:** registro, seguimiento y respuesta ante incidentes de seguridad.

Juntos permiten a las instituciones identificar qué información poseen, quién es responsable de ella, qué riesgos la amenazan y cómo responder ante eventos de seguridad.

---

## 🎯 Objetivos

- Inventariar y clasificar activos de información según nivel de confidencialidad.
- Asignar responsables de custodia y gestionar el ciclo de vida de los activos.
- Identificar y evaluar riesgos asociados a cada activo (ISO/IEC 27005).
- Registrar, gestionar y dar seguimiento a incidentes de seguridad.
- Garantizar el cumplimiento de controles establecidos en ISO/IEC 27001.

---

## 🧩 Módulos del Sistema

| # | Módulo | Descripción |
|---|---|---|
| 1 | **Seguridad y Administración** | Gestión de usuarios, roles, permisos y auditoría del sistema |
| 2 | **Módulo Organizacional** | Estructura organizacional, áreas y responsables |
| 3 | **Gestión de Activos** | Inventario, clasificación y ciclo de vida de activos de información |
| 4 | **Gestión de Procesos y Controles** | Procesos institucionales y controles de seguridad aplicados |
| 5 | **Gestión de Riesgos** | Identificación, evaluación y tratamiento de riesgos (ISO/IEC 27005) |
| 6 | **Gestión de Incidentes** | Registro, clasificación y seguimiento de eventos e incidentes de seguridad |
| 7 | **Catálogos** | Tipos de niveles, listas de estados, periodicidad, automatizaciones, etc. |
| 8 | **Auditoría** | Trazabilidad global y auditoría de cambios en todas las áreas |

---

## 🏗️ Arquitectura Tecnológica

| Capa | Tecnología | Detalles |
|---|---|---|
| **Backend** | **Spring Boot** *(Última version estable)* | Desarrollo de APIs REST, seguridad basada en JWT y control de roles. **Arquitectura modular** u orientada a microservicios. Integración con servicios externos. |
| **Frontend** | **Angular** *(Última version)* | Interfaz moderna y SPA responsiva. Arquitectura basada en componentes, dashboards administrativos y consumo de APIs REST. |
| **BD** | **PostgreSQL** | Modelo relacional robusto. Integridad referencial, escalabilidad y seguridad de datos. |
| **Contenedores** | **Docker + Docker Compose** | Empaquetado completo del sistema. Gestión mediante `Dockerfile`, `docker-compose.yml` y variables de entorno (`.env`). Incluirá documentación de despliegue. |

---

## 📁 Estructura del Proyecto (Backend Actual)

La actual estructura del backend (`sgsi-backend`) fue diseñada con un enfoque modular (Feature-Based Architecture). En el futuro, su migración directa a microservicios independientes es sencilla gracias a esta separación:

```text
sgsi-backend/
 ├── src/
 │   ├── main/
 │   │   ├── java/com/sgsi/
 │   │   │   ├── config/          # Configuraciones globales y Beans
 │   │   │   ├── security/        # JWT, Filtros, Entidades de Acceso
 │   │   │   ├── auditoria/       # Trazabilidad de entidades
 │   │   │   ├── catalogos/       # Listados en común
 │   │   │   ├── organizacion/    # Áreas, Grupos, Propietarios
 │   │   │   ├── activos/         # Dominio Activos (MVC/API REST)
 │   │   │   ├── procesos/        # Dominio Procesos, Controles y Documentos
 │   │   │   ├── riesgos/         # Dominio Riesgos y Vulnerabilidades ISO 27005
 │   │   │   ├── incidentes/      # Dominio Incidentes y Evidencias
 │   │   │   └── Application.java # Punto de arranque Spring Boot
 │   │
 │   ├── resources/
 │   │   ├── application.properties # Credenciales DB y entorno
 │
 ├── pom.xml
```

> **📌 Nota:** Dentro de cada módulo dominio (ej. `activos/`), las carpetas están segmentadas en base de responsabilidad simple: `controller/`, `service/`, `repository/`, `entity/` y `dto/`.

---

## 🚀 Instalación y Despliegue Local

### 0. Clonar el repositorio
Abre una terminal y clona el proyecto en tu máquina desde GitHub:

```bash
git clone https://github.com/DeyaneiraRuiz/san-martin-activos.git
cd san-martin-activos
```

### 1. Requisitos del Entorno

Para ejecutar este backend en tu fase de desarrollo se requiere:

1. **Java 17** o superior instalado y configurado en tus Variables de Entorno (`java -version`).
2. **PostgreSQL** instalado y ejecutando en el puerto TCP `5432`.
3. *(Opcional pero Recomendado)* **Docker Desktop / WSL2** para futuros levantamientos contenedorizados.

---

## ⚙️ Configuración y Despliegue Local

### 1. Configuración de Base de Datos
Entra a tu gestor gráfico de PostgreSQL (pgAdmin / DBeaver) y crea una nueva base de datos vacía. Por ejemplo, con el nombre `sgsi_db`.

Abre el archivo `src/main/resources/application.properties` y configura la conexión con tu usuario y contraseña:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/sgsi_db
spring.datasource.username=postgres
spring.datasource.password=TUPASSWORD_AQUI

# Permite que Hibernate (JPA) auto-regenere las 35 tablas de las entidades
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 2. Levantar el Backend (Spring Boot)
Ejecuta los siguientes comandos desde la misma carpeta que contiene al archivo pom (`activos/` o la inicialización principal) en tu terminal para compilar e iniciar el servidor (gracias al Maven Wrapper preinstalado):

**(En Windows)**
```powershell
.\mvnw.cmd clean compile
.\mvnw.cmd spring-boot:run
```

**(En Linux/Mac)**
```bash
./mvnw clean compile
./mvnw spring-boot:run
```

**Verificación de Despliegue:**
Asegurado en tu terminal los registros de inicialización, notarás multiples entradas refiriéndose a **"Hibernate: create table xy..."**, indicando que la estructura de la base de datos se ha levantado mágicamente según el código. Finalizando observarás una línea informando `Started Application in XX seconds...`.

### 3. Autenticación y Consumo 
*(Próxima iteración una vez implementada la seguridad JWT con perfiles `Usuario` y `Rol`).*

---

> **Autor:** Desarrollado para el cumplimiento con los estándares normativos ISO.
