
package com.example.demo2.api.v1.local.proyecto1.auth;


import com.example.demo2.api.v1.local.proyecto1.users.UserService;
import com.example.demo2.api.v1.local.proyecto1.users.adapters.bd1.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserRolesPrincipalService implements UserDetailsService {

    @Autowired
    UserService userService;
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Obtiene el Usuario desde la BD [ por nombre ]
        //User user = userService.getByName(username).get();
        // Obtiene el Usuario desde la BD [ por Correo ]
        //User user = userService.getByEmail(email).get();
        User user = (User)userService.getByEmail(email);

        // Convierte el Usuario en el "Principal" con sus privilegios
        return UserRolesPrincipal.build(user);
    }
    
}
