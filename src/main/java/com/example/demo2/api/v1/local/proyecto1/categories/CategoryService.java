
package com.example.demo2.api.v1.local.proyecto1.categories;

import com.example.demo2.api.v1.local.Utils.ErrorService;
import com.example.demo2.api.v1.local.proyecto1.categories.adapters.CategoryAdapter;
import com.example.demo2.api.v1.local.proyecto1.categories.adapters.CategoryDto;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;


@Service
public class CategoryService {
    
    @Autowired
    CategoryAdapter categoryAdapter;
    
    private String myClassName = CategoryService.class.getName();
    
    private final Path rootPath = Paths.get("uploads");
    private final String root_dir = "";
    
    
    public Object getAll(){
        try {
            return categoryAdapter.getAll();
        }
        catch (Exception e) {
            return new ErrorService(
                "No se listaron las Categorías", 
                e.getMessage(), 
                this.myClassName
            );
        }
    }
    
    public Object getById(Integer id){
        try {
            return categoryAdapter.getById(id);
        }
        catch (Exception e) {
            return new ErrorService(
                "No se obtuvo la Categoría", 
                e.getMessage(), 
                this.myClassName
            );
        }
    }

    public Object getByName(String name) {
        try {
            return categoryAdapter.getByName(name);
        }
        catch (Exception e) {
            return new ErrorService(
                "No se obtuvo la Categoría", 
                e.getMessage(), 
                this.myClassName
            );
        }
    }
    
    public Object save( CategoryDto categoryDto ){
        try {
            return categoryAdapter.save(categoryDto);
        }
        catch (Exception e) {
            return new ErrorService(
                "No se pudo guardar la Categoría", 
                e.getMessage(), 
                this.myClassName
            );
        }
    }

    
    public Object delete(Integer id) {
        try {
            return categoryAdapter.delete(id);
        }
        catch(Exception e){
            return new ErrorService(
                "No se eliminó la Categoría", 
                e.getMessage(), 
                this.myClassName
            );
        }
    }
    
}
