
package com.example.demo2.api.v1.local.proyecto1.mytasks;

import com.example.demo2.api.v1.local.Utils.ResponseLocal;
import com.example.demo2.api.v1.local.Utils.UtilsLocal;
import com.example.demo2.api.v1.local.Utils.UtilsService;
import com.example.demo2.api.v1.local.proyecto1.logs.LogService;
import com.example.demo2.api.v1.local.proyecto1.mytasks.adapters.MytaskDto;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/local/proyecto1/mytasks")
public class MytaskController {
    
    @Autowired
    MytaskService mytasksService;
    @Autowired
    LogService logService;
    
    private String myClassName = MytaskController.class.getName();
    
    
    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USUARIO') OR hasRole('ROLE_LECTURA')")
    @GetMapping("/all")
    public ResponseEntity<?> getAll( HttpServletRequest req )
    {
        ResponseLocal response = new ResponseLocal( logService );
        try {
            Object rows = mytasksService.getAll();
            HttpStatus httpStatus = response.validateService( rows, 
                "Tareas listadas",
                this.myClassName, 
                null, 
                req
            );
            return new ResponseEntity<Object>( response, httpStatus );
        }
        catch (Exception e) {
            response.setError( HttpStatus.BAD_REQUEST.value(), 
                "No se pudo listar las tareas", 
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
            Object row = this.mytasksService.getById(id);
            HttpStatus httpStatus = response.validateService( row, 
                "Tarea obtenida",
                this.myClassName, 
                null, 
                req
            );
            return new ResponseEntity<>( response, httpStatus );
        }
        catch (Exception e) {
            response.setError( HttpStatus.BAD_REQUEST.value(), 
                "No se pudo obtener la tarea", 
                e.getMessage(), 
                UtilsLocal.emptyErrorList(),
                this.myClassName, 
                null, 
                req
            );
            return new ResponseEntity<>( response, HttpStatus.BAD_REQUEST );
        }
    }
    

    @PreAuthorize("hasRole('ROLE_USUARIO')")
    @GetMapping("/query-name")
    public ResponseEntity<?> getByName(
        @RequestParam("name") String name,
        HttpServletRequest req
    )
    {
        ResponseLocal response = new ResponseLocal( logService );
        try {
            Object row = this.mytasksService.getByName(name);
            HttpStatus httpStatus = response.validateService( row, 
                "Tarea obtenida",
                this.myClassName, 
                null, 
                req
            );
            return new ResponseEntity<Object>( response, HttpStatus.OK );
        }
        catch (Exception e) {
            response.setError( HttpStatus.BAD_REQUEST.value(), 
                "No se pudo obtener la tarea", 
                e.getMessage(), 
                UtilsLocal.emptyErrorList(),
                this.myClassName, 
                null, 
                req
            );
            return new ResponseEntity<>( response, HttpStatus.BAD_REQUEST );
        }
    }
    
    
    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USUARIO')")
    @PostMapping("/save")
    public ResponseEntity<?> save(
        @Valid @RequestBody MytaskDto mytasksDto,
        BindingResult bindingResult, 
        HttpServletRequest req
    )
    {
        ResponseLocal response = new ResponseLocal( logService );
        
        if( bindingResult.hasErrors() ){
            response.setError( HttpStatus.BAD_REQUEST.value(), "", "", 
                bindingResult.getAllErrors(),
                this.myClassName, 
                mytasksDto.toString(), 
                req
            );
            return new ResponseEntity<Object>( response, HttpStatus.BAD_REQUEST );
        }
        try {
            Object row = this.mytasksService.save(mytasksDto);
            HttpStatus httpStatus = response.validateService( row, 
                "Tarea guardada correctamente",
                this.myClassName, 
                mytasksDto.toString(), 
                req
            );
            return new ResponseEntity<>( response, httpStatus );
        }
        catch (Exception e) {
            response.setError( HttpStatus.BAD_REQUEST.value(), 
                "No se pudo guardar la tarea", 
                e.getMessage(), 
                UtilsLocal.emptyErrorList(),
                this.myClassName, 
                mytasksDto.toString(), 
                req
            );
            return new ResponseEntity<>( response, HttpStatus.BAD_REQUEST );
        }
    }

    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping( path = "/del/{id}")
    public ResponseEntity<?> delete(
        @PathVariable("id") Integer id,
        HttpServletRequest req
    )
    {
        ResponseLocal response = new ResponseLocal( logService );
        try {
            String message = "";
            Object resDel = this.mytasksService.delete(id);
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
                "No se pudo eliminar la tarea", 
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
