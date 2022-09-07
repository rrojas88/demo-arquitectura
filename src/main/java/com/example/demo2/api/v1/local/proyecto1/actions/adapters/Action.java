
package com.example.demo2.api.v1.local.proyecto1.actions.adapters;

public abstract class Action {
    
    Integer id;
    Integer module_id;
    String code;
    String name;
    boolean active;

    public abstract Integer getId();

    public abstract void setId(Integer id);

    public abstract Integer getModule_id();

    public abstract void setModule_id(Integer module_id);
    
    public abstract String getCode();

    public abstract void setCode(String code);

    public abstract String getName();

    public abstract void setName(String name);

    public abstract boolean getActive();

    public abstract void setActive(boolean active);

}
