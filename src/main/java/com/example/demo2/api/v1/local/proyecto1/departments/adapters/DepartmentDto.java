
package com.example.demo2.api.v1.local.proyecto1.departments.adapters;

import com.example.demo2.api.v1.local.proyecto1.departments.adapters.bd1.DepartmentDto1;
import javax.validation.constraints.*;

public class DepartmentDto extends  DepartmentDto1 {
    
    private Integer id;
    @NotBlank(message = "El código del departamento es requerido.")
    private String code;
    @NotEmpty(message = "El nombre del departamento es requerido.")
    private String name;
    private boolean active;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return "DepartmentDto{" + "id=" + id + ", code=" + code + ", name=" + name + ", active=" + active + '}';
    }
    
        
}
