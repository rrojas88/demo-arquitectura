
package com.example.demo2.api.v1.local.Utils;

import com.example.demo2.api.v1.local.proyecto1.auth.UserRolesPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;


public class UtilsPermissions {
    
    /*public boolean success;
    public Integer code;
    public Object data;
    public String message;
    public String description;
    
    public Object setForbidden (){
        this.success = false;
        this.code = 403;
        //this.data = null;
        this.message = "No autorizado";
        this.description = "No autorizado";
        return this;
    }*/
    
    public static UserRolesPrincipal getUserData(  ){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if( principal.equals("anonymousUser") ) return null;
        
        UserRolesPrincipal userDetails = ( UserRolesPrincipal )principal;
        return userDetails;
    }
    
}
