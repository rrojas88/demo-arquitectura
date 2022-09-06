
package com.example.demo2.api.v1.local.proyecto1.users.adapters.bd1;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository1 extends JpaRepository<User1, Integer>{
    
    Optional<User1> findByName( String name );
    boolean existsByName( String name );
    
    //Optional<User> findByEmail( String email );
    Object findByEmail( String email );
    boolean existsByEmail( String email );
    
}
