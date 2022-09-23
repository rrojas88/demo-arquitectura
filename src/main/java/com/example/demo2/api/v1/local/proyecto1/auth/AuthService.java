
package com.example.demo2.api.v1.local.proyecto1.auth;

import com.example.demo2.api.v1.local.Utils.ErrorService;
import com.example.demo2.api.v1.local.proyecto1.auth.jwt.JwtProvider;
import com.example.demo2.api.v1.local.proyecto1.auth.login.LoginDto;
import com.example.demo2.api.v1.local.proyecto1.auth.login.ResponseLoginDto;
import com.example.demo2.api.v1.local.proyecto1.users.UserService;
import com.example.demo2.api.v1.local.proyecto1.users.adapters.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
public class AuthService {
    
    @Autowired
    UserService userService;
    
    @Autowired
    JwtProvider jwtProvider;
    
    @Autowired
    AuthenticationManager authenticationManager;
    
    private String myClassName = AuthService.class.getName();
    
    @Value("${enviroment.current}")
    public String  zona_extra;
    
    public Object register( UserDto userDto ){
        try {
            return userService.save(userDto);
        }
        catch (Exception e) {
            ErrorService errService = new ErrorService(
                "No se pudo registrar el usuario", 
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
                zona_extra,
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
