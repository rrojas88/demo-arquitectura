
package com.example.demo2.api.v1.local.proyecto1.permissions;

import com.example.demo2.api.v1.local.Utils.ResponseLocal;
import com.example.demo2.api.v1.local.Utils.UtilsLocal;
import com.example.demo2.api.v1.local.Utils.UtilsService;
import com.example.demo2.api.v1.local.proyecto1.logs.LogService;
import com.example.demo2.api.v1.local.proyecto1.modules.adapters.ModuleDto;
import com.example.demo2.api.v1.local.proyecto1.permissions.adapters.PermissionDto;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/local/proyecto1/permissions")
public class PermissionController {
    
    @Autowired
    PermissionService permissionService;
    
    @Autowired
    LogService logService;
    
    private String myClassName = PermissionController.class.getName();
    
    
    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USUARIO') OR hasRole('ROLE_LECTURA')")
    @GetMapping
    public ResponseEntity<?> getAll( HttpServletRequest req )
    {
        ResponseLocal response = new ResponseLocal( logService );
        try {
            Object rows = permissionService.getAll();
            HttpStatus httpStatus = response.validateService( rows, 
                "Permisos listados",
                this.myClassName, 
                null, 
                req
            );
            return new ResponseEntity<Object>( response, httpStatus );
        }
        catch (Exception e) {
            response.setError( HttpStatus.BAD_REQUEST.value(), 
                "No se pudo listar los permisos", 
                e.getMessage(), 
                UtilsLocal.emptyErrorList(),
                this.myClassName, 
                null, 
                req
            );
            return new ResponseEntity<>( response, HttpStatus.BAD_REQUEST );
        }
    }

    
    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USUARIO') OR hasRole('ROLE_LECTURA')")
    @GetMapping( path = "/{id}")
    public ResponseEntity<?> getById(
        @PathVariable("id") Integer id,
        HttpServletRequest req
    )
    {
        ResponseLocal response = new ResponseLocal( logService );
        try {
            Object row = this.permissionService.getById(id);
            HttpStatus httpStatus = response.validateService( row, 
                "Permiso obtenido",
                this.myClassName, 
                null, 
                req
            );
            return new ResponseEntity<>( response, httpStatus );
        }
        catch (Exception e) {
            response.setError( HttpStatus.BAD_REQUEST.value(), 
                "No se pudo obtener el permiso", 
                e.getMessage(), 
                UtilsLocal.emptyErrorList(),
                this.myClassName, 
                null, 
                req
            );
            return new ResponseEntity<>( response, HttpStatus.BAD_REQUEST );
        }
    }
    
/*
    @PreAuthorize("hasRole('ROLE_USUARIO')")
    @GetMapping("/query")
    public ResponseEntity<?> getByAction(
        @RequestParam("name") Integer action_id,
        HttpServletRequest req
    )
    {
        ResponseLocal response = new ResponseLocal( logService );
        try {
            Object row = this.permissionService.getByAction_id(action_id);
            HttpStatus httpStatus = response.validateService( row, 
                "Permiso obtenido",
                this.myClassName, 
                null, 
                req
            );
            return new ResponseEntity<Object>( response, HttpStatus.OK );
        }
        catch (Exception e) {
            response.setError( HttpStatus.BAD_REQUEST.value(), 
                "No se pudo obtener el permiso", 
                e.getMessage(), 
                UtilsLocal.emptyErrorList(),
                this.myClassName, 
                null, 
                req
            );
            return new ResponseEntity<>( response, HttpStatus.BAD_REQUEST );
        }
    } */
    
    
    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USUARIO')")
    @PostMapping()
    public ResponseEntity<?> save( 
        @Valid @RequestBody PermissionDto permissionDto,
        BindingResult bindingResult, 
        HttpServletRequest req
    )
    {
        ResponseLocal response = new ResponseLocal( logService );
        
        if( bindingResult.hasErrors() ){
            response.setError( HttpStatus.BAD_REQUEST.value(), "", "", 
                bindingResult.getAllErrors(),
                this.myClassName, 
                permissionDto.toString(), 
                req
            );
            return new ResponseEntity<Object>( response, HttpStatus.BAD_REQUEST );
        }
        try {
            Object row = this.permissionService.save(permissionDto);
            HttpStatus httpStatus = response.validateService( row, 
                "Permiso guardado correctamente",
                this.myClassName, 
                permissionDto.toString(), 
                req
            );
            return new ResponseEntity<>( response, httpStatus );
        }
        catch (Exception e) {
            response.setError( HttpStatus.BAD_REQUEST.value(), 
                "No se pudo guardar el permiso", 
                e.getMessage(), 
                UtilsLocal.emptyErrorList(),
                this.myClassName, 
                permissionDto.toString(), 
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
            String message = "";
            Object resDel = this.permissionService.delete(id);
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
                "No se pudo eliminar el permiso.", 
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
                "No se pudo eliminar el permiso", 
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
