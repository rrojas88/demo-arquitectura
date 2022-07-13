
package com.example.demo2.api.v1.local.proyecto1.users;

import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class UserService {
    
    @Autowired
    UserRepository userRepository;
    
    public Optional<User> getByName( String name ){
        return userRepository.findByName(name);
    }
    
    public Optional<User> getByEmail( String email ){
        return userRepository.findByEmail(email);
    }
    
    public boolean existsByName(String name ){
        return userRepository.existsByName(name);
    }
    
    public boolean existsByEmail(String email ){
        return userRepository.existsByEmail(email);
    }
    
    public void save( User user ){
        userRepository.save(user);
    }
    
}
