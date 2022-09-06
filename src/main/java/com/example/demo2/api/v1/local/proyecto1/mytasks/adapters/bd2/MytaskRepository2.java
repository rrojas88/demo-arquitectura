
package com.example.demo2.api.v1.local.proyecto1.mytasks.adapters.bd2;


import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MytaskRepository2 extends JpaRepository<Mytask2, Integer>{
    
    public abstract Optional<Mytask2> findByName(String name);
    
    public abstract Optional<Mytask2> findById(Integer id);

}
