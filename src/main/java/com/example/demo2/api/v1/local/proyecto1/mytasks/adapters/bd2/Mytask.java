
package com.example.demo2.api.v1.local.proyecto1.mytasks.adapters.bd2;

import javax.persistence.*;

@Entity
@Table(name = "mytasks")
public class Mytask {
    
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( unique = true, nullable = false )
    private Integer id;
    
    @Column( unique = true )
    private String name;
    private Byte active;
    //private boolean active;

    public Mytask() {
        this.active = 1;
        //this.active = true;
    }

    public Mytask( String name ) {
        this.name = name;
        this.active = 1;
        //this.active = true;
    }
    
    public Mytask(Integer id, String name, Byte active) {
    //public Mytasks2(Integer id, String code, String name, boolean active) {
        this.id = id;
        this.name = name;
        this.active = active;
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
    //public boolean getActive() {
        return active;
    }

    public void setActive( Byte active ) {
    //public void setActive( boolean active ) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Mytasks{" + "id=" + id + ", name=" + name + ", active=" + active + '}';
    }
    
    
}
