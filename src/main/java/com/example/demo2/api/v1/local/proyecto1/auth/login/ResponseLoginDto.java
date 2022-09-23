
package com.example.demo2.api.v1.local.proyecto1.auth.login;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;



public class ResponseLoginDto {
    
    private String token;
    private String bearer = "Bearer";
    private String email;
    private String name;
    private String extra;
    private Collection<? extends GrantedAuthority> authorities;

    public ResponseLoginDto(
        String token, 
        String email, 
        String name, 
         String extra,
        Collection<? extends GrantedAuthority> authorities
    ) {
        this.token = token;
        this.email = email;
        this.name = name;
        this.extra = extra;
        this.authorities = authorities;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getBearer() {
        return bearer;
    }

    public void setBearer(String bearer) {
        this.bearer = bearer;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
    
    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }
    
}
