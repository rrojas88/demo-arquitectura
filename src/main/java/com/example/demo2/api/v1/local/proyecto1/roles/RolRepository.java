
package com.example.demo2.api.v1.local.proyecto1.roles;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer>{
    
    Optional<Rol> findByName( String name );
}
