
package com.example.demo2.api.v1.local.Utils;



public class ErrorService {
    
    public String message;
    public String description;
    public String class_path;
    public int code;

    public ErrorService(String message, String description, String class_path) {
        if( message.equals("No hay registro") ){
            this.message = "No se encontró el registro";
            this.description = "No se encontró el registro con ID: "+description;
            this.code = 404;
        }
        else{
            this.message = message;
            this.description = description;
            this.code = 500;
        }
        this.class_path = class_path;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getClass_path() {
        return class_path;
    }

    public void setClass_path(String class_path) {
        this.class_path = class_path;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
