
package com.example.demo2.api.v1.local.proyecto1.upload_files;

import java.util.List;
import java.util.stream.Collectors;
import org.apache.tomcat.jni.FileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;


@RestController
@RequestMapping("/v1/local/auth")
public class UploadFilesController {
    
    @Autowired
    //FileStorageService storageService;
    UploadFilesService storageService;
    
    @GetMapping("/files-all")
    public ResponseEntity<?> getAll(  ){
        try {
            List<UploadFiles> fileInfos = (List<UploadFiles>) storageService.loadAll()
                .map( path -> {
                    String filename = path.getFileName().toString();
                    String url = MvcUriComponentsBuilder
                        .fromMethodName(UploadFilesController.class, "getFile", path.getFileName().toString()).build().toString();
                    return new UploadFiles(filename, url);
                })
                .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.OK).body( fileInfos );
        }
        catch (Exception e) {
            String message = "Error listando los archivos: " + e.getMessage();
            return new ResponseEntity<>( message, HttpStatus.BAD_REQUEST );
        }
    }
    
    @GetMapping("/file/{filename:.+}")
    @ResponseBody
    public ResponseEntity<?> getFile( @PathVariable String filename ){
        try {
            Resource file = storageService.load( filename );
            return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + 
                    file.getFilename() + "\"").body(file);
        }
        catch (Exception e) {
            String message = "Error obteniendo un archivo: " + e.getMessage();
            return new ResponseEntity<>( message, HttpStatus.BAD_REQUEST );
        }
    }
    
    @PostMapping("/files")
    public ResponseEntity<?> save( @RequestParam("file") MultipartFile file ){
        String message = "";
        try {
            storageService.save(file);
            message = "Cargado con éxito: "+ file.getOriginalFilename();
            return new ResponseEntity<>( message, HttpStatus.OK );
        }
        catch (Exception e) {
            message = "No se cargó el Archivo: " + e.getMessage();
            return new ResponseEntity<>( message, HttpStatus.BAD_REQUEST );
        }
    }
    
}
