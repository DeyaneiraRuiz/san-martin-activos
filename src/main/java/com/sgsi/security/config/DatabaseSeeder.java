package com.sgsi.security.config;

import com.sgsi.security.entity.Rol;
import com.sgsi.security.repository.RolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class DatabaseSeeder implements CommandLineRunner {

    private final RolRepository rolRepository;

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
            
            System.out.println("✅ Datos Iniciales Sembrados (Roles) en la Base de Datos");
        }
    }
}
