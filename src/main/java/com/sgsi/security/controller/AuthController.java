package com.sgsi.security.controller;

import com.sgsi.security.dto.UsuarioDto;
import com.sgsi.security.entity.Rol;
import com.sgsi.security.entity.Usuario;
import com.sgsi.security.jwt.JwtUtils;
import com.sgsi.security.jwt.UserDetailsImpl;
import com.sgsi.security.repository.RolRepository;
import com.sgsi.security.repository.UsuarioRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody UsuarioDto.LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

            List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority().replace("ROLE_", ""))
                .collect(Collectors.toList());

            Map<String, Object> response = new HashMap<>();
            response.put("token", jwt);
            response.put("id", userDetails.getId());
            response.put("username", userDetails.getUsername());
            response.put("email", userDetails.getEmail());
            response.put("roles", roles);
            response.put("role", roles.isEmpty() ? "USUARIO" : roles.get(0));

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Usuario o contraseña incorrectos");
            return ResponseEntity.status(401).body(error);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UsuarioDto.Request signUpRequest) {
        if (usuarioRepository.existsByUsername(signUpRequest.username())) {
            return ResponseEntity.badRequest().body("Error: El nombre de usuario ya está en uso.");
        }
        if (usuarioRepository.existsByEmail(signUpRequest.email())) {
            return ResponseEntity.badRequest().body("Error: El email ya está en uso.");
        }

        Usuario user = new Usuario();
        user.setUsername(signUpRequest.username());
        user.setEmail(signUpRequest.email());
        user.setPasswordHash(encoder.encode(
            signUpRequest.password() != null ? signUpRequest.password() : "Sgsi2024!"));
        user.setActivo(true);

        Set<Rol> roles = new HashSet<>();
        List<String> rolNombres = signUpRequest.rolNombres() != null
            ? signUpRequest.rolNombres() : List.of("USUARIO");
        for (String nombre : rolNombres) {
            String nombreCompleto = nombre.startsWith("ROLE_") ? nombre : "ROLE_" + nombre;
            rolRepository.findByNombre(nombreCompleto).ifPresent(roles::add);
        }
        if (roles.isEmpty()) {
            rolRepository.findByNombre("ROLE_USUARIO").ifPresent(roles::add);
        }
        user.setRoles(roles);

        usuarioRepository.save(user);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Usuario registrado exitosamente!");
        return ResponseEntity.ok(response);
    }
}