
package com.example.demo2.api.v1.local.proyecto1.users;

import com.example.demo2.api.v1.local.Utils.ErrorService;
import com.example.demo2.api.v1.local.proyecto1.roles.Rol;
import com.example.demo2.api.v1.local.proyecto1.roles.RolName;
import com.example.demo2.api.v1.local.proyecto1.roles.RolService;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class UserService {
    
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    RolService rolService;
    
    //@Autowired
    PasswordEncoder passwordEncoder;
    
    private String myClassName = UserService.class.getName();
    
    
    public Object getAll(){
        try {
            return userRepository.findAll();
        }
        catch (Exception e) {
            ErrorService errService = new ErrorService(
                "No se listaron los Usuarios", 
                e.getMessage(), 
                this.myClassName
            );
            return errService;
        }
    }
    
    public Object getByName( String name ){
        try {
            return userRepository.findByName(name);
        }
        catch (Exception e) {
            ErrorService errService = new ErrorService(
                "No se obtuvo el Usuario", 
                e.getMessage(), 
                this.myClassName
            );
            return errService;
        }
    }
    
    public Object getByEmail( String email ){ // Optional<User>
        try {
            return userRepository.findByEmail(email);
        }
        catch (Exception e) {
            ErrorService errService = new ErrorService(
                "No se obtuvo el Usuario", 
                e.getMessage(), 
                this.myClassName
            );
            return errService;
        } /* */
    }
    
    public boolean existsByName(String name ){
        return userRepository.existsByName(name);
    }
    
    public boolean existsByEmail(String email ){
        return userRepository.existsByEmail(email);
    }
    
    public Object save( UserDto userDto )
    {
        try{
            if( this.existsByEmail( userDto.getEmail() ) ){
                ErrorService errService = new ErrorService(
                    "Este correo ya está registrado", 
                    "", this.myClassName
                );
                return errService;
            }
            User user = new User(
                userDto.getName(), 
                userDto.getEmail(), 
                passwordEncoder.encode( userDto.getPassword() )
            );
            // Establecer Roles del Usuario
            Set<Rol> roles = new HashSet<>();
            roles.add(rolService.getByName(RolName.ROLE_LECTURA).get());
            if( userDto.getRoles().contains("Usuario") || userDto.getRoles().contains("Usuario normal") ){
                roles.add( rolService.getByName(RolName.ROLE_USUARIO).get() );
            }
            if( userDto.getRoles().contains("Admin") || userDto.getRoles().contains("Administrador") ){
                roles.add( rolService.getByName(RolName.ROLE_ADMIN).get() );
            }
            user.setRoles(roles);
            
            Object row = userRepository.save(user);;
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
    
}
