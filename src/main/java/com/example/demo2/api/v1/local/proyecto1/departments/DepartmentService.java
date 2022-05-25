
package com.example.demo2.api.v1.local.proyecto1.departments;

import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DepartmentService {
    
    @Autowired
    DepartmentRepository departmentRepository;
    
    public ArrayList<Department> obtenerTodos(){
        return (ArrayList<Department>) departmentRepository.findAll();
    }

    public Department guardar(Department depto){
        return departmentRepository.save(depto);
    }

    public Optional<Department> obtenerPorId(Integer id){
        return departmentRepository.findById(id);
    }

    public ArrayList<Department> obtenerPorNombre(String name) {
        return departmentRepository.findByName(name);
    }
    
    public ArrayList<Department> obtenerPorCodigo(String code) {
        return departmentRepository.findByName(code);
    }

    public boolean eliminar(Integer id) {
        try{
            departmentRepository.deleteById(id);
            return true;
        }catch(Exception err){
            return false;
        }
    }
}
