
package com.example.demo2.api.v1.local.proyecto1.mytasks.adapters.bd2;

public abstract class MytaskDto2 {
    
    private Integer id;
    private String name;
    private boolean active;

    public abstract Integer getId();

    public abstract void setId(Integer id);

    public abstract String getName();

    public abstract void setName(String name);

    public abstract Byte getActive();

    public abstract void setActive(Byte active);    
        
}
