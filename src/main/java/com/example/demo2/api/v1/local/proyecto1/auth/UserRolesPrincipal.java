
package com.example.demo2.api.v1.local.proyecto1.auth;

import com.example.demo2.api.v1.local.proyecto1.users.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

// Entidad: UsuarioPrincipal(Video)
public class UserRolesPrincipal implements UserDetails {
    
    private String name;
    private String email;
    private String password;
    private Byte active;
    private Collection<? extends GrantedAuthority> authorities;

    public UserRolesPrincipal(
            String name, String email, 
            String password, Byte active, 
            Collection<? extends GrantedAuthority> authorities 
    ) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.active = active;
        this.authorities = authorities;
    }
    
    // Define permisos/provilegios del Usuario (Autenticacion)
    public static UserRolesPrincipal build ( User user ){
        List<GrantedAuthority> authorities = user.getRoles()
            .stream()
            .map( rol -> new SimpleGrantedAuthority (rol.getName().name() ))
            .collect( Collectors.toList() );
        
        return new UserRolesPrincipal( user.getName(), user.getEmail(),
            user.getPassword(), user.getActive(), authorities
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
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
    // Nuevos
    public String getEmail() {
        return email;
    }

    public Byte getActive() {
        return active;
    }
    
}
