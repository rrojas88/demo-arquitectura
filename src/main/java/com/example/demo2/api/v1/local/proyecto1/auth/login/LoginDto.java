
package com.example.demo2.api.v1.local.proyecto1.auth.login;

import javax.validation.constraints.NotBlank;

public class LoginDto {
    
    @NotBlank(message = "El correo es requerido.")
    private String email;
    @NotBlank(message = "La contraseña es requerida.")
    private String password;

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

    @Override
    public String toString() {
        return "LoginDto{" + "email=" + email + ", password=" + password + '}';
    }
}
