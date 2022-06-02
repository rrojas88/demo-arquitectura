
package com.example.demo2.api.v1.local.proyecto1.departments;

import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
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
@RequestMapping("/v1/local/proyecto1/departments")
public class DepartmentController {
    
    @Autowired
    DepartmentService departmentService;
    
    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USUARIO') OR hasRole('ROLE_LECTURA')")
    @GetMapping
    public ArrayList<Department> obtenerTodos(){
        return departmentService.obtenerTodos();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USUARIO') OR hasRole('ROLE_LECTURA')")
    @GetMapping( path = "/{id}")
    public Optional<Department> obtenerUsuarioPorId(@PathVariable("id") Integer id) {
        return this.departmentService.obtenerPorId(id);
    }

    @PreAuthorize("hasRole('ROLE_USUARIO')")
    @GetMapping("/query")
    public ArrayList<Department> obtenerDepartamentoPorNombre(@RequestParam("name") String name){
        return this.departmentService.obtenerPorNombre(name);
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USUARIO')")
    @PostMapping()
    public Department guardarUsuario(@RequestBody Department depto ){
        return this.departmentService.guardar(depto);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping( path = "/{id}")
    public String eliminarPorId(@PathVariable("id") Integer id){
        boolean ok = this.departmentService.eliminar(id);
        if( ok ){
            return "Se eliminó el registro con ID: " + id;
        }else{
            return "No pudo eliminar el registro con ID: " + id;
        }
    }
}
