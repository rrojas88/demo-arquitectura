
package com.example.demo2.api.v1.local.proyecto1.roles;


import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class RolService {
    
    @Autowired
    RolRepository rolRepository;
    
    public Optional<Rol> getByName( RolName name ){
        return rolRepository.findByName(name);
    }
    
    public void save(Rol rol){
        rolRepository.save(rol);
    }
    
}
