
package com.example.demo2.api.v1.local.proyecto1.auth;

import com.example.demo2.api.v1.local.Utils.MessageLocal;
import com.example.demo2.api.v1.local.proyecto1.auth.jwt.JwtProvider;
import com.example.demo2.api.v1.local.proyecto1.auth.login.ResponseLoginDto;
import com.example.demo2.api.v1.local.proyecto1.auth.login.LoginDto;
import com.example.demo2.api.v1.local.proyecto1.roles.Rol;
import com.example.demo2.api.v1.local.proyecto1.roles.RolName;
import com.example.demo2.api.v1.local.proyecto1.roles.RolService;
import com.example.demo2.api.v1.local.proyecto1.users.User;
import com.example.demo2.api.v1.local.proyecto1.users.UserDto;
import com.example.demo2.api.v1.local.proyecto1.users.UserService;
import java.util.HashSet;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/v1/local/auth")
@CrossOrigin
public class AuthController {
    
    @Autowired
    PasswordEncoder passwordEncoder;
    
    @Autowired
    AuthenticationManager authenticationManager;
    
    @Autowired
    UserService userService;
    
    @Autowired
    RolService rolService;
    
    @Autowired
    JwtProvider jwtProvider;
    
    @PostMapping("/register")
    public ResponseEntity<?> nuevo (
        @Valid @RequestBody UserDto userDto, 
        BindingResult bindingResult
    ){
        String message = "";
        if( bindingResult.hasErrors() ){
            message = "Campos erroneos o email inválido";
        }
        if( userService.existsByEmail( userDto.getEmail() ) && message.equals("") ){
            message = "Este correo ya está registrado";
        }
        if( ! message.equals("") ){
            return new ResponseEntity( new MessageLocal(message), HttpStatus.BAD_REQUEST );
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
        
        userService.save(user);
        message = "Usuario guardado";
        
        return new ResponseEntity( new MessageLocal(message), HttpStatus.CREATED );
    }
    
    @PostMapping("/login")
    public ResponseEntity<ResponseLoginDto> login (@Valid @RequestBody LoginDto login, BindingResult bindingResult )
    {
        String message = "";
        if( bindingResult.hasErrors() ){
            message = "Campos erroneos o email inválido";
        }
        if( ! message.equals("") ){
            return new ResponseEntity( new MessageLocal(message), HttpStatus.BAD_REQUEST );
        }
        
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
            jwt, //
            authUserRoles.getEmail(),
            authUserRoles.getUsername(), 
            authUserRoles.getAuthorities()
        );
        
        return new ResponseEntity(responseLoginDto, HttpStatus.OK );
    }
    
    @GetMapping("/test")
    public ResponseEntity<ResponseLoginDto> test ( )
    {
        String message = "Get del Auth/test = OK ";
        return new ResponseEntity( new MessageLocal(message), HttpStatus.OK );
    }
    
}
