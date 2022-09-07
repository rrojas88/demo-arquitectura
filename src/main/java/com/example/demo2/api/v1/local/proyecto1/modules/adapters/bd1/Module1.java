
package com.example.demo2.api.v1.local.proyecto1.modules.adapters.bd1;

import com.example.demo2.api.v1.local.proyecto1.modules.adapters.Module;
import javax.persistence.*;

@Entity
@Table(name = "modules")
public class Module1 extends Module {
    
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( unique = true, nullable = false )
    private Integer id;
    
    @Column( unique = true )
    private String code;
    @Column( unique = true )
    private String name;
    //private Byte active;
    private boolean active;

    public Module1() {
        //this.active = 1;
        this.active = true;
    }

    public Module1(String code, String name ) {
        this.code = code;
        this.name = name;
        //this.active = 1;
        this.active = true;
    }
    
    //public Department(Integer id, String code, String name, Byte active) {
    public Module1(Integer id, String code, String name, boolean active) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.active = active;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //public Byte getActive() {
    public boolean getActive() {
        return active;
    }

    //public void setActive( Byte active ) {
    public void setActive( boolean active ) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Module{" + "id=" + id + ", code=" + code + ", name=" + name + ", active=" + active + '}';
    }
    
    
}
