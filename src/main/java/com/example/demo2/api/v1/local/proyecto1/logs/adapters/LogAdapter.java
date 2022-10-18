
package com.example.demo2.api.v1.local.proyecto1.logs.adapters;

import com.example.demo2.api.v1.local.proyecto1.logs.adapters.bd1.Log;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.DataException;
import org.hibernate.exception.GenericJDBCException;
import org.hibernate.exception.JDBCConnectionException;
import org.hibernate.exception.LockAcquisitionException;
import org.hibernate.exception.SQLGrammarException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import com.example.demo2.api.v1.local.proyecto1.logs.adapters.bd1.LogRepository;


@Service
public class LogAdapter {
    
    @Autowired
    LogRepository logRepository;
    
    public Log save( Log log ){
        try {
            System.out.println("======== LOG x2: ");System.out.println(log);
            return logRepository.save(log);
        }
        catch ( ConstraintViolationException e) {
            System.out.println("LogAdapter -> ConstraintViolationException");
            System.out.println(e);System.out.println("--- ---");
            System.out.println(e.getLocalizedMessage());System.out.println("--- ---");
            System.out.println(e.getMessage());
            return new Log();
        }
        catch ( DataException e) {
            System.out.println("LogAdapter -> DataException");
            System.out.println(e);System.out.println("--- ---");
            System.out.println(e.getLocalizedMessage());System.out.println("--- ---");
            System.out.println(e.getMessage());
            return new Log();
        }
        catch ( LockAcquisitionException e) {
            System.out.println("LogAdapter -> LockAcquisitionException");
            System.out.println(e);System.out.println("--- ---");
            System.out.println(e.getLocalizedMessage());System.out.println("--- ---");
            System.out.println(e.getMessage());
            return new Log();
        }
        catch ( JDBCConnectionException e) {
            System.out.println("LogAdapter -> JDBCConnectionException");
            System.out.println(e);System.out.println("--- ---");
            System.out.println(e.getLocalizedMessage());System.out.println("--- ---");
            System.out.println(e.getMessage());
            return new Log();
        }
        catch ( SQLGrammarException e) {
            System.out.println("LogAdapter -> SQLGrammarException");
            System.out.println(e);System.out.println("--- ---");
            System.out.println(e.getLocalizedMessage());System.out.println("--- ---");
            System.out.println(e.getMessage());
            return new Log();
        }
        catch ( GenericJDBCException e) {
            System.out.println("LogAdapter -> GenericJDBCException");
            System.out.println(e);System.out.println("--- ---");
            System.out.println(e.getLocalizedMessage());System.out.println("--- ---");
            System.out.println(e.getMessage());
            return new Log();
        }
        catch ( DataAccessException e) {
            System.out.println("LogAdapter -> DataAccessException");
            System.out.println(e);System.out.println("--- ---");
            System.out.println(e.getLocalizedMessage());System.out.println("--- ---");
            System.out.println(e.getMessage());
            log.setDescription( e.toString() );
            return log;
        }
        catch( Exception e ) {
            System.out.println("+++ Error en LogAdapter LOG:");
            System.out.println( e.getMessage() );
            return new Log();
        }
    }
}
