
package com.example.demo2.api.v1.local.proyecto1.categories;

import javax.validation.constraints.*;
import org.springframework.web.multipart.MultipartFile;

public class CategoryDto {
    
    private Integer id;
    @NotEmpty(message = "El nombre de la categoría es requerido.")
    private String name;
    //@NotBlank(message = "La imagen es requerida.")
    private String image;
    private MultipartFile file;
    //private Byte active;
    private boolean active;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    //public Byte getActive() {
    public boolean getActive() {
        return active;
    }

    //public void setActive(Byte active) {
    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "CategoryDto{" + "id=" + id + ", name=" + name + ", image=" + image + ", active=" + active + '}';
    }
}
