
package com.example.demo2.api.v1.external.api_arus.pse;

import org.springframework.stereotype.Service;

@Service
public class PseService {
    
    public String generar(){
        return "https//www.pse.com/pago?ref=123456";
    }
}
