
package com.example.demo2.api.v1.external.api_arus.coins;

import java.net.URI;
import java.net.URISyntaxException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/v1/local/auth")
public class CoinController {
    
    @GetMapping("/txt")
    public String getCoin2(){
        return "Lista de Monedas";
    }
    
    @GetMapping("/coins")
    public String getCoin(){
        String uri = "https://api.coingecko.com/api/v3/simple/price?ids=dogecoin&vs_currencies=usd";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        return result;
    }
    
    @PostMapping("/test-login")
    public ResponseEntity<?> postLogin( HttpServletRequest req ) // throws URISyntaxException 
    {
        UserCredentials userCredentials = new UserCredentials("pepito", "123456");
        
        URI uri = null;
        try {
            final String baseUrl = "http://localhost:3000/api/v1/test-login";
            uri = new URI(baseUrl);
        } 
        catch (URISyntaxException e) {
            System.out.println( "++++++ URISyntaxException" );
            System.out.println( e.getMessage() );
        }
        catch (Exception e) {
            System.out.println( "++++++ Exception" );
            System.out.println( e.getMessage() );
        }
        System.out.println( "----- URi  " ); System.out.println( uri );
        
        String auth_token = req.getHeader("auth-token");
        if( auth_token == null ) auth_token = "Token x defalt";
        
        String public_key = req.getHeader("public-key");
        if( public_key == null ) public_key = "Sin clave publica";
        
        HttpHeaders headers = new HttpHeaders();
        headers.set("auth-token", auth_token );    
        headers.set("public-key", public_key );
        
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        HttpEntity<UserCredentials> requestBody = new HttpEntity<>(userCredentials, headers);
        
        ResponseEntity<Object> result = null;
        try {
            RestTemplate restTemplate = new RestTemplate();
            
            result = restTemplate.postForEntity( uri, requestBody, Object.class );
        }
        catch (Exception e) {
            System.out.println( "++++++ Exception POST:" );
            System.out.println( e.getMessage() );
        }
        return result;
    }
    
}
