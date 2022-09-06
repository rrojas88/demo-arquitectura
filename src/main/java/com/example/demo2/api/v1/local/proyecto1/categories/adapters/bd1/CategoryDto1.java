
package com.example.demo2.api.v1.local.proyecto1.categories.adapters.bd1;

import org.springframework.web.multipart.MultipartFile;

public abstract class CategoryDto1 {
    
    Integer id;
    String name;
    String image;
    MultipartFile file;
    boolean active;

    public abstract Integer getId();

    public abstract void setId(Integer id);

    public abstract String getName();

    public abstract void setName(String name);

    public abstract String getImage();

    public abstract void setImage(String image);

    public abstract MultipartFile getFile();

    public abstract void setFile(MultipartFile file);

    public abstract boolean getActive();

    public abstract void setActive(boolean active);

}
