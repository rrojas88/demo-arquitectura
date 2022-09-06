
package com.example.demo2.api.v1.local.proyecto1.logs.adapters;

import java.time.ZonedDateTime;

public abstract class Log {
    
    Long id;
    Integer code;
    String message;
    String description;
    String class_path;
    String user_id;
    String user_login;
    ZonedDateTime row_date_time;
    String ip;
    String url;
    String method;
    String payload;

    public abstract Long getId();

    public abstract void setId(Long id);

    public abstract Integer getCode();

    public abstract void setCode(Integer code);

    public abstract String getMessage();

    public abstract void setMessage(String message);

    public abstract String getDescription();

    public abstract void setDescription(String description);

    public abstract String getClass_path();

    public abstract void setClass_path(String class_path);

    public abstract String getUser_id();

    public abstract void setUser_id(String user_id);

    public abstract String getUser_login();

    public abstract void setUser_login(String user_login);

    public abstract ZonedDateTime getRow_date_time();

    public abstract void setRow_date_time(ZonedDateTime row_date_time);

    public abstract String getIp();

    public abstract void setIp(String ip);
    
    public abstract String getUrl();

    public abstract void setUrl(String url);
    
    public abstract String getMethod();

    public abstract void setMethod(String method);

    public abstract String getPayload();

    public abstract void setPayload(String payload);
    
}

