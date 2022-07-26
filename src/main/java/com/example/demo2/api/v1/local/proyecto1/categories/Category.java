
package com.example.demo2.api.v1.local.proyecto1.categories;

import javax.persistence.*;


@Entity
@Table(name = "categories")
public class Category {
    
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( unique = true, nullable = false )
    private Integer id;
    
    private String name;
    private String image;
    private Byte active;

    public Category() {
        this.active = 1;
    }

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

    public Byte getActive() {
        return active;
    }

    public void setActive(Byte active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Category{" + "id=" + id + ", name=" + name + ", image=" + image + ", active=" + active + '}';
    }
}
