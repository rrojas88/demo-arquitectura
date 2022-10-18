
package com.example.demo2.api.v1.local.proyecto1.roles;


import com.example.demo2.api.v1.local.proyecto1.roles.adapters.RolAdapter;
//import com.example.demo2.api.v1.local.proyecto1.roles.adapters.RolName;
import com.example.demo2.api.v1.local.proyecto1.roles.adapters.bd1.Rol;
//import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class RolService {
    
    @Autowired
    RolAdapter rolAdapter;
    
    //public Optional<Rol1> getByName( RolName name ){
    //public Object getByName( RolName name ){ // Estes
    public Object getByName( String name ){
        return rolAdapter.getByName(name);
    }
    
    public void save(Rol rol){
        rolAdapter.save(rol);
    }
    
}
