
package com.example.demo2.api.v1.local.proyecto1.permissions.adapters;

import com.example.demo2.api.v1.local.proyecto1.permissions.adapters.bd1.PermissionDto1;
import javax.validation.constraints.*;

public class PermissionDto extends  PermissionDto1 {
    
    private Integer id;
    @NotNull(message = "El identificador de la acción es requerido.")
    private Integer action_id;
    @NotNull(message = "El identificador del rol es requerido.")
    private Integer rol_id;
    private boolean active;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAction_id() {
        return action_id;
    }

    public void setAction_id(Integer action_id) {
        this.action_id = action_id;
    }

    public Integer getRol_id() {
        return rol_id;
    }

    public void setRol_id(Integer rol_id) {
        this.rol_id = rol_id;
    }

    @Override
    public String toString() {
        return "PermissionDto{" + "id=" + id + ", action_id=" + action_id + ", rol_id=" + rol_id + ", active=" + active + '}';
    }
    
        
}
