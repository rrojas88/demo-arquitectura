
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
        if( UtilsService.isErrorService(data) )
        {
            String message_ = (( ErrorService )data).getMessage();
            String description_ = (( ErrorService )data).getDescription();
            String class_path_ = (( ErrorService )data).getClass_path();
            int code_ = (( ErrorService )data).getCode();
            this.setError( code_, 
                message_, 
                description_, 
                new ArrayList<ObjectError>(), 
                class_path_, 
                payload, 
                req
            );
            System.out.println( "===>  Error validateService:\n" + description_ );
            
            HttpStatus httpStatus = this.getHttpStatus( code_ );
            
            return httpStatus;
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
        String user_id = "";
        String user_login = "";
        UserRolesPrincipal userData = this.getUserData();
        if( userData != null ){
            user_id = userData.getId().toString();
            user_login = userData.getEmail();
        }  
        
        String url = this.getUrl(req);
        String dateTimeNow = this.getDateTimeNow();
        String ip = this.getIpAddr(req);
        
        Log log = new Log();
        log.setCode(this.code);
        log.setMessage(this.message);
        log.setDescription(description);
        log.setClass_path(class_path);
        log.setUser_id(user_id);
        log.setUser_login(user_login);
        log.setRow_date_time(dateTimeNow);
        log.setIp(ip);
        log.setUrl(url);
        log.setMethod(req.getMethod());
        log.setPayload(payload);
        //System.out.println( "\n--- LOG:\n" + log.toString() );
        
        Log row = this.logService.save(log);
    }
    
    
    public void setError(
        Integer code, 
        String message, 
        String description, 
        List<ObjectError> listErrors,
        //Object listErrors,
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
        
        String user_id = "";
        String user_login = "";
        UserRolesPrincipal userData = this.getUserData();
        if( userData != null ){
            user_id = userData.getId().toString();
            user_login = userData.getEmail();
        }      
        
        String url = this.getUrl(req);
        String dateTimeNow = this.getDateTimeNow();
        String ip = this.getIpAddr(req);
        
        Log log = new Log();
        log.setCode(this.code);
        log.setMessage(this.message);
        log.setDescription(description);
        log.setClass_path(class_path);
        log.setUser_id(user_id);
        log.setUser_login(user_login);
        log.setRow_date_time(dateTimeNow);
        log.setIp(ip);
        log.setUrl(url);
        log.setMethod(req.getMethod());
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
        if( principal.equals("anonymousUser") ) return null;
        
        UserRolesPrincipal userDetails = ( UserRolesPrincipal )principal;
        return userDetails;
    }
    
    
    private String getUrl(HttpServletRequest req){
        String queryString = ( req.getQueryString() != null )? "?" + req.getQueryString() : "";
        return req.getRequestURL().toString() + queryString;
    }
    
    
    private HttpStatus getHttpStatus( int code ){
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        if( code == 400 ) httpStatus = HttpStatus.BAD_REQUEST;
        else if( code == 401 ) httpStatus = HttpStatus.UNAUTHORIZED;
        else if( code == 403 ) httpStatus = HttpStatus.FORBIDDEN;
        else if( code == 404 ) httpStatus = HttpStatus.NOT_FOUND;
        else if( code == 405 ) httpStatus = HttpStatus.METHOD_NOT_ALLOWED;
        else if( code == 406 ) httpStatus = HttpStatus.NOT_ACCEPTABLE;
        else if( code == 409 ) httpStatus = HttpStatus.CONFLICT;
        else if( code == 408 ) httpStatus = HttpStatus.REQUEST_TIMEOUT;
        
        else if( code == 501 ) httpStatus = HttpStatus.NOT_IMPLEMENTED;
        else if( code == 502 ) httpStatus = HttpStatus.BAD_GATEWAY;
        else if( code == 503 ) httpStatus = HttpStatus.SERVICE_UNAVAILABLE;
        else if( code == 504 ) httpStatus = HttpStatus.GATEWAY_TIMEOUT;

        return httpStatus;
    }
}
