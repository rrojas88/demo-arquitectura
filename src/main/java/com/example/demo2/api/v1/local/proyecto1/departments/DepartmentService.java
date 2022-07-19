
package com.example.demo2.api.v1.local.proyecto1.departments;

import com.example.demo2.api.v1.local.Utils.ErrorService;
import com.example.demo2.api.v1.local.Utils.UtilsService;
import com.example.demo2.api.v1.local.proyecto1.cities.City;
import com.example.demo2.api.v1.local.proyecto1.cities.CityService;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DepartmentService {
    
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    CityService cityService;
    
    private String myClassName = DepartmentService.class.getName();
    

    public Object getAll() {
        try {
            return departmentRepository.findAll();
        }
        catch (Exception e) {
            ErrorService errService = new ErrorService(
                "No se listaron los Departamentos", 
                e.getMessage(), 
                this.myClassName
            );
            return errService;
        }
    }
    
    public Object getById(Integer id){
        try {
            return departmentRepository.findById(id);
        }
        catch (Exception e) {
            ErrorService errService = new ErrorService(
                "No se obtuvo el Departamento", 
                e.getMessage(), 
                this.myClassName
            );
            return errService;
        }
    }
    
    public Object getByName(String name){    
        try {
            return departmentRepository.findByName(name);
        }
        catch (Exception e) {
            ErrorService errService = new ErrorService(
                "No se obtuvo el Departamento", 
                e.getMessage(), 
                this.myClassName
            );
            return errService;
        }
    }
    
    public Object getByCode(String code) {
        try {
            return departmentRepository.findByCode(code);
        }
        catch (Exception e) {
            ErrorService errService = new ErrorService(
                "No se obtuvo el Departamento", 
                e.getMessage(), 
                this.myClassName
            );
            return errService;
        }
    }

    public Object save(Department depto){
        try {
            return departmentRepository.save(depto);
        }
        catch (Exception e) {
            ErrorService errService = new ErrorService(
                "No se pudo guardar el Departamento", 
                e.getMessage(), 
                this.myClassName
            );
            return errService;
        }
    }

    
    public Object delete(Integer id){
        try {
            Object cities = cityService.getByDepartment_id(id);
            System.out.println( "\n ---> Tiene Ciudades: " );System.out.println(cities);
            if( UtilsService.isErrorService(cities) ){
                return cities;
            }
            if( cities != null && ((Optional)cities).isEmpty() ){
                System.out.println( "---> Entra: Tiene Ciudades " );
                    ErrorService errService = new ErrorService(
                    "No se puede eliminar el departamento porque tiene ciudades asociadas", 
                    "", 
                    this.myClassName
                );
                return errService;
            }System.out.println( "---> NO Entra: NO Tiene Ciudades " );
            
            Optional<Department> row = departmentRepository.findById(id);
            if( ! row.isEmpty() ){
                departmentRepository.deleteById(id);
                return "Se eliminó el registro con ID: " + id;
            }
            return "No se eliminó el registro con ID: " + id;
        }
        catch(Exception e){
            ErrorService errService = new ErrorService(
                "No se eliminó el Departamento", 
                e.getMessage(), 
                this.myClassName
            );
            return errService;
        }
    }
}
