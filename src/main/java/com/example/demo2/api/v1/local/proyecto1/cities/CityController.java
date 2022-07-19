
package com.example.demo2.api.v1.local.proyecto1.cities;

import com.example.demo2.api.v1.local.Utils.ResponseLocal;
import com.example.demo2.api.v1.local.Utils.logs.LogService;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/local/proyecto1/cities")
public class CityController {
    
    @Autowired
    CityService cityService;
    @Autowired
    LogService logService;
    
    private String myClassName = CityController.class.getName();
    
    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USUARIO') OR hasRole('ROLE_LECTURA')")
    @GetMapping
    public ResponseEntity<?> getAll( HttpServletRequest req ){
        ResponseLocal response = new ResponseLocal( logService );
        try {
            Object rows = cityService.getAll();
            HttpStatus httpStatus = response.validateService( rows, 
                "Ciudades listadas",
                this.myClassName, 
                null, 
                req
            );
            return new ResponseEntity<Object>( response, httpStatus );
        }
        catch ( Exception e ){
            response.setError( HttpStatus.BAD_REQUEST.value(), 
                "No se pudo listar las Ciudades", 
                e.getMessage(), 
                new ArrayList<ObjectError>(),
                this.myClassName, 
                null, 
                req
            );
            return new ResponseEntity<>( response, HttpStatus.BAD_REQUEST );
        }
    }
    
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping( path = "/{id}")
    public ResponseEntity<?> getById(
        @PathVariable("id") Integer id,
        HttpServletRequest req
    )
    {
        ResponseLocal response = new ResponseLocal( logService );
        try {
            Object row = this.cityService.getById(id);
            HttpStatus httpStatus = response.validateService( row, 
                "Ciudad obtenida",
                this.myClassName, 
                null, 
                req
            );
            return new ResponseEntity<>( response, httpStatus );
        }
        catch (Exception e) {
            response.setError( HttpStatus.BAD_REQUEST.value(), 
                "No se pudo obtener la Ciudad", 
                e.getMessage(), 
                new ArrayList<ObjectError>(),
                this.myClassName, 
                null, 
                req
            );
            return new ResponseEntity<>( response, HttpStatus.BAD_REQUEST );
        }
    }

    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/query")
    public ResponseEntity<?> getByName(
        @RequestParam("name") String name,
        HttpServletRequest req
    )
    {
        ResponseLocal response = new ResponseLocal( logService );
        try {
            Object row = this.cityService.getByName(name);
            HttpStatus httpStatus = response.validateService( row, 
                "Ciudad obtenida",
                this.myClassName, 
                null, 
                req
            );
            return new ResponseEntity<Object>( response, HttpStatus.OK );
        }
        catch (Exception e) {
            response.setError( HttpStatus.BAD_REQUEST.value(), 
                "No se pudo obtener la Ciudad", 
                e.getMessage(), 
                new ArrayList<ObjectError>(),
                this.myClassName, 
                null, 
                req
            );
            return new ResponseEntity<>( response, HttpStatus.BAD_REQUEST );
        }
    }
    
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping()
    public ResponseEntity<?> save(
        @Valid @RequestBody CityDto cityDto,
        BindingResult bindingResult, 
        HttpServletRequest req
    ){
        ResponseLocal response = new ResponseLocal( logService );
        if( bindingResult.hasErrors() ){
            response.setError( HttpStatus.BAD_REQUEST.value(), "", "", 
                bindingResult.getAllErrors(),
                this.myClassName, 
                cityDto.toString(), 
                req
            );
            return new ResponseEntity<>( response, HttpStatus.BAD_REQUEST );
        }
        try {
            Object row = this.cityService.save(cityDto);
            HttpStatus httpStatus = response.validateService( row, 
                "Ciudad guardada correctamente",
                this.myClassName, 
                cityDto.toString(), 
                req
            );
            return new ResponseEntity<Object>( response, httpStatus );
        }
        catch ( Exception e ){
            response.setError( HttpStatus.BAD_REQUEST.value(), 
                "No se pudo guardar la Ciudad", 
                e.getMessage(), 
                new ArrayList<ObjectError>(),
                this.myClassName, 
                cityDto.toString(), 
                req
            );
            return new ResponseEntity<>( response, HttpStatus.BAD_REQUEST );
        }
    }

    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping( path = "/{id}")
    public ResponseEntity<?> delete(
        @PathVariable("id") Integer id,
        HttpServletRequest req
    )
    {
        ResponseLocal response = new ResponseLocal( logService );
        try {
            Object ok = this.cityService.delete(id);
            String message = "Se eliminó el registro con ID: " + id;
            if( ! (boolean)ok )
                message = "No se eliminó el registro con ID: " + id;
            
            HttpStatus httpStatus = response.validateService( null, 
                message,
                this.myClassName, 
                null, 
                req
            );
            return new ResponseEntity<>( response, httpStatus );
        }
        catch ( DataAccessException e) {
            response.setError( HttpStatus.BAD_REQUEST.value(), 
                "No se pudo eliminar la Ciudad.", 
                e.getMessage(), 
                new ArrayList<ObjectError>(),
                this.myClassName, 
                null, 
                req
            );
            return new ResponseEntity<>( response, HttpStatus.BAD_REQUEST );
        }
        catch (Exception e) {
            response.setError( HttpStatus.BAD_REQUEST.value(), 
                "No se pudo eliminar la Ciudad", 
                e.getMessage(), 
                new ArrayList<ObjectError>(),
                this.myClassName, 
                null, 
                req
            );
            return new ResponseEntity<>( response, HttpStatus.BAD_REQUEST );
        } 
    }
    
}
