
package com.example.demo2.api.v1.local.proyecto1.roles.adapters;

import com.example.demo2.api.v1.local.Utils.ErrorService;
import com.example.demo2.api.v1.local.proyecto1.roles.adapters.bd1.Rol;
//import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo2.api.v1.local.proyecto1.roles.adapters.bd1.RolRepository;

@Service
public class RolAdapter {
    
    @Autowired
    RolRepository rolRepository;
    
    private String myClassName = RolAdapter.class.getName();
    
    //public Optional<Rol1> getByName(RolName name) {
    //public Object getByName(RolName name) { // Este
    public Object getByName(String name) {
        try {
            return rolRepository.findByName(name);
        }
        catch (Exception e) {
            return new ErrorService(
                "No se obtuvo el rol", 
                e.getMessage(), 
                this.myClassName
            );
        }
    }
    
    public Object save(Rol rol ){
        try {
            return rolRepository.save(rol);
        }
        catch (Exception e) {
            return new ErrorService(
                "No se pudo guardar el rol", 
                e.getMessage(), 
                this.myClassName
            );
        }
    }
}
