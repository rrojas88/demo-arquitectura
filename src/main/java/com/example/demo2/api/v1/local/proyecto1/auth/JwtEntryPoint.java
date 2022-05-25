
package com.example.demo2.api.v1.local.proyecto1.auth;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;


// Comprueba si Hay token valido, sino regresa: 401
@Component
public class JwtEntryPoint implements AuthenticationEntryPoint {
    
    // Depurar
    private final static Logger logger = LoggerFactory.getLogger(JwtEntryPoint.class );

    @Override
    public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException e ) throws IOException, ServletException {
        logger.error("Ha fallado el Metodo: commence en JwtEntryPoint.");
        
        res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "No autorizado");
    }
    
    
    
}
