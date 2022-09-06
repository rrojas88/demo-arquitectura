
package com.example.demo2.api.v1.local.proyecto1.departments.adapters.bd1;

import java.util.ArrayList;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DepartmentRepository1 extends JpaRepository<Department1, Integer>{
    
    public abstract Optional<Department1> findByName(String name);
    
    public abstract ArrayList<Department1> findByCode(String code);
    
    public abstract Optional<Department1> findById(Integer id);

}
