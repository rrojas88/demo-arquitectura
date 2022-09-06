
package com.example.demo2.api.v1.local.proyecto1.mytasks.adapters;

import com.example.demo2.api.v1.local.proyecto1.mytasks.adapters.bd2.MytaskDto2;
import javax.validation.constraints.*;

public class MytaskDto extends  MytaskDto2 {
    
    private Integer id;
    @NotEmpty(message = "El nombre del departamento es requerido.")
    private String name;
    private Byte active;
    //private boolean active;

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

    public Byte getActive() {
    //public boolean getActive() {
        return active;
    }

    public void setActive(Byte active) {
    //public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "MytasksDto{" + "id=" + id + ", name=" + name + ", active=" + active + '}';
    }
    
        
}
