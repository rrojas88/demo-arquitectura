
package com.example.demo2.api.v1.local.Utils;

import com.example.demo2.api.v1.local.Utils.logs.Log;
import com.example.demo2.api.v1.local.Utils.logs.LogService;
import com.example.demo2.api.v1.local.proyecto1.auth.UserRolesPrincipal;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.ObjectError;


public class ResponseLocal {
    
    public Integer code;
    public Object data;
    public String message;
    
    private LogService logService;
    
    public ResponseLocal( LogService logService ){
        this.code = 200;
        this.logService = logService;
    }
    
    /*
    public ResponseLocal( Integer code, String message, 
        ArrayList data, HttpServletRequest req
    ){
        if( code == null ) this.code = 200;
        else this.code = code;
    } */
    
    public HttpStatus validateService(
        Object data,
        String message,
        String class_path,
        String payload,
        HttpServletRequest req
    ){
        System.out.println( "\n============ validateService ================" );
        String typeData = (data != null) ? data.getClass().getSimpleName() : "";
        //System.out.println( "===> typeData: " + typeData );
        if( typeData.equals("ErrorService") )
        {
            String message_ = (( ErrorService )data).getMessage();
            String description_ = (( ErrorService )data).getDescription();
            String class_path_ = (( ErrorService )data).getClass_path();
            this.setError( 500, 
                message_, 
                description_, 
                new ArrayList<ObjectError>(), 
                class_path_, 
                payload, 
                req
            );
            System.out.println( "===>  Error validateService:\n" + message );
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        else
        {
            this.code = 200;
            this.message = message;
            this.data = data;
            this.setSuccess( 
                class_path, 
                payload, 
                req
            );
            System.out.println( "===> OK validateService:\n" + message );
            return HttpStatus.OK;
        }
    }
    
    
    public void setSuccess(
        //Object data,
        //String message,
        String class_path,
        String payload,
        HttpServletRequest req
    ){
        //this.code = 200;
        //this.message = message;
        //this.data = data;
        
        String description = "";
        UserRolesPrincipal userData = this.getUserData();
        String user_id = userData.getId().toString();
        String user_login = userData.getEmail();
        
        String url = this.getUrl(req);
        String dateTimeNow = this.getDateTimeNow();
        String ip = this.getIpAddr(req);
        
        Log log = new Log();
        log.setCode(this.code);
        log.setMessage(this.message);
        log.setDescription(description);
        log.setClass_path(class_path);
        log.setUrl(url);
        log.setUser_id(user_id);
        log.setUser_login(user_login);
        log.setRow_date_time(dateTimeNow);
        log.setIp(ip);
        log.setPayload(payload);
        System.out.println( "\n--- LOG:\n" + log.toString() );
        
        Log row = this.logService.save(log);
    }
    
    
    public void setError( Integer code, 
        String message, 
        String description, 
        List<ObjectError> listErrors,
        //List<Object> listErrors,
        String class_path,
        String payload,
        HttpServletRequest req
    ){
        if( code == null ) this.code = 500;
        else this.code = code;
        
        if( listErrors.size() != 0 ){
            StringBuilder messages_ = new StringBuilder();
            listErrors.forEach( err -> messages_.append( "- "+err.getDefaultMessage() ) );
            this.message = messages_.toString();
        }
        else{
            this.message = message;
        }
        if( description == null || description.equals("") ) description = this.message;
        
        UserRolesPrincipal userData = this.getUserData();
        String user_id = userData.getId().toString();
        String user_login = userData.getEmail();
        
        String url = this.getUrl(req);
        String dateTimeNow = this.getDateTimeNow();
        String ip = this.getIpAddr(req);
        
        Log log = new Log();
        log.setCode(this.code);
        log.setMessage(this.message);
        log.setDescription(description);
        log.setClass_path(class_path);
        log.setUrl(url);
        log.setUser_id(user_id);
        log.setUser_login(user_login);
        log.setRow_date_time(dateTimeNow);
        log.setIp(ip);
        log.setPayload(payload);
        System.out.println( "\n--- LOG:\n" + log.toString() );
        
        Log row = this.logService.save(log);
    }
    
    
    public String getIpAddr(HttpServletRequest req ) {
       String ip = req.getHeader("x-forwarded-for");
       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
           ip = req.getHeader("Proxy-Client-IP");
       }
       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
           ip = req.getHeader("WL-Proxy-Client-IP");
       }
       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
           ip = req.getRemoteAddr();
       }
       return ip;
   } 
    
    private String getDateTimeNow(){
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        String dateTimeNow = DateTime.now(DateTimeZone.forID("America/Bogota")).toString(formatter);
        return dateTimeNow;
    }
    
    private UserRolesPrincipal getUserData(  ){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserRolesPrincipal userDetails = ( UserRolesPrincipal )principal;
        return userDetails;
    }
    
    private String getUrl(HttpServletRequest req){
        String queryString = ( req.getQueryString() != null )? "?" + req.getQueryString() : "";
        return req.getRequestURL().toString() + queryString;
    }
    
}
