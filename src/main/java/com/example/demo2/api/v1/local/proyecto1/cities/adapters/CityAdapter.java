
package com.example.demo2.api.v1.local.proyecto1.cities.adapters;

import com.example.demo2.api.v1.local.Utils.ErrorService;
import com.example.demo2.api.v1.local.proyecto1.cities.adapters.bd1.City;
import com.example.demo2.api.v1.local.proyecto1.cities.adapters.bd1.CityDeptosDto;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo2.api.v1.local.proyecto1.cities.adapters.bd1.CityRepository1;
import java.util.ArrayList;


@Service
public class CityAdapter {
    
    @Autowired
    CityRepository1 cityRepository;
    
    private String myClassName = CityAdapter.class.getName();
    
    public Object getAll(){
        try {
            return cityRepository.findAll();
            //return new Object();
        }
        catch (Exception e) {
            return new ErrorService(
                "No se listaron las Ciudades.", 
                e.getMessage(), 
                this.myClassName
            );
        }
    }
    
    public Object getById(Integer id){
        try {
            Optional<City> rowOptional = cityRepository.findById(id);
            if( ! rowOptional.isPresent() || rowOptional.isEmpty() )
                return new ErrorService(id.toString(), "", this.myClassName, 404 );
            return rowOptional;
            
            //return new Object();
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
            return cityRepository.findByName(name);
            //return new Object();
        }
        catch (Exception e) {
            return new ErrorService(
                "No se obtuvo la Ciudad.", 
                e.getMessage(), 
                this.myClassName
            );
        }
    }
    
    public Object getByDepartment_id(Integer department_id){
        try {
            return cityRepository.findAllByDepartmentId(department_id);
            //return new Object();
        }
        catch (Exception e) {
            return new ErrorService(
                "No se obtuvieron ciudades.", 
                e.getMessage(), 
                this.myClassName
            );
        }
    }
    
    public Object findAllBySql(Integer department_id){
        try {         
            var sql = "SELECT c.id AS code_mun, c.name AS mun, "
                + "d.id AS code_dep, d.name AS dep "
                + "FROM cities c "
                + "JOIN departments d ON d.id = c.department_id "
                + "WHERE c.department_id = 1 ";
            
            Object resDataDb = cityRepository.runMyQueryBySql( sql );
            ArrayList<Object> arrData = ( ArrayList<Object> ) resDataDb;
            
            ArrayList<CityDeptosDto> listData = new ArrayList<CityDeptosDto>();
            for( Object item :  arrData )
            {
                CityDeptosDto resData = new CityDeptosDto( ( Object[] ) item );
                listData.add( resData );
            }
            return listData;
        }
        catch (Exception e) {
            return new ErrorService(
                "No se obtuvieron ciudades..", 
                e.getMessage(), 
                this.myClassName
            );
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
            //return new Object();
        }
        catch (Exception e) {
            return new ErrorService(
                "No se pudo guardar la Ciudad.", 
                e.getMessage(), 
                this.myClassName
            );
        }
    }

    public Object delete(Integer id) {
        try { 
            Optional<City> row = cityRepository.findById(id);
            if( ! row.isEmpty() ){
                cityRepository.deleteById(id);
                return "Se eliminó el registro con ID: " + id;
            } 
            return "No se encontró el registro con ID: " + id;
        }
        catch(Exception e){
            return new ErrorService(
                "No se eliminó la Ciudad.", 
                e.getMessage(), 
                this.myClassName
            );
        }
    }
}
