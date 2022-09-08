
package com.example.demo2.api.v1.local.proyecto1.actions.adapters.bd1;

import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface ActionRepository1 extends JpaRepository<Action1, Integer>{
    
    public abstract ArrayList<Action1> findByName(String name);
    
    @Transactional
    @Query(value="SELECT a.* FROM actions a WHERE a.module_id = :module_id ", nativeQuery=true )
    ArrayList<Action1> findAllByModuleId( Integer module_id );
    
    @Transactional
    @Query(value="SELECT a.* FROM actions a WHERE a.module_id = :module_id AND a.code = :code ", nativeQuery=true )
    ArrayList<Action1> findAllByModuleIdAndCode( Integer module_id, String code );
    
}
