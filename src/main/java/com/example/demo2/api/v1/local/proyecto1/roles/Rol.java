
package com.example.demo2.api.v1.local.proyecto1.roles;

import javax.persistence.*;


@Entity
@Table(name = "roles")
public class Rol {
    
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( unique = true, nullable = false )
    private Integer id;
    
    private String name;
    private Byte active;
    
    public Rol(){
    }

    public Rol(String name) {
        this.name = name;
        this.active = 1;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getActive() {
        return active;
    }

    public void setActive(Byte active) {
        this.active = active;
    }
    
    
    
}
