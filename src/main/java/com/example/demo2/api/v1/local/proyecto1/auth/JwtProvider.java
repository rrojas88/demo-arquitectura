
package com.example.demo2.api.v1.local.proyecto1.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;


// Genera el Token, y lo valida
@Component
public class JwtProvider {
    
    // Depurar
    private final static Logger logger = LoggerFactory.getLogger(JwtProvider.class );
    
    @Value("${jwt.secret}")
    private String secret;
    
    @Value("${jwt.expiration}")
    private int expiration;
    
    public String generateToken( Authentication auth ){
        return "";
    }
    
    
    
}
