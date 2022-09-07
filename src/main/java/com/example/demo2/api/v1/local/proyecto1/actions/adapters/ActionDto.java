
package com.example.demo2.api.v1.local.proyecto1.actions.adapters;

import com.example.demo2.api.v1.local.proyecto1.actions.adapters.bd1.ActionDto1;
import javax.validation.constraints.*;


public class ActionDto extends ActionDto1 {
    
    private Integer id;
    @NotNull(message = "El identificador del departamento es requerido.")
    private Integer module_id;
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

    public Integer getModule_id() {
        return module_id;
    }

    public void setModule_id(Integer module_id) {
        this.module_id = module_id;
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
        return "Action{" + "id=" + id + ", module_id=" + module_id + ", code=" + code + ", name=" + name + ", active=" + active + '}';
    }
}
