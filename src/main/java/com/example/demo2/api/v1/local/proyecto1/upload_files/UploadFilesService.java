
package com.example.demo2.api.v1.local.proyecto1.upload_files;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;


@Service 
public class UploadFilesService implements FileStorageService {
    
    private final Path root = Paths.get("uploads");
    
    @Override
    public void init(){
        try {
            Files.createDirectory(root);
        }
        catch (Exception e) {
            throw new RuntimeException("No se puedo inicializar: upload");
        }
    }
    
    @Override
    public void save( MultipartFile file ){
        try {
            Files.copy( file.getInputStream(), this.root.resolve( file.getOriginalFilename() ) );
        }
        catch (Exception e) {
            throw new RuntimeException("No se pudo guardar el Archivo. Error: " + e.getMessage());
        }
    }
    
    @Override
    public Resource load( String filename ){
        try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource( file.toUri() );
            if( resource.exists() || resource.isReadable() ){
                return resource;
            }
            else{
                throw new RuntimeException("No se puede leer el archivo");
            }
        } 
        catch (Exception e) {
            throw new RuntimeException("Error cargando archivo: " + e.getMessage());
        }
    }
    
    @Override
    public void deleteAll(){
        FileSystemUtils.deleteRecursively(root.toFile());
    }
    
    @Override
    public Stream<Path> loadAll(){
        try {
            return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
        }
        catch (Exception e) {
            throw new RuntimeException("No se puediron cargar los Archivos");
        }
    }
    
}
