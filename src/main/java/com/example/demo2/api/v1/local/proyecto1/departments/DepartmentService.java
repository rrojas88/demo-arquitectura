
package com.example.demo2.api.v1.local.proyecto1.departments;

import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DepartmentService {
    
    @Autowired
    DepartmentRepository departmentRepository;
    
    public ArrayList<Department> getAll(){
        return (ArrayList<Department>) departmentRepository.findAll();
    }
    
    public Optional<Department> getById(Integer id){
        return departmentRepository.findById(id);
    }
    
    public ArrayList<Department> getByName(String name) {
        return departmentRepository.findByName(name);
    }
    
    public ArrayList<Department> getByCode(String code) {
        return departmentRepository.findByCode(code);
    }

    public Department save(Department depto){
        return departmentRepository.save(depto);
    }

    public boolean delete(Integer id) {
        try{
            departmentRepository.deleteById(id);
            return true;
        }catch(Exception err){
            return false;
        }
    }
}
