
package com.example.demo2.api.v1.local.proyecto1.auth;

import com.example.demo2.api.v1.local.proyecto1.users.User;
import com.example.demo2.api.v1.local.proyecto1.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class AuthUserRolesService implements UserDetailsService {

    @Autowired
    UserService userService;
    
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.obtenerPorCorreo(username).get();
        return AuthUserRoles.build(user);
    }
    
    
    
}
