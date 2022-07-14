
package com.example.demo2.api.v1.local.proyecto1.auth;

import com.example.demo2.api.v1.local.Utils.ErrorService;
import com.example.demo2.api.v1.local.proyecto1.auth.jwt.JwtProvider;
import com.example.demo2.api.v1.local.proyecto1.auth.login.LoginDto;
import com.example.demo2.api.v1.local.proyecto1.auth.login.ResponseLoginDto;
import com.example.demo2.api.v1.local.proyecto1.roles.Rol;
import com.example.demo2.api.v1.local.proyecto1.roles.RolName;
import com.example.demo2.api.v1.local.proyecto1.roles.RolService;
import com.example.demo2.api.v1.local.proyecto1.users.User;
import com.example.demo2.api.v1.local.proyecto1.users.UserDto;
import com.example.demo2.api.v1.local.proyecto1.users.UserService;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthService {
    
    @Autowired
    UserService userService;
    
    @Autowired
    RolService rolService;
    
    @Autowired
    JwtProvider jwtProvider;
    
    @Autowired
    PasswordEncoder passwordEncoder;
    
    @Autowired
    AuthenticationManager authenticationManager;
    
    private String myClassName = AuthService.class.getName();
    
    
    public Object register( UserDto userDto ){
        try {
            if( userService.existsByEmail( userDto.getEmail() ) ){
                ErrorService errService = new ErrorService(
                    "Este correo ya está registrado", 
                    "", this.myClassName
                );
                return errService;
            }
            User user = new User(
                userDto.getName(), userDto.getEmail(), 
                passwordEncoder.encode( userDto.getPassword() )
            );

            Set<Rol> roles = new HashSet<>();
            roles.add(rolService.getByName(RolName.ROLE_LECTURA).get());
            if( userDto.getRoles().contains("Usuario") || userDto.getRoles().contains("Usuario normal") ){
                roles.add( rolService.getByName(RolName.ROLE_USUARIO).get() );
            }
            if( userDto.getRoles().contains("Admin") || userDto.getRoles().contains("Administrador") ){
                roles.add( rolService.getByName(RolName.ROLE_ADMIN).get() );
            }
            
            user.setRoles(roles);
            Object row = userService.save(user);
            return null;
        }
        catch (Exception e) {
            ErrorService errService = new ErrorService(
                "No se pudo guardar el usuario", 
                e.getMessage(), 
                this.myClassName
            );
            return errService;
        }
    }
    
    
    public Object login( LoginDto login ){
        try {
            Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    login.getEmail(), login.getPassword()
                )
            );
            // Se Autentica el usuario
            SecurityContextHolder.getContext().setAuthentication(auth);
            // Genera el Token a partir de la Autenticacion
            String jwt = jwtProvider.generateToken(auth);
            // 
            UserRolesPrincipal authUserRoles = (UserRolesPrincipal) auth.getPrincipal();
            // 
            ResponseLoginDto responseLoginDto = new ResponseLoginDto(
                jwt,
                authUserRoles.getEmail(),
                authUserRoles.getUsername(), 
                authUserRoles.getAuthorities()
            );
            return responseLoginDto;
        }
        catch (Exception e) {
            ErrorService errService = new ErrorService(
                "Usuario o contraseña incorrecta", 
                e.getMessage(), 
                this.myClassName
            );
            return errService;
        }
    }
}
