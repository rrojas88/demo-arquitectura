
package com.example.demo2.api.v1.local.proyecto1.roles;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "roles")
public class Rol {
    
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( unique = true, nullable = false )
    private Integer id;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    private RolName name;
    //private String name;
    //private Byte active;
    private boolean active;
    
    public Rol(){
    }

    public Rol(@NotNull RolName name) {
        this.name = name;
        //this.active = 1;
        this.active = true;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RolName getName() {
        return name;
    }

    public void setName(RolName name) {
        this.name = name;
    }

    //public Byte getActive() {
    public boolean getActive() {
        return active;
    }

    //public void setActive(Byte active) {
    public void setActive(boolean active) {
        this.active = active;
    }
    
    
    
}
