
package com.example.demo2.api.v1.local.proyecto1.cities;

import java.util.ArrayList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface CityRepository extends CrudRepository<City, Integer>{
    
    public abstract ArrayList<City> findByName(String name);
    
    //@Modifying
    @Transactional
    @Query(value="SELECT c.* FROM cities c WHERE c.department_id = :department_id ", nativeQuery=true )
    //Optional<City> findAllByDepartmentId(@Param("department_id") Integer department_id);
    ArrayList<City> findAllByDepartmentId( Integer department_id );
    
}
