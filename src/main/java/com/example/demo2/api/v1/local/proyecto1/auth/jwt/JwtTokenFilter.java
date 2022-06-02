
package com.example.demo2.api.v1.local.proyecto1.auth.jwt;


import com.example.demo2.api.v1.local.proyecto1.auth.UserRolesPrincipalService;
import com.example.demo2.api.v1.local.proyecto1.auth.jwt.JwtProvider;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

// Se ejecuta en cada Peticion !
// Valida el Token y permite acceso o Deniega (Autoriza)

public class JwtTokenFilter extends OncePerRequestFilter {
    
    private final static Logger logger = LoggerFactory.getLogger(JwtTokenFilter.class );
    
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    UserRolesPrincipalService userRolesPrincipalService;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws ServletException, IOException {
        
        // En cada Peticion comprueba si el Token es valido o no
        try {
            String token = getToken(req);
            if( token != null && jwtProvider.validateToken(token) ){
                //String userName = jwtProvider.getUserNameFromToken(token);
                String userEmail = jwtProvider.getUserEmailFromToken(token);
                // Servicio que obtiene la Info del User (por el correo) desde la BD
                // y lo carga como "Principal"
                UserDetails userDetails = userRolesPrincipalService.loadUserByUsername(userEmail);
                logger.info("* * * Lista Roles: " + userDetails.getAuthorities() );
                // Obtiene Autenticacion
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken( 
                    userDetails, null, userDetails.getAuthorities()
                );
                // Obtiene la Autorizacion y se le pasa al Contexto
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        catch (Exception e) {
            logger.error("= = = Falla en el doFilterInternal. " + e.getMessage());
        }
        
        // ???
        filterChain.doFilter(req, res);
    }
    
    
    private String getToken( HttpServletRequest req ){
        String header = req.getHeader("Authorization");
        if( header != null && header.startsWith("Bearer") )
            return header.replace( "Bearer ", "" );
        return null;
    }
    
    
}
