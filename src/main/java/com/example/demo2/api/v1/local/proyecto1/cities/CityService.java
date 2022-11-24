
package com.example.demo2.api.v1.local.proyecto1.cities;

import com.example.demo2.api.v1.local.Utils.ErrorService;
import com.example.demo2.api.v1.local.proyecto1.cities.adapters.CityAdapter;
import com.example.demo2.api.v1.local.proyecto1.cities.adapters.CityDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CityService {
    
    @Autowired
    CityAdapter cityAdapter;
    
    private String myClassName = CityService.class.getName();
    
    public Object getAll(){
        try {
            return cityAdapter.getAll();
        }
        catch (Exception e) {
            return new ErrorService(
                "No se listaron las Ciudades", 
                e.getMessage(), 
                this.myClassName
            );
        }
    }
    
    public Object getById(Integer id){
        try {
            return cityAdapter.getById(id);
        }
        catch (Exception e) {
            return new ErrorService(
                "No se obtuvo la Ciudad", 
                e.getMessage(), 
                this.myClassName
            );
        }
    }

    public Object getByName(String name) {
        try {
            return cityAdapter.getByName(name);
        }
        catch (Exception e) {
            return new ErrorService(
                "No se obtuvo la Ciudad", 
                e.getMessage(), 
                this.myClassName
            );
        }
    }
    
    public Object getByDepartment_id(Integer department_id){
        try {
            return cityAdapter.getByDepartment_id(department_id);
        }
        catch (Exception e) {
            return new ErrorService(
                "No se obtuvieron ciudades", 
                e.getMessage(), 
                this.myClassName
            );
        }
    }
    
    public Object getBySql(Integer department_id){
        try {
            return cityAdapter.findAllBySql(department_id);
        }
        catch (Exception e) {
            return new ErrorService(
                "No se obtuvieron ciudades", 
                e.getMessage(), 
                this.myClassName
            );
        }
    }

    public Object save(CityDto cityDto ){
        try {
            return cityAdapter.save(cityDto);
        }
        catch (Exception e) {
            return new ErrorService(
                "No se pudo guardar la Ciudad", 
                e.getMessage(), 
                this.myClassName
            );
        }
    }

    public Object delete(Integer id) {
        try {
            return cityAdapter.delete(id);
        }
        catch(Exception e){
            return new ErrorService(
                "No se eliminó la Ciudad", 
                e.getMessage(), 
                this.myClassName
            );
        }
    }
    
}
