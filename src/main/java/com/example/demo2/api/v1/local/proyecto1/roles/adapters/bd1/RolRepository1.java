
package com.example.demo2.api.v1.local.proyecto1.roles.adapters.bd1;

import com.example.demo2.api.v1.local.proyecto1.roles.adapters.RolName;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository1 extends JpaRepository<Rol1, Integer>{
    
    //Optional<Rol> findByRoleName( RolName name );
    //Optional<Rol1> findByName( RolName name );// Este
    Optional<Rol1> findByName( String name );
}
