
package com.example.demo2.api.v1.local.proyecto1.permissions.adapters.bd1;

public abstract class PermissionDto1 {
    
    Integer id;
    Integer action_id;
    Integer rol_id;

    public abstract Integer getId();

    public abstract void setId(Integer id);

    public abstract Integer getAction_id();

    public abstract void setAction_id(Integer action_id);

    public abstract Integer getRol_id();

    public abstract void setRol_id(Integer rol_id);
        
}
