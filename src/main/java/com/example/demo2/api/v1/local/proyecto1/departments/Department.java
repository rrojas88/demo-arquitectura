
package com.example.demo2.api.v1.local.proyecto1.departments;

import javax.persistence.*;

@Entity
@Table(name = "departments")
public class Department {
    
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

    public Department() {
        //this.active = 1;
        this.active = true;
    }

    public Department(String code, String name ) {
        this.code = code;
        this.name = name;
        //this.active = 1;
        this.active = true;
    }
    
    //public Department(Integer id, String code, String name, Byte active) {
    public Department(Integer id, String code, String name, boolean active) {
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
        return "Department{" + "id=" + id + ", code=" + code + ", name=" + name + ", active=" + active + '}';
    }
    
    
}
