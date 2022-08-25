
package com.example.demo2.api.v1.local.proyecto1.logs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LogService {
    
    @Autowired
    LogRepository logRepository;
    
    public Log save( Log log ){
        try {
            return logRepository.save( log );
        }
        catch( Exception e ) {
            System.out.println("+++ Error en SERVICIO LOG:");
            System.out.println( e.getMessage() );
            return new Log();
        }
    }
}
