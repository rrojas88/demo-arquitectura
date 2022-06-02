
package com.example.demo2.api.v1.local.proyecto1.users;

import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

public class UserDto {
    
    @NotBlank // No NULO /No cadena vacia /No espacio en blanco
    private String name;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;
    private Byte active = 1;
    // Cadenas por ser JSONs
    private Set<String> roles = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Byte getActive() {
        return active;
    }

    public void setActive(Byte active) {
        this.active = active;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
    
}
