
package com.example.demo2.api.v1.local.proyecto1.logs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LogRepository extends JpaRepository<Log, Long> {
    
}
