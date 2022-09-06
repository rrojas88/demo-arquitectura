
package com.example.demo2.api.v1.local.proyecto1.users.adapters;

import com.example.demo2.api.v1.local.proyecto1.roles.adapters.bd1.Rol1;
import java.util.HashSet;
import java.util.Set;


public abstract class User {
    
    Integer id;
    String name;
    String email;
    String password;
    boolean active;

    private Set<Rol1> roles = new HashSet<>();

    public abstract Integer getId();

    public abstract void setId(Integer id);

    public abstract String getName();

    public abstract void setName(String name);

    public abstract String getEmail();

    public abstract void setEmail(String email);

    public abstract String getPassword();

    public abstract void setPassword(String password);
    
    public abstract boolean getActive();

    public abstract void setActive(boolean active);

    public abstract Set<Rol1> getRoles();

    public abstract void setRoles(Set<Rol1> roles);
    
    
    
}
