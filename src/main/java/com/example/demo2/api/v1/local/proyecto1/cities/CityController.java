
package com.example.demo2.api.v1.local.proyecto1.cities;

import com.example.demo2.api.v1.local.Utils.ResponseLocal;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
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
    
    @GetMapping
    public ResponseLocal obtenerTodos(){
        ResponseLocal response = new ResponseLocal();
        try {
            ArrayList<City> data = cityService.obtenerTodos();
            response.data = data;
            return response;
        }
        catch ( Exception e ){
            response.code = 500;
            response.message = e.getMessage();
            return response;
        }
    }
    
    @PostMapping()
    public ResponseLocal guardar(@RequestBody City city ){
        ResponseLocal response = new ResponseLocal();
        ArrayList data = new ArrayList();
        try {
            City newCity = this.cityService.guardar(city);

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

    @GetMapping( path = "/{id}")
    public Optional<City> obtenerPorId(@PathVariable("id") Integer id) {
        return this.cityService.obtenerPorId(id);
    }

    @GetMapping("/query")
    public ArrayList<City> obtenerPorNombre(@RequestParam("name") String name){
        return this.cityService.obtenerPorNombre(name);
    }

    @DeleteMapping( path = "/{id}")
    public String eliminarPorId(@PathVariable("id") Integer id){
        boolean ok = this.cityService.eliminar(id);
        if( ok ){
            return "Se eliminó el registro con ID: " + id;
        }else{
            return "No pudo eliminar el registro con ID: " + id;
        }
    }
    
}
