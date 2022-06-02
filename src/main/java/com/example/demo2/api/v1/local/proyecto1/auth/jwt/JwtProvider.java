
package com.example.demo2.api.v1.local.proyecto1.auth.jwt;

import com.example.demo2.api.v1.local.proyecto1.auth.UserRolesPrincipal;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;


// Genera el Token, Descompone el Token y  tsmuién lo valida
@Component
public class JwtProvider {
    
    // Depurar
    private final static Logger logger = LoggerFactory.getLogger(JwtProvider.class );
    
    @Value("${jwt.secret}")
    private String secret;
    
    @Value("${jwt.expiration}")
    private int expiration;
    
    public String generateToken( Authentication auth ){
        // Obtiene Usuario
        UserRolesPrincipal authUserRoles = (UserRolesPrincipal) auth.getPrincipal();
        // Crea el Token
        return Jwts.builder()
            .setSubject( authUserRoles.getUsername() )
            .setIssuer( authUserRoles.getEmail() )
            .setIssuedAt( new Date() )
            .setExpiration( new Date( new Date().getTime() + expiration * 1000 ) )
            .signWith( SignatureAlgorithm.HS512, secret )
            .compact();
    }
    
    public String getUserNameFromToken( String token ){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token)
            .getBody().getSubject();
    }
    
    public String getUserEmailFromToken( String token ){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token)
            .getBody().getIssuer();
    }
    
    public boolean validateToken( String token ){
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            //logger.info("* * * Token OK. pasa 2 .");
            return true;
        }
        catch( MalformedJwtException e ){
            logger.error("Token mal construido.");
        }
        catch( UnsupportedJwtException e ){
            logger.error("Token no soportado.");
        }
        catch( ExpiredJwtException e ){
            logger.error("Token expirado/caducado.");
        }
        catch( IllegalArgumentException e ){
            logger.error("Token vacío.");
        }
        catch( SignatureException e ){
            logger.error("Error en la firma del Token.");
        }
        return false;
    }
    
}
