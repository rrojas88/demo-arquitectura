
package com.example.demo2.api.v1.local.proyecto1.modules.adapters.bd1;

import java.util.ArrayList;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ModuleRepository extends JpaRepository<Module, Integer>{
    
    public abstract Optional<Module> findByName(String name);
    
    public abstract ArrayList<Module> findByCode(String code);
    
    public abstract Optional<Module> findById(Integer id);

}
