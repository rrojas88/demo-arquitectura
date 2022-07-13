
package com.example.demo2.api.v1.local.Utils.logs;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LogRepository extends CrudRepository<Log, Long> {
    
}
