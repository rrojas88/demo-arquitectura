
package com.example.demo2.api.v1.local.proyecto1.roles.adapters;

public abstract class Rol {
    
    Integer id;
    RolName name;
    boolean active;

    public abstract Integer getId();

    public abstract void setId(Integer id);

    public abstract RolName getName();

    public abstract void setName(RolName name);

    public abstract boolean getActive();

    public abstract void setActive(boolean active);
    
}
