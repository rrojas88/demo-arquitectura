
package com.example.demo2.api.v1.external.api_arus.pse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/external/arus/pse")
public class PseController {
    
    @Autowired
    PseService pseService;
    
    @PostMapping( path = "/generate" )
    public String generate(){
        return pseService.generar();
    }
    
}
