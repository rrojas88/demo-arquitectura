
package com.example.demo2.api.v1.local.proyecto1.logs;


import java.time.ZoneId;
import java.time.ZonedDateTime;
import javax.servlet.http.HttpServletRequest;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/v1/local/auth/log")
public class LogCtrl {
    
    @Autowired
    LogService logService;
    
    @GetMapping( path = "/test")
    public ResponseEntity<?> getAll( HttpServletRequest req ){
        System.out.println("\n... ... TestCtrl -> Pasa x1 ");
        Log log = new Log();
        log.setCode(300);
        log.setMessage("Prueba");
        log.setDescription("Probando");
        log.setClass_path("Ruta...");
        log.setUser_id("100");
        log.setUser_login("el user");
        log.setRow_date_time( this.getDateTimeNow() );
        log.setIp("Ip");
        log.setUrl("Url abc");
        log.setMethod("Metodo..");
        log.setPayload("Bodyyy");
        
        try {
            Object row = logService.save( log );
            return new ResponseEntity<Object>( row, HttpStatus.OK );
        } catch (Exception e) {
            String msg = e.getMessage();
            System.out.println("TestCtrl -> Exception");
            System.out.println(e);
            return new ResponseEntity<>( e, HttpStatus.BAD_REQUEST );
        }
    }
    
    private ZonedDateTime getDateTimeNow(){
        /*
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        String dateTimeNow = DateTime.now(DateTimeZone.forID("America/Bogota")).toString(formatter);
        return LocalDateTime.parse(dateTimeNow, formatter); */
        
        //Date date_ = LocalDate.now(DateTimeZone.forID("America/Bogota")).toDate();
        
        //ZonedDateTime date_ = ZonedDateTime.now();
        /*
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        String dateTimeNow = DateTime.now(DateTimeZone.forID("America/Bogota")).toString(formatter);
        ZonedDateTime date_ = ZonedDateTime.parse(dateTimeNow, java.time.format.DateTimeFormatter.ISO_DATE); */
        ZoneId zoneIdCOL = ZoneId.of("America/Bogota");
        ZonedDateTime date_ = ZonedDateTime.now(zoneIdCOL);
        
        System.out.println("\n+++ +++ TestCtrl -> getDateTimeNow()");
        System.out.println(date_);
        return date_;
    }
    
}
