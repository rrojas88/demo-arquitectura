
package com.example.demo2.api.v1.local.proyecto1.departments;

import java.util.ArrayList;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer>{
    
    public abstract Optional<Department> findByName(String name);
    
    public abstract ArrayList<Department> findByCode(String code);
    public abstract Optional<Department> findById(Integer id);


}
