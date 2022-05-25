
package com.example.demo2.api.v1.local.proyecto1.departments;

import javax.persistence.*;

@Entity
@Table(name = "departments")
public class Department {
    
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( unique = true, nullable = false )
    private Integer id;
    
    private String code;
    private String name;
    private Byte active;

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

    public Byte getActive() {
        return active;
    }

    public void setActive( Byte active ) {
        this.active = active;
    }
}
