
package com.example.demo2.api.v1.local.proyecto1.categories;

import com.example.demo2.api.v1.local.Utils.ErrorService;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class CategoryService {
    
    @Autowired
    CategoryRepository categoryRepository;
    
    private String myClassName = CategoryService.class.getName();
    
    //private final Path root = Paths.get("uploads");
    private final Path rootPath = Paths.get("uploads");
    private final String root_dir = "";
    
    
    public Object getAll(){
        try {
            return categoryRepository.findAll();
        }
        catch (Exception e) {
            ErrorService errService = new ErrorService(
                "No se listaron las Categorías", 
                e.getMessage(), 
                this.myClassName
            );
            return errService;
        }
    }
    
    public Object getById(Integer id){
        try {
            return categoryRepository.findById(id);
        }
        catch (Exception e) {
            ErrorService errService = new ErrorService(
                "No se obtuvo la Categoría", 
                e.getMessage(), 
                this.myClassName
            );
            return errService;
        }
    }

    public Object getByName(String name) {
        try {
            return categoryRepository.findByName(name);
        }
        catch (Exception e) {
            ErrorService errService = new ErrorService(
                "No se obtuvo la Categoría", 
                e.getMessage(), 
                this.myClassName
            );
            return errService;
        }
    }
    
    public Object save( CategoryDto categoryDto ){ 
        String nameFile = categoryDto.getFile().getOriginalFilename();
        try {
            Files.copy(
                categoryDto.getFile().getInputStream(), 
                this.rootPath.resolve( nameFile ) 
            );
        }
        catch (Exception e) {
            ErrorService errService = new ErrorService(
                "No se pudo cargar la Imagen", 
                e.getMessage(), 
                this.myClassName
            );
            return errService;
        }
        try {
            Category category = new Category();
            if( categoryDto.getId() != null ){
                category.setId( categoryDto.getId() );
                category.setName( categoryDto.getName() );
                category.setImage(this.rootPath +"/"+ nameFile);
                category.setActive( categoryDto.getActive() );
            }
            else{
                category.setName( categoryDto.getName() );
                category.setImage( this.rootPath +"/"+ nameFile );
            }
            return categoryRepository.save(category);
        }
        catch (Exception e) {
            ErrorService errService = new ErrorService(
                "No se pudo guardar la Categoría", 
                e.getMessage(), 
                this.myClassName
            );
            return errService;
        }
    }
/*
    public Object delete(Integer id) {
        try {
            Optional<City> row = categoryRepository.findById(id);
            if( ! row.isEmpty() ){
                categoryRepository.deleteById(id);
                return "Se eliminó el registro con ID: " + id;
            }
            return "No se encontró el registro con ID: " + id;
        }
        catch(Exception e){
            ErrorService errService = new ErrorService(
                "No se eliminó la Categoría", 
                e.getMessage(), 
                this.myClassName
            );
            return errService;
        }
    } */
    
}
