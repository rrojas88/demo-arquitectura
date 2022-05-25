
package com.example.demo2.api.v1.local.proyecto1.departments;

import java.util.ArrayList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DepartmentRepository extends CrudRepository<Department, Integer>{
    
    public abstract ArrayList<Department> findByName(String name);
    
    public abstract ArrayList<Department> findByCode(String code);

}
