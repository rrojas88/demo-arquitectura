
package com.example.demo2.api.v1.external.api_arus.coins;

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
}
