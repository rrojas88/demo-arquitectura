
package com.example.demo2.api.v1.local.proyecto1.user_roles;

import javax.persistence.*;


@Entity
@Table(name = "user_roles")
public class UserRol {
    
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( unique = true, nullable = false )
    private Integer id;
    
    private Integer user_id;
    private Integer rol_id;
    
    public UserRol(){}

    public UserRol(Integer user_id, Integer rol_id) {
        this.user_id = user_id;
        this.rol_id = rol_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getRol_id() {
        return rol_id;
    }

    public void setRol_id(Integer rol_id) {
        this.rol_id = rol_id;
    }
    
    
    
}
