
//package com.example.demo2.api.v1.local.proyecto1.cities;
package com.example.demo2.api.v1.local.proyecto1.cities.adapters.bd1;

import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface CityRepository1 extends JpaRepository<City, Integer>{
    
    public abstract ArrayList<City> findByName(String name);
    
    @Transactional
    @Query(value="SELECT c.* FROM cities c WHERE c.department_id = :department_id ", nativeQuery=true )
    ArrayList<City> findAllByDepartmentId( Integer department_id );
    
}
