
package com.example.demo2.api.v1.local.proyecto1.cities;

import java.util.ArrayList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CityRepository extends CrudRepository<City, Integer>{
    
    public abstract ArrayList<City> findByName(String name);
    
}
