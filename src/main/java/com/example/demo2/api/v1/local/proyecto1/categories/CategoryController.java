
package com.example.demo2.api.v1.local.proyecto1.categories;

import com.example.demo2.api.v1.local.Utils.ResponseLocal;
import com.example.demo2.api.v1.local.Utils.UtilsFile;
import com.example.demo2.api.v1.local.Utils.UtilsLocal;
import com.example.demo2.api.v1.local.Utils.UtilsService;
import com.example.demo2.api.v1.local.proyecto1.categories.adapters.CategoryDto;
import com.example.demo2.api.v1.local.proyecto1.logs.LogService;
import com.example.demo2.api.v1.local.proyecto1.permissions.PermissionService;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/local/proyecto1/categories")
public class CategoryController {
    
    @Autowired
    CategoryService categoryService;
    
    @Autowired
    LogService logService;
    
    @Autowired
    PermissionService permissionService;
    
    private String myClassName = CategoryController.class.getName();
    
    private final String module = "Categories";
    
    // Primer num es cant de Mb permitidas
    private int limitSize = 2 * 1024 * 1024;
    // Extensiones permitidas
    private String[] extensions = {"jpg","jpeg","png"};
    
    
    @GetMapping
    public ResponseEntity<?> getAll( HttpServletRequest req ){
        ResponseLocal response = new ResponseLocal( logService );
        Object permission = permissionService.validate( req, this.module, "getAll", response );
        if( permission != null ) return new ResponseEntity<>( permission, HttpStatus.FORBIDDEN );
        
        try {
            Object rows = categoryService.getAll();
            HttpStatus httpStatus = response.validateService( rows, 
                "Categorías listadas",
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
            Object row = this.categoryService.getById(id);
            HttpStatus httpStatus = response.validateService( row, 
                "Categoría obtenida",
                this.myClassName, 
                null, 
                req
            );
            return new ResponseEntity<>( response, httpStatus );
        }
        catch (Exception e) {
            response.setError( HttpStatus.BAD_REQUEST.value(), 
                "No se pudo obtener la categoría", 
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
            Object row = this.categoryService.getByName(name);
            HttpStatus httpStatus = response.validateService( row, 
                "Categoría obtenida",
                this.myClassName, 
                null, 
                req
            );
            return new ResponseEntity<Object>( response, HttpStatus.OK );
        }
        catch (Exception e) {
            response.setError( HttpStatus.BAD_REQUEST.value(), 
                "No se pudo obtener la categoría", 
                e.getMessage(), 
                UtilsLocal.emptyErrorList(),
                this.myClassName, 
                null, 
                req
            );
            return new ResponseEntity<>( response, HttpStatus.BAD_REQUEST );
        }
    }
    
    
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(
        @Valid @ModelAttribute CategoryDto categoryDto,
        BindingResult bindingResult, 
        HttpServletRequest req
    ){
        System.out.println("\n... ... Pasa => 1 ");
        String message = "";
        ResponseLocal response = new ResponseLocal( logService );
        Object permission = permissionService.validate( req, this.module, "save", response );
        if( permission != null ) return new ResponseEntity<>( permission, HttpStatus.FORBIDDEN );
        
        if( categoryDto.getFile() == null || categoryDto.getFile().isEmpty() )
            message = "La imagen es Obligatoria";
        else
            categoryDto.setImage( categoryDto.getFile().getOriginalFilename() );
        
        if( message.equals("") && categoryDto.getFile().getSize() > this.limitSize )
            message = "Solo se permiten imagenes de tamaño hasta 2 MB";
        
        if( message.equals("") && (! UtilsFile.validateExtension(categoryDto.getImage(), extensions)) )
            message = "Solo se permiten imagenes con extensiones: "+ UtilsLocal.arrayStringToString(extensions);
        
        if( ! message.equals("") ){
            response.setError( HttpStatus.BAD_REQUEST.value(), 
                message, 
                "", 
                UtilsLocal.emptyErrorList(),
                this.myClassName, 
                categoryDto.toString(), 
                req
            );
            return new ResponseEntity<>( response, HttpStatus.BAD_REQUEST );
        }
        
        if( bindingResult.hasErrors() ){
            response.setError( HttpStatus.BAD_REQUEST.value(), "", "", 
                bindingResult.getAllErrors(),
                this.myClassName, 
                categoryDto.toString(), 
                req
            );
            return new ResponseEntity<>( response, HttpStatus.BAD_REQUEST );
        } 
        try
        {
            Object row = this.categoryService.save( categoryDto );
            HttpStatus httpStatus = response.validateService( row, 
                "Categoría guardada correctamente",
                this.myClassName, 
                categoryDto.toString(), 
                req
            );
            return new ResponseEntity<Object>( response, httpStatus );
        }
        catch ( Exception e ){
            response.setError( HttpStatus.BAD_REQUEST.value(), 
                "No se pudo guardar la categoría", 
                e.getMessage(), 
                UtilsLocal.emptyErrorList(),
                this.myClassName, 
                categoryDto.toString(), 
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
            Object resDel = this.categoryService.delete(id);
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
                "No se pudo eliminar la categoría.", 
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
                "No se pudo eliminar la categoría", 
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
