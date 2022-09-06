
package com.example.demo2.api.v1.local.proyecto1.logs;


import com.example.demo2.api.v1.local.proyecto1.logs.adapters.LogAdapter;
import com.example.demo2.api.v1.local.proyecto1.logs.adapters.bd1.Log1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LogService {
    
    @Autowired
    LogAdapter logAdapter;
    
    public Log1 save( Log1 log ){
        try {System.out.println("======== LOG: ");
            return logAdapter.save(log);
        }
        catch( Exception e ) {
            System.out.println("+++ Error en SERVICIO LOG:");
            System.out.println( e.getMessage() );
            return new Log1();
        }
    }
}
