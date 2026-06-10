package com.sgsi.security.service;

import com.sgsi.security.dto.UsuarioDto;
import com.sgsi.security.entity.Rol;
import com.sgsi.security.entity.Usuario;
import com.sgsi.security.repository.RolRepository;
import com.sgsi.security.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;

    public List<UsuarioDto.Response> findAll() {
        return repository.findAll().stream().map(this::toResponse).toList();
    }

    public UsuarioDto.Response findById(Integer id) {
        return toResponse(repository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con ID: " + id)));
    }

    @Transactional
    public UsuarioDto.Response save(UsuarioDto.Request request) {
        Usuario usuario = new Usuario();
        usuario.setUsername(request.username());
        usuario.setEmail(request.email());
        String pwd = (request.password() != null && !request.password().isBlank())
            ? request.password() : "Sgsi2024!";
        usuario.setPasswordHash(passwordEncoder.encode(pwd));
        usuario.setActivo(!"inactivo".equals(request.estado()) && !"bloqueado".equals(request.estado()));
        usuario.setCi(request.ci());
        usuario.setComplementoCi(request.complementoCi());
        usuario.setApellidoPaterno(request.apellidoPaterno());
        usuario.setApellidoMaterno(request.apellidoMaterno());
        usuario.setNombres(request.nombres());
        usuario.setNombreCompleto(buildNombreCompleto(request));
        usuario.setCelular(request.celular());
        usuario.setCargo(request.cargo());
        usuario.setDepartamento(request.departamento());
        usuario.setRoles(resolveRoles(request.rolNombres()));
        return toResponse(repository.save(usuario));
    }

    @Transactional
    public UsuarioDto.Response update(Integer id, UsuarioDto.Request request) {
        Usuario usuario = repository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con ID: " + id));
        usuario.setUsername(request.username());
        if (request.password() != null && !request.password().isBlank()) {
            usuario.setPasswordHash(passwordEncoder.encode(request.password()));
        }
        usuario.setEmail(request.email());
        usuario.setActivo(!"inactivo".equals(request.estado()) && !"bloqueado".equals(request.estado()));
        usuario.setCi(request.ci());
        usuario.setComplementoCi(request.complementoCi());
        usuario.setApellidoPaterno(request.apellidoPaterno());
        usuario.setApellidoMaterno(request.apellidoMaterno());
        usuario.setNombres(request.nombres());
        usuario.setNombreCompleto(buildNombreCompleto(request));
        usuario.setCelular(request.celular());
        usuario.setCargo(request.cargo());
        usuario.setDepartamento(request.departamento());
        if (request.rolNombres() != null && !request.rolNombres().isEmpty()) {
            usuario.setRoles(resolveRoles(request.rolNombres()));
        }
        return toResponse(repository.save(usuario));
    }

    public void deleteById(Integer id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Usuario no encontrado con ID: " + id);
        }
        repository.deleteById(id);
    }

    private Set<Rol> resolveRoles(List<String> rolNombres) {
        Set<Rol> roles = new HashSet<>();
        for (String nombre : rolNombres) {
            String nombreCompleto = nombre.startsWith("ROLE_") ? nombre : "ROLE_" + nombre;
            rolRepository.findByNombre(nombreCompleto).ifPresent(roles::add);
        }
        return roles;
    }

    public UsuarioDto.Response toResponse(Usuario u) {
        List<String> roles = u.getRoles().stream()
            .map(r -> r.getNombre().replace("ROLE_", ""))
            .collect(Collectors.toList());

        String estado;
        if (u.getBloqueadoHasta() != null && u.getBloqueadoHasta().isAfter(LocalDateTime.now())) {
            estado = "bloqueado";
        } else {
            estado = Boolean.TRUE.equals(u.getActivo()) ? "activo" : "inactivo";
        }

        return new UsuarioDto.Response(
            u.getId(), u.getUsername(), u.getEmail(), estado, roles,
            u.getCi(), u.getComplementoCi(), u.getApellidoPaterno(), u.getApellidoMaterno(),
            u.getNombres(), u.getNombreCompleto(), u.getCelular(), u.getCargo(), u.getDepartamento(),
            u.getUltimoLogin(), u.getCreatedAt()
        );
    }

    private String buildNombreCompleto(UsuarioDto.Request r) {
        StringBuilder sb = new StringBuilder();
        if (r.nombres() != null) sb.append(r.nombres().trim());
        if (r.apellidoPaterno() != null) { if (sb.length() > 0) sb.append(" "); sb.append(r.apellidoPaterno().trim()); }
        if (r.apellidoMaterno() != null) { if (sb.length() > 0) sb.append(" "); sb.append(r.apellidoMaterno().trim()); }
        return sb.length() > 0 ? sb.toString() : null;
    }
}