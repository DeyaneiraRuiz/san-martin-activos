package com.sgsi.security.config;

import com.sgsi.security.entity.Rol;
import com.sgsi.security.entity.Usuario;
import com.sgsi.security.repository.RolRepository;
import com.sgsi.security.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class DatabaseSeeder implements CommandLineRunner {

    private final RolRepository rolRepository;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (!rolRepository.findByNombre("ROLE_ADMIN").isPresent()) {
            Rol admin = new Rol();
            admin.setNombre("ROLE_ADMIN");
            admin.setDescripcion("Administrador del Sistema");
            rolRepository.save(admin);
        }

        if (!rolRepository.findByNombre("ROLE_AUDITOR").isPresent()) {
            Rol auditor = new Rol();
            auditor.setNombre("ROLE_AUDITOR");
            auditor.setDescripcion("Auditor del Sistema");
            rolRepository.save(auditor);
        }

        if (!rolRepository.findByNombre("ROLE_OBSERVADOR").isPresent()) {
            Rol observador = new Rol();
            observador.setNombre("ROLE_OBSERVADOR");
            observador.setDescripcion("Observador del Sistema");
            rolRepository.save(observador);
        }

        System.out.println("Roles verificados/sembrados en la Base de Datos");

        Rol adminRol = rolRepository.findByNombre("ROLE_ADMIN").orElse(null);
        Rol auditorRol = rolRepository.findByNombre("ROLE_AUDITOR").orElse(null);
        Rol obsRol = rolRepository.findByNombre("ROLE_OBSERVADOR").orElse(null);

        if (adminRol != null && !usuarioRepository.findByUsername("admin").isPresent()) {
            Usuario adminUser = new Usuario();
            adminUser.setUsername("admin");
            adminUser.setEmail("admin@sgsi.com");
            adminUser.setPasswordHash(passwordEncoder.encode("admin1"));
            adminUser.setActivo(true);
            adminUser.setRol(adminRol);
            adminUser.setNombreCompleto("Administrador del Sistema");
            usuarioRepository.save(adminUser);
        }

        if (auditorRol != null && !usuarioRepository.findByUsername("auditor").isPresent()) {
            Usuario auditorUser = new Usuario();
            auditorUser.setUsername("auditor");
            auditorUser.setEmail("auditor@sgsi.com");
            auditorUser.setPasswordHash(passwordEncoder.encode("auditor1"));
            auditorUser.setActivo(true);
            auditorUser.setRol(auditorRol);
            auditorUser.setNombreCompleto("Auditor del Sistema");
            usuarioRepository.save(auditorUser);
        }

        if (obsRol != null && !usuarioRepository.findByUsername("observador").isPresent()) {
            Usuario obsUser = new Usuario();
            obsUser.setUsername("observador");
            obsUser.setEmail("observador@sgsi.com");
            obsUser.setPasswordHash(passwordEncoder.encode("observador1"));
            obsUser.setActivo(true);
            obsUser.setRol(obsRol);
            obsUser.setNombreCompleto("Observador del Sistema");
            usuarioRepository.save(obsUser);
        }

        System.out.println("Usuarios iniciales verificados/sembrados (admin, auditor, observador)");

        System.out.println("Roles disponibles en el sistema:");
        rolRepository.findAll().forEach(r -> {
            System.out.println(r.getNombre() + " | ID para Postman: " + r.getId());
        });
    }
}
