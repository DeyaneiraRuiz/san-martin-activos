package com.sgsi.security.config;

import com.sgsi.security.entity.Rol;
import com.sgsi.security.entity.Usuario;
import com.sgsi.security.repository.RolRepository;
import com.sgsi.security.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class DatabaseSeeder implements CommandLineRunner {

    private final RolRepository rolRepository;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        seedRoles();
        seedUsuarios();
        System.out.println("DatabaseSeeder completado.");
    }

    private void seedRoles() {
        String[] roleNames = {
            "ROLE_ADMINISTRADOR", "ROLE_RESPONSABLE_AREA",
            "ROLE_ANALISTA_SEGURIDAD", "ROLE_TECNICO",
            "ROLE_USUARIO", "ROLE_OBSERVADOR"
        };
        for (String roleName : roleNames) {
            // Migrar roles viejos sin prefijo ROLE_
            String oldName = roleName.replace("ROLE_", "");
            rolRepository.findByNombre(oldName).ifPresent(rol -> {
                rol.setNombre(roleName);
                rolRepository.save(rol);
            });
            if (!rolRepository.findByNombre(roleName).isPresent()) {
                Rol rol = new Rol();
                rol.setNombre(roleName);
                rol.setDescripcion("Rol " + oldName);
                rolRepository.save(rol);
            }
        }
    }

    private void seedUsuarios() {
        crearUsuario("admin", "admin@sgsi.com", "Admin1234!", "Administrador del Sistema",
            null, null, "Administrador", null, null, null, "sistemas", "Administrador del Sistema",
            List.of("ROLE_ADMINISTRADOR"));
    }

    private void crearUsuario(String username, String email, String password,
                               String nombreCompleto, String ci, String complementoCi,
                               String nombres, String apellidoPaterno, String apellidoMaterno,
                               String celular, String departamento, String cargo,
                               List<String> rolNombres) {
        Set<Rol> roles = new HashSet<>();
        for (String nombre : rolNombres) {
            rolRepository.findByNombre(nombre).ifPresent(roles::add);
        }

        Optional<Usuario> existing = usuarioRepository.findByUsername(username);
        if (existing.isPresent()) {
            Usuario u = existing.get();
            // Actualizar roles si está vacío (migración desde schema anterior)
            if (u.getRoles() == null || u.getRoles().isEmpty()) {
                u.setRoles(roles);
                usuarioRepository.save(u);
                System.out.println("Roles migrados para: " + username);
            }
            return;
        }

        Usuario user = new Usuario();
        user.setUsername(username);
        user.setEmail(email);
        user.setPasswordHash(passwordEncoder.encode(password));
        user.setActivo(true);
        user.setNombreCompleto(nombreCompleto);
        user.setCi(ci);
        user.setComplementoCi(complementoCi);
        user.setNombres(nombres);
        user.setApellidoPaterno(apellidoPaterno);
        user.setApellidoMaterno(apellidoMaterno);
        user.setCelular(celular);
        user.setDepartamento(departamento);
        user.setCargo(cargo);
        user.setRoles(roles);
        usuarioRepository.save(user);
        System.out.println("Usuario creado: " + username);
    }
}