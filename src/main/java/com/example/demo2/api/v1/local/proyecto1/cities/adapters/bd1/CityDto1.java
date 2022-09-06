
package com.example.demo2.api.v1.local.proyecto1.cities.adapters.bd1;

public abstract class CityDto1 {
    
    Integer id;
    Integer department_id;
    String code;
    String name;
    boolean active;

    public abstract Integer getId();

    public abstract void setId(Integer id);

    public abstract Integer getDepartment_id();

    public abstract void setDepartment_id(Integer department_id);

    public abstract String getCode();

    public abstract void setCode(String code);

    public abstract String getName();

    public abstract void setName(String name);

    public abstract boolean getActive();

    public abstract void setActive(boolean active);

}
