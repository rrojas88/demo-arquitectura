
package com.example.demo2.api.v1.local.proyecto1.departments;

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
@RequestMapping("/v1/local/proyecto1/departments")
public class DepartmentController {
    
    @Autowired
    DepartmentService departmentService;
    @Autowired
    LogService logService;
    
    private String myClassName = DepartmentController.class.getName();
    
    
    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USUARIO') OR hasRole('ROLE_LECTURA')")
    @GetMapping
    public ResponseEntity<?> getAll( HttpServletRequest req )
    {
        ResponseLocal response = new ResponseLocal( logService );
        try {
            Object rows = departmentService.getAll();
            HttpStatus httpStatus = response.validateService( rows, 
                "Departamentos listados",
                this.myClassName, 
                null, 
                req
            );
            return new ResponseEntity<Object>( response, httpStatus );
        }
        catch (Exception e) {
            response.setError( HttpStatus.BAD_REQUEST.value(), 
                "No se pudo listar los Departamentos", 
                e.getMessage(), 
                new ArrayList<ObjectError>(),
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
            Object row = this.departmentService.getById(id);
            HttpStatus httpStatus = response.validateService( row, 
                "Departamento obtenido",
                this.myClassName, 
                null, 
                req
            );
            return new ResponseEntity<>( response, httpStatus );
        }
        catch (Exception e) {
            response.setError( HttpStatus.BAD_REQUEST.value(), 
                "No se pudo obtener el Departamento", 
                e.getMessage(), 
                new ArrayList<ObjectError>(),
                this.myClassName, 
                null, 
                req
            );
            return new ResponseEntity<>( response, HttpStatus.BAD_REQUEST );
        }
    }
    

    @PreAuthorize("hasRole('ROLE_USUARIO')")
    @GetMapping("/query")
    public ResponseEntity<?> getByName(
        @RequestParam("name") String name,
        HttpServletRequest req
    )
    {
        ResponseLocal response = new ResponseLocal( logService );
        try {
            Object row = this.departmentService.getByName(name);
            HttpStatus httpStatus = response.validateService( row, 
                "Departamento obtenido",
                this.myClassName, 
                null, 
                req
            );
            return new ResponseEntity<Object>( response, HttpStatus.OK );
        }
        catch (Exception e) {
            response.setError( HttpStatus.BAD_REQUEST.value(), 
                "No se pudo obtener el Departamento", 
                e.getMessage(), 
                new ArrayList<ObjectError>(),
                this.myClassName, 
                null, 
                req
            );
            return new ResponseEntity<>( response, HttpStatus.BAD_REQUEST );
        }
    }
    
    
    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USUARIO')")
    @PostMapping()
    public ResponseEntity<?> save( 
        @Valid @RequestBody DepartmentDto deptoDto,
        BindingResult bindingResult, 
        HttpServletRequest req
    )
    {
        ResponseLocal response = new ResponseLocal( logService );
        
        if( bindingResult.hasErrors() ){
            response.setError( HttpStatus.BAD_REQUEST.value(), "", "", 
                bindingResult.getAllErrors(),
                this.myClassName, 
                deptoDto.toString(), 
                req
            );
            return new ResponseEntity<Object>( response, HttpStatus.BAD_REQUEST );
        }
        try {
            Department depto = new Department();
            if( deptoDto.getId() != null ){
                depto.setId( deptoDto.getId() );
                depto.setCode( deptoDto.getCode() );
                depto.setName( deptoDto.getName() );
                depto.setActive( deptoDto.getActive() );
            }
            else{
                depto.setCode( deptoDto.getCode() );
                depto.setName( deptoDto.getName() );
            }
            Object row = this.departmentService.save(depto);
            HttpStatus httpStatus = response.validateService( row, 
                "Departamento guardado correctamente",
                this.myClassName, 
                deptoDto.toString(), 
                req
            );
            return new ResponseEntity<>( response, httpStatus );
        }
        catch (Exception e) {
            response.setError( HttpStatus.BAD_REQUEST.value(), 
                "No se pudo guardar el Departamento", 
                e.getMessage(), 
                new ArrayList<ObjectError>(),
                this.myClassName, 
                deptoDto.toString(), 
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
            Object resDel = this.departmentService.delete(id);
            HttpStatus httpStatus = response.validateService( resDel, 
                "Se eliminó el registro con ID: " + id,
                this.myClassName, 
                null, 
                req
            );
            return new ResponseEntity<>( response, httpStatus );
        } /*
        catch ( SQLException e) {
            response.setError( HttpStatus.BAD_REQUEST.value(), 
                "No se pudo eliminar el Departamento", 
                e.getMessage(), 
                new ArrayList<ObjectError>(),
                this.myClassName, 
                null, 
                req
            );
            return new ResponseEntity<>( response, HttpStatus.BAD_REQUEST );
        } */
        catch ( DataAccessException e) {
            response.setError( HttpStatus.BAD_REQUEST.value(), 
                "No se pudo eliminar el Departamento.", 
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
                "No se pudo eliminar el Departamento", 
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
