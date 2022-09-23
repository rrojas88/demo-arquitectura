
package com.example.demo2.api.v1.local.proyecto1.users;

import com.example.demo2.api.v1.local.Utils.ErrorService;
import com.example.demo2.api.v1.local.proyecto1.roles.RolService;
import com.example.demo2.api.v1.local.proyecto1.users.adapters.UserAdapter;
import com.example.demo2.api.v1.local.proyecto1.users.adapters.UserDto;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class UserService {
    
    @Autowired
    UserAdapter userAdapter;
    
    @Autowired
    RolService rolService;
    
    //@Autowired //Error: Is there an unresolvable circular reference? -> authController
    //PasswordEncoder passwordEncoder;
    //@Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
    private String myClassName = UserService.class.getName();
    
    
    public Object getAll(){
        try {
            return userAdapter.getAll();
        }
        catch (Exception e) {
            return new ErrorService(
                "No se listaron los Usuarios", 
                e.getMessage(), 
                this.myClassName
            );
        }
    }
    
    public Object getById(Integer id){
        try {
            return userAdapter.getById(id);
        }
        catch (Exception e) {
            return new ErrorService(
                "No se obtuvo el usuario", 
                e.getMessage(), 
                this.myClassName
            );
        }
    }
    
    public Object getByName( String name ){
        try {
            return userAdapter.getByName(name);
        }
        catch (Exception e) {
            return new ErrorService(
                "No se obtuvo el Usuario", 
                e.getMessage(), 
                this.myClassName
            );
        }
    }
    
    public Object getByEmail( String email ){ // Optional<User>
        try {
            return userAdapter.getByEmail(email);
        }
        catch (Exception e) {
            return new ErrorService(
                "No se obtuvo el Usuario", 
                e.getMessage(), 
                this.myClassName
            );
        }
    }
    
    public boolean existsByName(String name ){
        return userAdapter.existsByName(name);
    }
    
    public boolean existsByEmail(String email ){
        return userAdapter.existsByEmail(email);
    }
    
    
    public Object save( UserDto userDto )
    {
        try{
            Object row = userAdapter.save(userDto);
            return row;
        }
        catch (Exception e) {
            return new ErrorService(
                "No se pudo guardar el usuario", 
                e.getMessage(), 
                this.myClassName
            );
        }
    }
    
}
