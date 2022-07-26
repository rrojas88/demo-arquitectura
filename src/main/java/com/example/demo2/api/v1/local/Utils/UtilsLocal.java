
package com.example.demo2.api.v1.local.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import org.springframework.validation.ObjectError;


public class UtilsLocal {
    
    public static ArrayList<ObjectError> emptyErrorList(){
        return new ArrayList<ObjectError>();
    }
    
    public static String arrayStringToString( String [] array ){
        /*
        StringBuffer str = new StringBuffer();
        for( int x = 0; x < array.length; x++ ){
            str = str.append( array[ x ] );
        }
        return str.toString(); */
        //String str = Arrays.toString(array);
        String str = String.join(", ", array );
        return str;
    }
    
}
