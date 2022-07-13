
package com.example.demo2.api.v1.local.proyecto1.cities;

import com.example.demo2.api.v1.local.Utils.ResponseLocal;
import com.example.demo2.api.v1.local.Utils.logs.LogService;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/v1/local/proyecto1/cities")
public class CityController {
    
    @Autowired
    CityService cityService;
    @Autowired
    LogService logService;
    
    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USUARIO') OR hasRole('ROLE_LECTURA')")
    @GetMapping
    public ResponseLocal getAll(){
        ResponseLocal response = new ResponseLocal( logService );
        try {
            ArrayList<City> data = cityService.getAll();
            response.data = data;
            return response;
        }
        catch ( Exception e ){
            response.code = 500;
            response.message = e.getMessage();
            return response;
        }
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping( path = "/{id}")
    public Optional<City> getById(@PathVariable("id") Integer id) {
        return this.cityService.getById(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/query")
    public ArrayList<City> getByName(@RequestParam("name") String name){
        return this.cityService.getByName(name);
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping()
    public ResponseLocal save(@RequestBody City city ){
        ResponseLocal response = new ResponseLocal( logService );
        ArrayList data = new ArrayList();
        try {
            City newCity = this.cityService.save(city);

            data.add( newCity );
            response.data = data;
            return response;
        }
        catch ( Exception e ){ //data.add( city ); response.data = data; 
            response.code = 500;
            response.message = e.getMessage();
            return response;
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping( path = "/{id}")
    public String delete(@PathVariable("id") Integer id){
        boolean ok = this.cityService.delete(id);
        if( ok ){
            return "Se eliminó el registro con ID: " + id;
        }else{
            return "No pudo eliminar el registro con ID: " + id;
        }
    }
    
}
