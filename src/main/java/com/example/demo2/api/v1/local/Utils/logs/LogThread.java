
package com.example.demo2.api.v1.local.Utils.logs;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;


public class LogThread extends Thread {
    
    //@Autowired
    //LogService logService;
    
    private Log log;
    
    public LogThread(){}
    
    public LogThread( Integer code, String message,
        String description, String class_path,
        String url, String user_id,
        String user_login, String ip, 
        String payload
    ){
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        String dateTimeNow = DateTime.now(DateTimeZone.forID("America/Bogota")).toString(formatter);
        
        this.log = new Log();
        this.log.setCode(code);
        this.log.setMessage(message);
        this.log.setDescription(description);
        this.log.setClass_path(class_path);
        this.log.setUrl(url);
        this.log.setUser_id(user_id);
        this.log.setUser_login(user_login);
        this.log.setRow_date_time(dateTimeNow);
        this.log.setIp(ip);
        this.log.setPayload(payload);
    }
    
    @Override
    public void run()
    {
        System.out.println("\n\n+++ Codigo: "+this.log.getCode());
        System.out.println(log.getRow_date_time());
        System.out.println(log.getMessage());
        System.out.println("--->getPayload:\n"+log.getPayload());
        try {
            //this.logService = new LogService();
            //Log row = this.logService.save(this.log);
            LogService logService = new LogService();
            Log row = logService.save(this.log);
        } catch (Exception e) {
            System.out.println("+++ Error en HILO:");
            System.out.println(e.getMessage());
            System.out.println( e.getLocalizedMessage() );
        }
    }
    
}
