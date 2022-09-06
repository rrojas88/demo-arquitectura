
package com.example.demo2.api.v1.local.proyecto1.cities.adapters;

import com.example.demo2.api.v1.local.proyecto1.cities.adapters.bd1.CityDto1;
import javax.validation.constraints.*;


public class CityDto extends CityDto1 {
    
    private Integer id;
    @NotNull(message = "El identificador del departamento es requerido.")
    private Integer department_id;
    @NotBlank(message = "El código de la ciudad es requerido.")
    private String code;
    @NotEmpty(message = "El nombre de la ciudad es requerido.")
    private String name;
    private boolean active;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(Integer department_id) {
        this.department_id = department_id;
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

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "CityDto{" + "id=" + id + ", department_id=" + department_id + ", code=" + code + ", name=" + name + ", active=" + active + '}';
    }
}
