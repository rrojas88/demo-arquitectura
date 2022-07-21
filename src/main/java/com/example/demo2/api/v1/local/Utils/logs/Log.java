
package com.example.demo2.api.v1.local.Utils.logs;

import javax.persistence.*;


@Entity
@Table(name = "logs")
public class Log {
    
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( unique = true, nullable = false )
    private long id;
    
    private Integer code;
    private String message;
    private String description;
    private String class_path;
    private String user_id;
    private String user_login;
    @Column( nullable = false )
    private String row_date_time;
    private String ip;
    private String url;
    private String method;
    private String payload;
    
    public Log() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
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

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_login() {
        return user_login;
    }

    public void setUser_login(String user_login) {
        this.user_login = user_login;
    }

    public String getRow_date_time() {
        return row_date_time;
    }

    public void setRow_date_time(String row_date_time) {
        this.row_date_time = row_date_time;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
    
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "Log{" + "id=" + id + ", code=" + code + ", message=" + message + ", description=" + description + ", class_path=" + class_path + ", user_id=" + user_id + ", user_login=" + user_login + ", row_date_time=" + row_date_time + ", ip=" + ip + ", url=" + url + ", method=" + method + ", payload=" + payload + '}';
    }
    
}

