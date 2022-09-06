
package com.example.demo2.api.v1.local.proyecto1.departments.adapters.bd1;

public abstract class DepartmentDto1 {
    
    private Integer id;
    private String code;
    private String name;
    private boolean active;

    public abstract Integer getId();

    public abstract void setId(Integer id);

    public abstract String getCode();

    public abstract void setCode(String code);

    public abstract String getName();

    public abstract void setName(String name);

    public abstract boolean getActive();

    public abstract void setActive(boolean active);    
        
}
