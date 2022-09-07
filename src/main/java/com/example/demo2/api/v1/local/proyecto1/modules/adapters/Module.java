
package com.example.demo2.api.v1.local.proyecto1.modules.adapters;

public abstract class Module {
    
    Integer id;
    String code;
    String name;
    //Byte active;
    boolean active;

    public abstract Integer getId();

    public abstract void setId(Integer id);

    public abstract String getCode();

    public abstract void setCode(String code);

    public abstract String getName();

    public abstract void setName(String name);

    //public Byte getActive() {
    public abstract boolean getActive();

    //public void setActive( Byte active ) {
    public abstract void setActive( boolean active );

}
