
package com.example.demo2.api.v1.local.proyecto1.cities;

import com.example.demo2.api.v1.local.Utils.ResponseLocal;
import com.example.demo2.api.v1.local.Utils.UtilsLocal;
import com.example.demo2.api.v1.local.Utils.UtilsService;
import com.example.demo2.api.v1.local.proyecto1.cities.adapters.CityDto;

import com.example.demo2.api.v1.local.proyecto1.logs.LogService;
import com.example.demo2.api.v1.local.proyecto1.permissions.PermissionService;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/local/proyecto1/cities")
public class CityController {
    
    @Autowired
    CityService cityService;
    
    @Autowired
    LogService logService;
    
    @Autowired
    PermissionService permissionService;
    
    private String myClassName = CityController.class.getName();
    
    private final String module = "Cities";
    

    @GetMapping
    public ResponseEntity<?> getAll( HttpServletRequest req ){
        ResponseLocal response = new ResponseLocal( logService );
        Object permission = permissionService.validate( req, this.module, "getAll", response );
        if( permission != null ) return new ResponseEntity<>( permission, HttpStatus.FORBIDDEN );
        
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
                UtilsLocal.emptyErrorList(),
                this.myClassName, 
                null, 
                req
            );
            return new ResponseEntity<>( response, HttpStatus.BAD_REQUEST );
        }
    }
    
    
    @GetMapping( path = "/id/{id}")
    public ResponseEntity<?> getById(
        @PathVariable("id") Integer id,
        HttpServletRequest req
    )
    {System.out.println( "----- ----- Entra en ByID:  " );
        ResponseLocal response = new ResponseLocal( logService );
        //Object permission = permissionService.validate( req, this.module, "getOne", response );
        //if( permission != null ) return new ResponseEntity<>( permission, HttpStatus.FORBIDDEN );
        
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
                UtilsLocal.emptyErrorList(),
                this.myClassName, 
                null, 
                req
            );
            return new ResponseEntity<>( response, HttpStatus.BAD_REQUEST );
        }
    }

    
    @GetMapping("/query")
    public ResponseEntity<?> getByName(
        @RequestParam("name") String name,
        HttpServletRequest req
    )
    {System.out.println( "----- ----- Entra en QUERY:  " );
        ResponseLocal response = new ResponseLocal( logService );
        Object permission = permissionService.validate( req, this.module, "getOne", response );
        if( permission != null ) return new ResponseEntity<>( permission, HttpStatus.FORBIDDEN );
        
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
                UtilsLocal.emptyErrorList(),
                this.myClassName, 
                null, 
                req
            );
            return new ResponseEntity<>( response, HttpStatus.BAD_REQUEST );
        }
    }
    
    
    @GetMapping( path = "/sql/{id}")
    public ResponseEntity<?> getBySql(
        @PathVariable("id") Integer id,
        HttpServletRequest req
    )
    {System.out.println( "----- ----- Entra en SQL:  " );
        ResponseLocal response = new ResponseLocal( logService );
        //Object permission = permissionService.validate( req, this.module, "getOne", response );
        //if( permission != null ) return new ResponseEntity<>( permission, HttpStatus.FORBIDDEN );
        
        try {
            Object row = this.cityService.getBySql(id);
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
                UtilsLocal.emptyErrorList(),
                this.myClassName, 
                null, 
                req
            );
            return new ResponseEntity<>( response, HttpStatus.BAD_REQUEST );
        }
    }
    
    
    @PostMapping()
    public ResponseEntity<?> save(
        @Valid @RequestBody CityDto cityDto,
        BindingResult bindingResult, 
        HttpServletRequest req
    ){
        ResponseLocal response = new ResponseLocal( logService );
        Object permission = permissionService.validate( req, this.module, "save", response );
        if( permission != null ) return new ResponseEntity<>( permission, HttpStatus.FORBIDDEN );
        
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
                UtilsLocal.emptyErrorList(),
                this.myClassName, 
                cityDto.toString(), 
                req
            );
            return new ResponseEntity<>( response, HttpStatus.BAD_REQUEST );
        }
    }

    
    @DeleteMapping( path = "/{id}")
    public ResponseEntity<?> delete(
        @PathVariable("id") Integer id,
        HttpServletRequest req
    )
    {
        ResponseLocal response = new ResponseLocal( logService );
        Object permission = permissionService.validate( req, this.module, "delete", response );
        if( permission != null ) return new ResponseEntity<>( permission, HttpStatus.FORBIDDEN );
        try {
            String message = "";
            Object resDel = this.cityService.delete(id);
            if( ! UtilsService.isErrorService(resDel) ){ 
                message = (String) resDel;
                resDel = null;
            }
            
            HttpStatus httpStatus = response.validateService( resDel, 
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
                UtilsLocal.emptyErrorList(),
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
                UtilsLocal.emptyErrorList(),
                this.myClassName, 
                null, 
                req
            );
            return new ResponseEntity<>( response, HttpStatus.BAD_REQUEST );
        } 
    }
    
}
