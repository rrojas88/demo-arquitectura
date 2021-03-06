
package com.example.demo2.api.v1.local.proyecto1.cities;

import com.example.demo2.api.v1.local.Utils.ErrorService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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
    
    public Object getByDepartment_id(Integer department_id){
        try {
            return cityRepository.findAllByDepartmentId(department_id);
        }
        catch (Exception e) {
            ErrorService errService = new ErrorService(
                "No se obtuvieron ciudades", 
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
                return "Se elimin?? el registro con ID: " + id;
            }
            return "No se encontr?? el registro con ID: " + id;
        }
        catch(Exception e){
            ErrorService errService = new ErrorService(
                "No se elimin?? la Ciudad", 
                e.getMessage(), 
                this.myClassName
            );
            return errService;
        }
    }
    
}
