
package com.example.demo2.api.v1.local.proyecto1.users.adapters.bd1;

import java.util.Set;

public abstract class UserDto1 {
    
    String name;
    String email;
    String password;
    boolean active;
    
    //public abstract Set<String> roles;

    public abstract String getName();

    public abstract void setName(String name);

    public abstract String getEmail();

    public abstract void setEmail(String email);

    public abstract String getPassword();

    public abstract void setPassword(String password);

    //public Byte getActive() {
    public abstract boolean getActive();

    //public void setActive(Byte active) {
    public abstract void setActive(boolean active);

    public abstract Set<String> getRoles();

    public abstract void setRoles(Set<String> roles);

}
