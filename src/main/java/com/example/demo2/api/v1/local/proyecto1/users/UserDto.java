
package com.example.demo2.api.v1.local.proyecto1.users;

import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

public class UserDto {
    
    // No NULO /No cadena vacia /No espacio en blanco
    @NotBlank(message = "El nombre es requerido.")
    private String name;
    @NotBlank(message = "El correo es requerido.")
    @Email(message = "Debe ingresar un correo válido.")
    private String email;
    @NotBlank(message = "La contraseña es requerida.")
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

    @Override
    public String toString() {
        return "UserDto{" + "name=" + name + ", email=" + email + ", password=" + password + ", active=" + active + ", roles=" + roles + '}';
    }
}
