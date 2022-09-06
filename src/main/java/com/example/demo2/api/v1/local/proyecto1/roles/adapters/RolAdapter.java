
package com.example.demo2.api.v1.local.proyecto1.roles.adapters;

import com.example.demo2.api.v1.local.Utils.ErrorService;
import com.example.demo2.api.v1.local.proyecto1.roles.adapters.bd1.Rol1;
import com.example.demo2.api.v1.local.proyecto1.roles.adapters.bd1.RolRepository1;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolAdapter {
    
    @Autowired
    RolRepository1 rolRepository;
    
    private String myClassName = RolAdapter.class.getName();
    
    public Object getByName(RolName name) {
    //public Optional<Rol1> getByName(RolName name) {

        try {
            return rolRepository.findByName(name);
        }
        catch (Exception e) {
            return new ErrorService(
                "No se obtuvo la Ciudad", 
                e.getMessage(), 
                this.myClassName
            );
        }
    }
    
    public Object save(Rol1 rol ){
        try {
            return rolRepository.save(rol);
        }
        catch (Exception e) {
            return new ErrorService(
                "No se pudo guardar la Ciudad", 
                e.getMessage(), 
                this.myClassName
            );
        }
    }
}
