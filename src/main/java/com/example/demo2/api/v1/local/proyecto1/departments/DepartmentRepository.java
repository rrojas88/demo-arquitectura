
package com.example.demo2.api.v1.local.proyecto1.departments;

import java.util.ArrayList;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DepartmentRepository extends CrudRepository<Department, Integer>{
    
    public abstract Optional<Department> findByName(String name);
    
    public abstract ArrayList<Department> findByCode(String code);
    public abstract ArrayList<Department> findById(String id);


}
