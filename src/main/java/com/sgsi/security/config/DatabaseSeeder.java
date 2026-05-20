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
        if (rolRepository.count() == 0) {
            Rol admin = new Rol();
            admin.setNombre("ROLE_ADMIN");
            admin.setDescripcion("Administrador del Sistema");

            Rol user = new Rol();
            user.setNombre("ROLE_USER");
            user.setDescripcion("Usuario Standard");

            rolRepository.saveAll(Arrays.asList(admin, user));

            System.out.println("Datos Iniciales Sembrados (Roles) en la Base de Datos");
        }

        Rol adminRol = rolRepository.findByNombre("ROLE_ADMIN").orElse(null);

        if (usuarioRepository.count() == 0 && adminRol != null) {
            Usuario adminUser = new Usuario();
            adminUser.setUsername("admin");
            adminUser.setEmail("admin@sgsi.com");
            adminUser.setPasswordHash(passwordEncoder.encode("admin1"));
            adminUser.setActivo(true);
            adminUser.setRol(adminRol);
            adminUser.setNombreCompleto("Administrador del Sistema");

            usuarioRepository.save(adminUser);
            System.out.println("Usuario Administrador inicial sembrado (admin / admin1)");
        }

        System.out.println("Roles disponibles en el sistema:");
        rolRepository.findAll().forEach(r -> {
            System.out.println(r.getNombre() + " | ID para Postman: " + r.getId());
        });
    }
}
