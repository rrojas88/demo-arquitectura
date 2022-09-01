
package com.example.demo2.api.v1.local.proyecto1.logs;


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
public class LogService {
    
    @Autowired
    LogRepository logRepository;
    
    public Log save( Log log ){
        try {System.out.println("======== LOG: ");System.out.println(log);
            //return logRepository.save( log );
            //log = logRepository.saveAndFlush(log);
            return logRepository.save(log);
        }
        catch ( ConstraintViolationException e) {
            System.out.println("LogService -> ConstraintViolationException");
            System.out.println(e);System.out.println("--- ---");
            System.out.println(e.getLocalizedMessage());System.out.println("--- ---");
            System.out.println(e.getMessage());
            return new Log();
        }
        catch ( DataException e) {
            System.out.println("LogService -> DataException");
            System.out.println(e);System.out.println("--- ---");
            System.out.println(e.getLocalizedMessage());System.out.println("--- ---");
            System.out.println(e.getMessage());
            return new Log();
        }
        catch ( LockAcquisitionException e) {
            System.out.println("LogService -> LockAcquisitionException");
            System.out.println(e);System.out.println("--- ---");
            System.out.println(e.getLocalizedMessage());System.out.println("--- ---");
            System.out.println(e.getMessage());
            return new Log();
        }
        catch ( JDBCConnectionException e) {
            System.out.println("LogService -> JDBCConnectionException");
            System.out.println(e);System.out.println("--- ---");
            System.out.println(e.getLocalizedMessage());System.out.println("--- ---");
            System.out.println(e.getMessage());
            return new Log();
        }
        catch ( SQLGrammarException e) {
            System.out.println("LogService -> SQLGrammarException");
            System.out.println(e);System.out.println("--- ---");
            System.out.println(e.getLocalizedMessage());System.out.println("--- ---");
            System.out.println(e.getMessage());
            return new Log();
        }
        catch ( GenericJDBCException e) {
            System.out.println("LogService -> GenericJDBCException");
            System.out.println(e);System.out.println("--- ---");
            System.out.println(e.getLocalizedMessage());System.out.println("--- ---");
            System.out.println(e.getMessage());
            return new Log();
        }
        catch ( DataAccessException e) {
            System.out.println("LogService -> DataAccessException");
            System.out.println(e);System.out.println("--- ---");
            System.out.println(e.getLocalizedMessage());System.out.println("--- ---");
            System.out.println(e.getMessage());
            log.setDescription( e.toString() );
            return log;
        }
        catch( Exception e ) {
            System.out.println("+++ Error en SERVICIO LOG:");
            System.out.println( e.getMessage() );
            return new Log();
        }
    }
}
