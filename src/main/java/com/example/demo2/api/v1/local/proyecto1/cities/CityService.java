
package com.example.demo2.api.v1.local.proyecto1.cities;

import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CityService {
    
    @Autowired
    CityRepository cityRepository;
    
    public ArrayList<City> obtenerTodos(){
        return (ArrayList<City>) cityRepository.findAll();
    }

    public City guardar(City city){
        return cityRepository.save(city);
    }

    public Optional<City> obtenerPorId(Integer id){
        return cityRepository.findById(id);
    }

    public ArrayList<City> obtenerPorNombre(String name) {
        return cityRepository.findByName(name);
    }

    public boolean eliminar(Integer id) {
        try{
            cityRepository.deleteById(id);
            return true;
        }catch(Exception err){
            return false;
        }
    }
    
}
