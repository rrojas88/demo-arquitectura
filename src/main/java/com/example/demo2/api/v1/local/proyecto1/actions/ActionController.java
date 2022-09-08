
package com.example.demo2.api.v1.local.proyecto1.actions;

import com.example.demo2.api.v1.local.Utils.ResponseLocal;
import com.example.demo2.api.v1.local.Utils.UtilsLocal;
import com.example.demo2.api.v1.local.Utils.UtilsService;
import com.example.demo2.api.v1.local.proyecto1.actions.adapters.ActionDto;
import com.example.demo2.api.v1.local.proyecto1.logs.LogService;
import com.example.demo2.api.v1.local.proyecto1.permissions.PermissionService;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/local/proyecto1/actions")
public class ActionController {
    
    @Autowired
    ActionService actionService;
    
    @Autowired
    LogService logService;
    
    @Autowired
    PermissionService permissionService;
    
    private String myClassName = ActionController.class.getName();
    
    private final String module = "Actions";
    

    @GetMapping
    public ResponseEntity<?> getAll( HttpServletRequest req ){
        ResponseLocal response = new ResponseLocal( logService );
        Object permission = permissionService.validate( req, this.module, "getAll", response );
        if( permission != null ) return new ResponseEntity<>( permission, HttpStatus.FORBIDDEN );
        
        try {
            Object rows = actionService.getAll();
            HttpStatus httpStatus = response.validateService( rows, 
                "acciones listadas",
                this.myClassName, 
                null, 
                req
            );
            return new ResponseEntity<Object>( response, httpStatus );
        }
        catch ( Exception e ){
            response.setError( HttpStatus.BAD_REQUEST.value(), 
                "No se pudo listar las acciones", 
                e.getMessage(), 
                UtilsLocal.emptyErrorList(),
                this.myClassName, 
                null, 
                req
            );
            return new ResponseEntity<>( response, HttpStatus.BAD_REQUEST );
        }
    }
    
    
    @GetMapping( path = "/{id}")
    public ResponseEntity<?> getById(
        @PathVariable("id") Integer id,
        HttpServletRequest req
    )
    {
        ResponseLocal response = new ResponseLocal( logService );
        Object permission = permissionService.validate( req, this.module, "getOne", response );
        if( permission != null ) return new ResponseEntity<>( permission, HttpStatus.FORBIDDEN );
        
        try {
            Object row = this.actionService.getById(id);
            HttpStatus httpStatus = response.validateService( row, 
                "acción obtenida",
                this.myClassName, 
                null, 
                req
            );
            return new ResponseEntity<>( response, httpStatus );
        }
        catch (Exception e) {
            response.setError( HttpStatus.BAD_REQUEST.value(), 
                "No se pudo obtener la acción", 
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
    {
        ResponseLocal response = new ResponseLocal( logService );
        Object permission = permissionService.validate( req, this.module, "getOne", response );
        if( permission != null ) return new ResponseEntity<>( permission, HttpStatus.FORBIDDEN );
        
        try {
            Object row = this.actionService.getByName(name);
            HttpStatus httpStatus = response.validateService( row, 
                "Acción obtenida",
                this.myClassName, 
                null, 
                req
            );
            return new ResponseEntity<Object>( response, HttpStatus.OK );
        }
        catch (Exception e) {
            response.setError( HttpStatus.BAD_REQUEST.value(), 
                "No se pudo obtener la acción", 
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
        @Valid @RequestBody ActionDto actionDto,
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
                actionDto.toString(), 
                req
            );
            return new ResponseEntity<>( response, HttpStatus.BAD_REQUEST );
        }
        try {
            Object row = this.actionService.save(actionDto);
            HttpStatus httpStatus = response.validateService( row, 
                "Acción guardada correctamente",
                this.myClassName, 
                actionDto.toString(), 
                req
            );
            return new ResponseEntity<Object>( response, httpStatus );
        }
        catch ( Exception e ){
            response.setError( HttpStatus.BAD_REQUEST.value(), 
                "No se pudo guardar la acción", 
                e.getMessage(), 
                UtilsLocal.emptyErrorList(),
                this.myClassName, 
                actionDto.toString(), 
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
            Object resDel = this.actionService.delete(id);
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
        catch (Exception e) {
            response.setError( HttpStatus.BAD_REQUEST.value(), 
                "No se pudo eliminar la acción", 
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
