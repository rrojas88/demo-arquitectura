
package com.example.demo2.api.v1.local.proyecto1.categories.adapters.bd1;

import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface CategoryRepository1 extends JpaRepository<Category1, Integer> {
    
    
    @Transactional
    @Query(value="SELECT c.* FROM categories c WHERE c.name like %:name% ", nativeQuery=true )
    ArrayList<Category1> findByName( String name );
    
    ArrayList<Category1> findByNameContains( String name );
    
}
