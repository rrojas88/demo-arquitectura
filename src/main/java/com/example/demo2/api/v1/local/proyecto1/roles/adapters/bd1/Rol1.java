
package com.example.demo2.api.v1.local.proyecto1.roles.adapters.bd1;

import com.example.demo2.api.v1.local.proyecto1.roles.adapters.Rol;
import com.example.demo2.api.v1.local.proyecto1.roles.adapters.RolName;
import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "roles")
public class Rol1 extends Rol {
    
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( unique = true, nullable = false )
    private Integer id;
    
    @NotNull
    //@Enumerated(EnumType.STRING) //Este
    //private RolName name; //Este
    private String name;
    //private Byte active;
    private boolean active;
    
    public Rol1(){
    }

    //public Rol1(@NotNull RolName name) {
    public Rol1(@NotNull String name) {
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

    //public RolName getName() { // Este
    public String getName() {
        return name;
    }

    //public void setName(RolName name) {
    public void setName(String name) {
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

    @Override
    public String toString() {
        return "Rol1{" + "id=" + id + ", name=" + name + ", active=" + active + '}';
    }
    
}
