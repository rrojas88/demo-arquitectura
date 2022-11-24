
package com.example.demo2.api.v1.local.proyecto1.cities.adapters.bd1;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface CityRepository1 extends JpaRepository<City, Integer> {
    
    public abstract ArrayList<City> findByName(String name);
    
    @Transactional
    @Query(value="SELECT c.* FROM cities c WHERE c.department_id = :department_id ", nativeQuery=true )
    ArrayList<City> findAllByDepartmentId( Integer department_id );
    

    @Transactional
    @Query(value="SELECT c.id AS code_mun, c.name AS mun, "
        + "d.id AS code_dep, d.name AS dep "
        + "FROM cities c "
        + "JOIN departments d ON d.id = c.department_id "
        + "WHERE c.department_id = :department_id ", 
        countQuery = "SELECT count(*) FROM cities ",
        nativeQuery=true )
    ArrayList<City> findAllBySql( Integer department_id );

    
    @Transactional
    public List<?> runMyQueryBySql( String sql ) ;
    
}
