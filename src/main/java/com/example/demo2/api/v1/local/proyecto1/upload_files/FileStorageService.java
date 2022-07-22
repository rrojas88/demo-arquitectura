
package com.example.demo2.api.v1.local.proyecto1.upload_files;

import java.util.stream.Stream;
import org.springframework.core.io.Resource;
import java.nio.file.Path;
import org.springframework.web.multipart.MultipartFile;


public interface FileStorageService {
    public void init();
    public void save(MultipartFile file);
    public Resource load(String filename);
    public void deleteAll();
    public Stream<Path> loadAll();
}
