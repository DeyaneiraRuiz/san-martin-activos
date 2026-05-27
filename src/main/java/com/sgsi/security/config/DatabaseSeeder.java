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
        // Sembrar 6 Roles
        String[] roleNames = {
                "ROLE_ADMINISTRADOR", "ROLE_RESPONSABLE_AREA",
                "ROLE_ANALISTA_SEGURIDAD", "ROLE_TECNICO",
                "ROLE_USUARIO", "ROLE_OBSERVADOR"
        };

        for (String roleName : roleNames) {
            // Migrar roles viejos sin prefijo ROLE_ al formato nuevo
            String oldName = roleName.replace("ROLE_", "");
            java.util.Optional<Rol> oldRol = rolRepository.findByNombre(oldName);
            if (oldRol.isPresent()) {
                Rol rol = oldRol.get();
                rol.setNombre(roleName);
                rol.setDescripcion("Rol " + oldName);
                rolRepository.save(rol);
                System.out.println("Rol migrado: " + oldName + " -> " + roleName);
            }

            if (!rolRepository.findByNombre(roleName).isPresent()) {
                Rol rol = new Rol();
                rol.setNombre(roleName);
                rol.setDescripcion("Rol " + roleName.replace("ROLE_", ""));
                rolRepository.save(rol);
            }
        }
        System.out.println("Roles verificados/sembrados en la Base de Datos");

        // Sembrar Usuarios
        crearUsuarioSiNoExiste("admin", "admin@sgsi.com", "admin1", "Administrador del Sistema", "ROLE_ADMINISTRADOR");
        crearUsuarioSiNoExiste("responsable", "responsable@sgsi.com", "responsable", "Responsable de Área",
                "ROLE_RESPONSABLE_AREA");
        crearUsuarioSiNoExiste("analista", "analista@sgsi.com", "analista", "Analista de Seguridad",
                "ROLE_ANALISTA_SEGURIDAD");
        crearUsuarioSiNoExiste("tecnico", "tecnico@sgsi.com", "tecnico", "Técnico", "ROLE_TECNICO");
        crearUsuarioSiNoExiste("usuario", "usuario@sgsi.com", "usuario", "Usuario Standard", "ROLE_USUARIO");
        crearUsuarioSiNoExiste("observador", "observador@sgsi.com", "observador1", "Observador del Sistema",
                "ROLE_OBSERVADOR");

        System.out.println("Usuarios iniciales verificados/sembrados");
    }

    private void crearUsuarioSiNoExiste(String username, String email, String password, String fullName,
            String roleName) {
        Rol rol = rolRepository.findByNombre(roleName).orElse(null);
        if (rol == null) return;

        java.util.Optional<Usuario> existingUser = usuarioRepository.findByUsername(username);
        if (existingUser.isPresent()) {
            // Corregir rol si no coincide con el esperado
            Usuario user = existingUser.get();
            if (user.getRol() == null || !roleName.equals(user.getRol().getNombre())) {
                user.setRol(rol);
                usuarioRepository.save(user);
                System.out.println("Usuario '" + username + "' actualizado con rol: " + roleName);
            }
        } else {
            Usuario user = new Usuario();
            user.setUsername(username);
            user.setEmail(email);
            user.setPasswordHash(passwordEncoder.encode(password));
            user.setActivo(true);
            user.setRol(rol);
            user.setNombreCompleto(fullName);
            usuarioRepository.save(user);
        }
    }
}
