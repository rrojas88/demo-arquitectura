
package com.example.demo2.api.v1.local.proyecto1.cities.adapters.bd1;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class CityRepository1Impl {
    
    @PersistenceContext
     private EntityManager em;
    
    public List<?> runMyQueryBySql( String sql ) {
        return em.createNativeQuery( sql
          ).getResultList();
    }
    
}
