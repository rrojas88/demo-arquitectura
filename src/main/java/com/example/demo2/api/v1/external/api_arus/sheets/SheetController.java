
package com.example.demo2.api.v1.external.api_arus.sheets;

import com.example.demo2.api.v1.external.api_arus.sheets.SheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/external/arus/sheets")
public class SheetController {
    
    @Autowired
    SheetService sheetService;
    
    @PostMapping( path = "/validate" )
    public String validate(){
        return sheetService.validar();
    }
    
    @PostMapping( path = "/fix" )
    public String fix(){
        return sheetService.corregir();
    }
    
    @GetMapping( path = "/get-totals-state/{id}" )
    public String getTotalesAndState(@PathVariable("id") Integer id){
        return sheetService.obtenerTotalesConEstado();
    }
    
}
