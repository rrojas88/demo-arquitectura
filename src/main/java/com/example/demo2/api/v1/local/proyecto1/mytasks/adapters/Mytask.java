
package com.example.demo2.api.v1.local.proyecto1.mytasks.adapters;

public abstract class Mytask {
    
    Integer id;
    String name;
    Byte active;
    //boolean active;

    public abstract Integer getId();

    public abstract void setId(Integer id);

    public abstract String getName();

    public abstract void setName(String name);

    public abstract Byte getActive();
    //public abstract boolean getActive();

    public abstract void setActive( Byte active );
    //public abstract void setActive( boolean active );
    
}
