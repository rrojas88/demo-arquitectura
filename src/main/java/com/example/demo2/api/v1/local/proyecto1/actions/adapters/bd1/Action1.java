
package com.example.demo2.api.v1.local.proyecto1.actions.adapters.bd1;

import com.example.demo2.api.v1.local.proyecto1.actions.adapters.Action;
import javax.persistence.*;

@Entity
@Table(name = "actions")
public class Action1 extends Action {
    
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( unique = true, nullable = false )
    private Integer id;
    
    private Integer module_id;
    private String code;
    private String name;
    private boolean active;
    //private Byte active;

    public Action1() {
        //this.active = 1;
        this.active = true;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getModule_id() {
        return module_id;
    }

    public void setModule_id(Integer module_id) {
        this.module_id = module_id;
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
        return "Action{" + "id=" + id + ", module_id=" + module_id + ", code=" + code + ", name=" + name + ", active=" + active + '}';
    }
}
