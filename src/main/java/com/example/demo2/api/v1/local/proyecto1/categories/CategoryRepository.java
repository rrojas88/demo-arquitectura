
package com.example.demo2.api.v1.local.proyecto1.categories;

import java.util.ArrayList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;


public interface CategoryRepository extends CrudRepository<Category, Integer> {
    
    
    @Transactional
    @Query(value="SELECT c.* FROM categories c WHERE c.name like %:name% ", nativeQuery=true )
    ArrayList<Category> findByName( String name );
    
    ArrayList<Category> findByNameContains( String name );
    
}
