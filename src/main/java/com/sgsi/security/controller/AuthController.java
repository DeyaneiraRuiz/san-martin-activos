package com.sgsi.security.controller;

import com.sgsi.security.dto.UsuarioDto;
import com.sgsi.security.entity.Usuario;
import com.sgsi.security.jwt.JwtUtils;
import com.sgsi.security.jwt.UserDetailsImpl;
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

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody UsuarioDto.Request loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        Map<String, Object> response = new HashMap<>();
        response.put("token", jwt);
        response.put("id", userDetails.getId());
        response.put("username", userDetails.getUsername());
        response.put("email", userDetails.getEmail());

        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UsuarioDto.Request signUpRequest) {
        if (usuarioRepository.existsByUsername(signUpRequest.username())) {
            return ResponseEntity.badRequest().body("Error: El nombre de usuario ya está en uso.");
        }

        if (usuarioRepository.existsByEmail(signUpRequest.email())) {
            return ResponseEntity.badRequest().body("Error: El email ya está en uso.");
        }

        // Crear nueva cuenta de usuario
        Usuario user = new Usuario();
        user.setUsername(signUpRequest.username());
        user.setEmail(signUpRequest.email());
        user.setPasswordHash(encoder.encode(signUpRequest.password()));
        user.setActivo(true);
        com.sgsi.security.entity.Rol rol = new com.sgsi.security.entity.Rol();
        rol.setId(signUpRequest.rolId() != null ? signUpRequest.rolId() : 2); // Assuming 2 is a default user role
        user.setRol(rol);

        usuarioRepository.save(user);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Usuario registrado exitosamente!");

        return ResponseEntity.ok(response);
    }
}