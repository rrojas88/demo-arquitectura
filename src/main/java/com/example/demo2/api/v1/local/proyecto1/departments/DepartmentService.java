
package com.example.demo2.api.v1.local.proyecto1.departments;

import com.example.demo2.api.v1.local.Utils.ErrorService;
import com.example.demo2.api.v1.local.Utils.UtilsService;
import com.example.demo2.api.v1.local.proyecto1.cities.CityService;
import com.example.demo2.api.v1.local.proyecto1.departments.adapters.DepartmentAdapter;
import com.example.demo2.api.v1.local.proyecto1.departments.adapters.DepartmentDto;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DepartmentService {

    @Autowired
    DepartmentAdapter departmentAdapter;
    
    @Autowired
    CityService cityService;

    private String myClassName = DepartmentService.class.getName();

    public Object getAll() {
        try {
            return departmentAdapter.getAll();
        } catch (Exception e) {
            return new ErrorService(
                "No se listaron los Departamentos",
                e.getMessage(),
                this.myClassName
            );
        }
    }

    public Object getById(Integer id) {
        try {
            return departmentAdapter.getById(id);
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
            return departmentAdapter.getByName(name);
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
            return departmentAdapter.getByCode(code);
        } catch (Exception e) {
            return new ErrorService(
                "No se obtuvo el Departamento",
                e.getMessage(),
                this.myClassName
            );
        }
    }

    public Object save(DepartmentDto depto) {
        try {
            return departmentAdapter.save(depto);
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
            Object cities = cityService.getByDepartment_id(id);
            if (UtilsService.isErrorService(cities)) {
                return cities;
            }
            if (cities != null && !((ArrayList) cities).isEmpty()) {
                return new ErrorService(
                    "No se puede eliminar el departamento porque tiene ciudades asociadas",
                    "",
                    this.myClassName
                );
            }
            return departmentAdapter.delete(id);
        } catch (Exception e) {
            return new ErrorService(
                "No se eliminó el Departamento",
                e.getMessage(),
                this.myClassName
            );
        }
    }
}
