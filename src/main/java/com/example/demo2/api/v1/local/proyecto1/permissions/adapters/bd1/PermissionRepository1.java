
package com.example.demo2.api.v1.local.proyecto1.permissions.adapters.bd1;

import java.util.ArrayList;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PermissionRepository1 extends JpaRepository<Permission1, Integer>{
    
    public abstract Optional<Permission1> findById(Integer id);
    
    //public abstract Optional<Permission1> findByAction_id(Integer action_id);
    
    //public abstract ArrayList<Permission1> findByRol_id(Integer rol_id);

}
