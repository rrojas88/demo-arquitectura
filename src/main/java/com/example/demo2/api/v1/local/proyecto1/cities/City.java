
package com.example.demo2.api.v1.local.proyecto1.cities;

import javax.persistence.*;

@Entity
@Table(name = "cities")
public class City {
    
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( unique = true, nullable = false )
    private Integer id;
    
    private Integer department_id;
    private String code;
    private String name;
    private boolean active;
    //private Byte active;

    public City() {
        //this.active = 1;
        this.active = true;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(Integer department_id) {
        this.department_id = department_id;
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

    //public void setActive(Byte active) {
    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "City{" + "id=" + id + ", department_id=" + department_id + ", code=" + code + ", name=" + name + ", active=" + active + '}';
    }
}
