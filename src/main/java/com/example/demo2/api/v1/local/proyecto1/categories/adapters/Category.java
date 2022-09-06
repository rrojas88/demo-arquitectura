
package com.example.demo2.api.v1.local.proyecto1.categories.adapters;

public abstract class Category {
    
    Integer id;
    String name;
    String image;
    boolean active;

    public abstract Integer getId();

    public abstract void setId(Integer id);

    public abstract String getName();

    public abstract void setName(String name);

    public abstract String getImage();

    public abstract void setImage(String image);

    public abstract boolean getActive();

    public abstract void setActive(boolean active);

}
