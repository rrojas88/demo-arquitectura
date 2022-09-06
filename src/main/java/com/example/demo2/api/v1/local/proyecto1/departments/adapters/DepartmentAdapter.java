
package com.example.demo2.api.v1.local.proyecto1.departments.adapters;

import com.example.demo2.api.v1.local.Utils.ErrorService;
import com.example.demo2.api.v1.local.proyecto1.cities.CityService;
import com.example.demo2.api.v1.local.proyecto1.departments.adapters.bd1.Department1;
import com.example.demo2.api.v1.local.proyecto1.departments.adapters.bd1.DepartmentRepository1;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DepartmentAdapter {

    @Autowired
    DepartmentRepository1 departmentRepository;
    
    @Autowired
    CityService cityService;

    private String myClassName = DepartmentAdapter.class.getName();

    public Object getAll() {
        try {
            return departmentRepository.findAll();
        } catch (Exception e) {
            return new ErrorService(
                "No se listaron los Departamentos.",
                e.getMessage(),
                this.myClassName
            );
        }
    }

    public Object getById(Integer id) {
        try {
            Optional<Department1> rowOptional = departmentRepository.findById(id);
            if (!rowOptional.isPresent() || rowOptional.isEmpty()) {
                return new ErrorService(id.toString(), "", this.myClassName, 404);
            }
            return rowOptional;
        } catch (Exception e) {
            return new ErrorService(
                "No se obtuvo el Departamento",
                e.getMessage(),
                this.myClassName
            );
        }
    }

    public Object getByName(String name) {
        try {
            return departmentRepository.findByName(name);
        } catch (Exception e) {
            return new ErrorService(
                "No se obtuvo el Departamento",
                e.getMessage(),
                this.myClassName
            );
        }
    }

    public Object getByCode(String code) {
        try {
            return departmentRepository.findByCode(code);
        } catch (Exception e) {
            return new ErrorService(
                "No se obtuvo el Departamento",
                e.getMessage(),
                this.myClassName
            );
        }
    }

    public Object save(DepartmentDto deptoDto) {
        try {
            Department1 depto = new Department1();
            if( deptoDto.getId() != null ){
                depto.setId( deptoDto.getId() );
                depto.setCode( deptoDto.getCode() );
                depto.setName( deptoDto.getName() );
                depto.setActive( deptoDto.getActive() );
            }
            else{
                depto.setCode( deptoDto.getCode() );
                depto.setName( deptoDto.getName() );
            }
            return departmentRepository.save(depto);
        } catch (Exception e) {
            return new ErrorService(
                "No se pudo guardar el Departamento",
                e.getMessage(),
                this.myClassName
            );
        }
    }

    public Object delete(Integer id) {
        try {
            Optional<Department1> row = departmentRepository.findById(id);
            if (!row.isEmpty()) {
                departmentRepository.deleteById(id);
                return "Se eliminó el registro con ID: " + id;
            }
            return "No se encontró el registro con ID: " + id;
        } catch (Exception e) {
            return new ErrorService(
                "No se eliminó el Departamento",
                e.getMessage(),
                this.myClassName
            );
        }
    }
}
