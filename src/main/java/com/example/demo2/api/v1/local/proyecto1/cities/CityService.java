
package com.example.demo2.api.v1.local.proyecto1.cities;

import com.example.demo2.api.v1.local.Utils.ErrorService;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;


@Service
public class CityService {
    
    @Autowired
    CityRepository cityRepository;
    
    private String myClassName = CityService.class.getName();
    
    public Object getAll(){
        try {
            return cityRepository.findAll();
        }
        catch (Exception e) {
            ErrorService errService = new ErrorService(
                "No se listaron las Ciudades", 
                e.getMessage(), 
                this.myClassName
            );
            return errService;
        }
    }
    
    public Object getById(Integer id){
        try {
            return cityRepository.findById(id);
        }
        catch (Exception e) {
            ErrorService errService = new ErrorService(
                "No se obtuvo la Ciudad", 
                e.getMessage(), 
                this.myClassName
            );
            return errService;
        }
    }

    public Object getByName(String name) {
        try {
            return cityRepository.findByName(name);
        }
        catch (Exception e) {
            ErrorService errService = new ErrorService(
                "No se obtuvo la Ciudad", 
                e.getMessage(), 
                this.myClassName
            );
            return errService;
        }
    }

    public Object save(CityDto cityDto ){
        try {
            City city = new City();
            if( cityDto.getId() != null ){
                city.setId( cityDto.getId() );
                city.setDepartment_id( cityDto.getDepartment_id() );
                city.setCode( cityDto.getCode() );
                city.setName( cityDto.getName() );
                city.setActive( cityDto.getActive() );
            }
            else{
                city.setDepartment_id( cityDto.getDepartment_id() );
                city.setCode( cityDto.getCode() );
                city.setName( cityDto.getName() );
            }
            return cityRepository.save(city);
        }
        catch (Exception e) {
            ErrorService errService = new ErrorService(
                "No se pudo guardar la Ciudad", 
                e.getMessage(), 
                this.myClassName
            );
            return errService;
        }
    }

    public Object delete(Integer id) {
        try {
            Optional<City> row = cityRepository.findById(id);
            if( ! row.isEmpty() ){
                cityRepository.deleteById(id);
                return true;
            }
            return false;
        }
        catch(Exception e){
            ErrorService errService = new ErrorService(
                "No se eliminó la Ciudad", 
                e.getMessage(), 
                this.myClassName
            );
            return errService;
        }
    }
    
}
