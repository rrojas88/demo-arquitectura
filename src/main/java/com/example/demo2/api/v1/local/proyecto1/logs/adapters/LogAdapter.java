
package com.example.demo2.api.v1.local.proyecto1.logs.adapters;

import com.example.demo2.api.v1.local.proyecto1.logs.adapters.bd1.Log1;
import com.example.demo2.api.v1.local.proyecto1.logs.adapters.bd1.LogRepository1;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.DataException;
import org.hibernate.exception.GenericJDBCException;
import org.hibernate.exception.JDBCConnectionException;
import org.hibernate.exception.LockAcquisitionException;
import org.hibernate.exception.SQLGrammarException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;


@Service
public class LogAdapter {
    
    @Autowired
    LogRepository1 logRepository;
    
    public Log1 save( Log1 log ){
        try {
            System.out.println("======== LOG x2: ");System.out.println(log);
            return logRepository.save(log);
        }
        catch ( ConstraintViolationException e) {
            System.out.println("LogAdapter -> ConstraintViolationException");
            System.out.println(e);System.out.println("--- ---");
            System.out.println(e.getLocalizedMessage());System.out.println("--- ---");
            System.out.println(e.getMessage());
            return new Log1();
        }
        catch ( DataException e) {
            System.out.println("LogAdapter -> DataException");
            System.out.println(e);System.out.println("--- ---");
            System.out.println(e.getLocalizedMessage());System.out.println("--- ---");
            System.out.println(e.getMessage());
            return new Log1();
        }
        catch ( LockAcquisitionException e) {
            System.out.println("LogAdapter -> LockAcquisitionException");
            System.out.println(e);System.out.println("--- ---");
            System.out.println(e.getLocalizedMessage());System.out.println("--- ---");
            System.out.println(e.getMessage());
            return new Log1();
        }
        catch ( JDBCConnectionException e) {
            System.out.println("LogAdapter -> JDBCConnectionException");
            System.out.println(e);System.out.println("--- ---");
            System.out.println(e.getLocalizedMessage());System.out.println("--- ---");
            System.out.println(e.getMessage());
            return new Log1();
        }
        catch ( SQLGrammarException e) {
            System.out.println("LogAdapter -> SQLGrammarException");
            System.out.println(e);System.out.println("--- ---");
            System.out.println(e.getLocalizedMessage());System.out.println("--- ---");
            System.out.println(e.getMessage());
            return new Log1();
        }
        catch ( GenericJDBCException e) {
            System.out.println("LogAdapter -> GenericJDBCException");
            System.out.println(e);System.out.println("--- ---");
            System.out.println(e.getLocalizedMessage());System.out.println("--- ---");
            System.out.println(e.getMessage());
            return new Log1();
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
            return new Log1();
        }
    }
}
