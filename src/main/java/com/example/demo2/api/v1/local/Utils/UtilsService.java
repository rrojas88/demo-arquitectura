
package com.example.demo2.api.v1.local.Utils;



public class UtilsService {
    
    public static boolean isErrorService( Object object ){
        String typeData = (object != null) ? object.getClass().getSimpleName() : "";
        if( typeData.equals("ErrorService") )
            return true;
        return false;
    }
    
}
