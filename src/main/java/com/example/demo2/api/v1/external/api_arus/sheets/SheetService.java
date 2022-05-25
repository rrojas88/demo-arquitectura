
package com.example.demo2.api.v1.external.api_arus.sheets;

import org.springframework.stereotype.Service;

@Service
public class SheetService {
    
    public String validar(){
        return "Planilla correcta";
    }
    
    public String corregir(){
        return "Planilla corregida";
    }
    
    public String obtenerTotalesConEstado(){
        return "La planilla está Validada y tiene un Total de $ 5.050.000 Pesos";
    }
}
