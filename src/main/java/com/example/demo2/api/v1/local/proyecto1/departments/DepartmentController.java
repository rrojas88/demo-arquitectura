
package com.example.demo2.api.v1.local.proyecto1.departments;

import com.example.demo2.api.v1.local.Utils.ResponseLocal;
import com.example.demo2.api.v1.local.Utils.logs.LogService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/v1/local/proyecto1/departments")
public class DepartmentController {
    
    @Autowired
    DepartmentService departmentService;
    @Autowired
    LogService logService;
    
    
    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USUARIO') OR hasRole('ROLE_LECTURA')")
    @GetMapping
    public ResponseEntity<?> getAll( HttpServletRequest req ){
    //public ResponseLocal getAll( HttpServletRequest req ){

        ResponseLocal response = new ResponseLocal( logService );
        //Map<String, Object> response = new HashMap<>();
        try {
            //ArrayList<Department> rows = departmentService.getAll();
            ArrayList<?> rows = departmentService.getAll();
            /*
            response.put( "data", rows );
            response.put( "code", 200 );
            return new ResponseEntity<Map<String, Object>>( response, HttpStatus.OK ); */
            response.setSuccess( rows, 
                "Departamentos listados", 
                DepartmentController.class.getName(), 
                null, 
                req
            );
            //return response;
            return new ResponseEntity<Object>( response, HttpStatus.OK );
        } catch (Exception e) {
            /*
            response.put( "data", null );
            response.put( "code", 400 );
            response.put( "message", "Error obteniendo los Departamentos. " + e.getMessage() );
            return new ResponseEntity<Map<String, Object>>( response, HttpStatus.BAD_REQUEST );
            */
            response.setError( HttpStatus.BAD_REQUEST.value(), 
                "No se pudo listar los Departamentos", 
                e.getMessage(), 
                new ArrayList<ObjectError>(),
                DepartmentController.class.getName(), 
                null, 
                req
            );
            //return response;
            return new ResponseEntity<Object>( response, HttpStatus.BAD_REQUEST );
        }
    }

    
    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USUARIO') OR hasRole('ROLE_LECTURA')")
    @GetMapping( path = "/{id}")
    //public Optional<Department> getById(@PathVariable("id") Integer id) {
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        Optional<?> depto = this.departmentService.getById(id);
        Map<String, Object> response = new HashMap<>();
        response.put( "data", depto );
        response.put( "code", 200 );
        return new ResponseEntity<Map<?,?>>( response, HttpStatus.OK );
    }
    

    @PreAuthorize("hasRole('ROLE_USUARIO')")
    @GetMapping("/query")
    public ArrayList<Department> getByName(@RequestParam("name") String name){
        return this.departmentService.getByName(name);
    }
    
    
    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USUARIO')")
    @PostMapping()
    //public ResponseLocal save( @Valid @RequestBody DepartmentDto deptoDto,
    public ResponseEntity<?> save( @Valid @RequestBody DepartmentDto deptoDto,
        BindingResult bindingResult, 
        HttpServletRequest req
    )
    {
        ResponseLocal response = new ResponseLocal( logService );
        //ArrayList data = new ArrayList();
        
        if( bindingResult.hasErrors() ){
            response.setError( HttpStatus.BAD_REQUEST.value(), "", "", 
                bindingResult.getAllErrors(),
                DepartmentController.class.getName(), 
                deptoDto.toString(), 
                req
            );
            // Hilo ==========================
            /*
            LogThread logThread = new LogThread( 
                500, messages,
                messages, "Departments->save()",
                "/v1/local/proyecto1/departments", "11",
                "admin@html.com", "localhost", 
                deptoDto.toString()
            );
            logThread.start();
            /* */
            // Fin Hilo ======================
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
            Department row = this.departmentService.save(depto);
            //data.add( row );
            //response.data = data;
            response.setSuccess( row, 
                "Departamento guardado correctamente", 
                DepartmentController.class.getName(), 
                deptoDto.toString(), 
                req
            );
            return new ResponseEntity<Object>( response, HttpStatus.OK );
        } catch (Exception e) {
            response.setError( HttpStatus.BAD_REQUEST.value(), 
                "No se pudo guardar el Departamento", 
                e.getMessage(), 
                new ArrayList<ObjectError>(),
                DepartmentController.class.getName(), 
                deptoDto.toString(), 
                req
            );
            return new ResponseEntity<Object>( response, HttpStatus.BAD_REQUEST );
        }
    }

    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping( path = "/{id}")
    public String delete(@PathVariable("id") Integer id){
        boolean ok = this.departmentService.delete(id);
        if( ok ){
            return "Se eliminó el registro con ID: " + id;
        }else{
            return "No pudo eliminar el registro con ID: " + id;
        }
    }
}
