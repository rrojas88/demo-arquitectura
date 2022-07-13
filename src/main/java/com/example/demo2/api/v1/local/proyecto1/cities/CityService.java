
package com.example.demo2.api.v1.local.proyecto1.cities;

import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CityService {
    
    @Autowired
    CityRepository cityRepository;
    
    public ArrayList<City> getAll(){
        return (ArrayList<City>) cityRepository.findAll();
    }
    
    public Optional<City> getById(Integer id){
        return cityRepository.findById(id);
    }

    public ArrayList<City> getByName(String name) {
        return cityRepository.findByName(name);
    }

    public City save(City city){
        return cityRepository.save(city);
    }

    public boolean delete(Integer id) {
        try{
            cityRepository.deleteById(id);
            return true;
        }catch(Exception err){
            return false;
        }
    }
    
}
