
package com.example.demo2.api.v1.local.proyecto1.users.adapters;

import com.example.demo2.api.v1.local.Utils.ErrorService;
import com.example.demo2.api.v1.local.proyecto1.roles.RolService;
import com.example.demo2.api.v1.local.proyecto1.roles.adapters.RolName;
import com.example.demo2.api.v1.local.proyecto1.roles.adapters.bd1.Rol1;
import com.example.demo2.api.v1.local.proyecto1.users.adapters.bd1.User1;
import com.example.demo2.api.v1.local.proyecto1.users.adapters.bd1.UserRepository1;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class UserAdapter {
    
    @Autowired
    UserRepository1 userRepository;
    
    @Autowired
    RolService rolService;
    
    //@Autowired //Error: Is there an unresolvable circular reference? -> authController
    //PasswordEncoder passwordEncoder;
    //@Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
    private String myClassName = UserAdapter.class.getName();
    
    
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
    
    public Object getById(Integer id){
        try {
            Optional<User1> rowOptional = userRepository.findById(id);
            if( ! rowOptional.isPresent() || rowOptional.isEmpty() )
                return new ErrorService(id.toString(), "", this.myClassName, 404 );
            return rowOptional;
        }
        catch (Exception e) {
            ErrorService errService = new ErrorService(
                "No se obtuvo el usuario", 
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
            
            String passBd = "";
            try {
                passBd = this.passwordEncoder().encode( userDto.getPassword() );
            } catch (Exception e) {
                ErrorService errService = new ErrorService(
                    "No se pudo cifrar la contraseña", 
                    e.getMessage(), 
                    this.myClassName
                );
                return errService;
            }
            
            User1 user = new User1(
                userDto.getName(), 
                userDto.getEmail(), 
                passBd
            );
            
            // Establecer Roles del Usuario
            Set<Rol1> roles = new HashSet<>();
            //roles.add(rolService.getByName(RolName.ROLE_LECTURA).get());
            //Rol1 role_ = (Rol1)rolService.getByName(RolName.ROLE_LECTURA); // Este
            Rol1 role_ = (Rol1)rolService.getByName("ROLE_LECTURA");
            roles.add(role_);
            
            if( userDto.getRoles().contains("Usuario") || userDto.getRoles().contains("Usuario normal") ){
                //roles.add( rolService.getByName(RolName.ROLE_USUARIO).get() );
                //Rol1 role2_ = (Rol1)rolService.getByName(RolName.ROLE_USUARIO); // Este
                Rol1 role2_ = (Rol1)rolService.getByName("ROLE_USUARIO");
                roles.add(role2_);
            }
            if( userDto.getRoles().contains("Admin") || userDto.getRoles().contains("Administrador") ){
                //roles.add( rolService.getByName(RolName.ROLE_ADMIN).get() );
                //Rol1 role3_ = (Rol1)rolService.getByName(RolName.ROLE_ADMIN); // Este
                Rol1 role3_ = (Rol1)rolService.getByName("ROLE_ADMIN");
                roles.add(role3_);
            }
            user.setRoles(roles);
            
            Object row = userRepository.save(user);
            return null;
        }
        catch ( DataAccessException e) {
            ErrorService errService = new ErrorService(
                "No se pudo guardar el usuario", 
                e.getMessage(), 
                this.myClassName
            );
            return errService;
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

