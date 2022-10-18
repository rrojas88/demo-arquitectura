
package com.example.demo2.api.v1.local.proyecto1.mytasks.adapters.bd2;


import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MytaskRepository extends JpaRepository<Mytask, Integer>{
    
    public abstract Optional<Mytask> findByName(String name);
    
    public abstract Optional<Mytask> findById(Integer id);

}
