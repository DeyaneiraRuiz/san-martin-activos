package com.sgsi.security.jwt;

import com.sgsi.security.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Getter
@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String username;
    private String email;
    private String password;
    private Boolean activo;
    private Collection<? extends GrantedAuthority> authorities;

    public static UserDetailsImpl build(Usuario user) {
        // As per the requirement, we will simply use the rol_id or a fixed "ROLE_USER"/"ROLE_ADMIN" 
        // based on the rol_id. If rol_id == 1, ADMIN, else USER.
        // Actually, if we just want to load the role from DB, we can just say "ROLE_" + user.getRolId()
        // Or if rol mapped, user.getRol().getNombre(). We don't fetch rol eagerly in this current impl, 
        // so let's stick to "ROLE_" + user.getRolId() to fulfill "simplemente usamos el rol_id"
        Integer roleId = user.getRol() != null ? user.getRol().getId() : 2; // Default to 2 if no role
        List<GrantedAuthority> authorities = Collections.singletonList(
                new SimpleGrantedAuthority("ROLE_" + roleId)
        );

        return new UserDetailsImpl(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPasswordHash(),
                user.getActivo(),
                authorities
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // Podrías mapear esto a 'bloqueado_hasta' si gustas, por ahora activo
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return activo;
    }
}
