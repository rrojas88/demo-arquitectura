
package com.example.demo2.api.v1.local.proyecto1.permissions.adapters.bd1;

import com.example.demo2.api.v1.local.proyecto1.permissions.adapters.Permission;
import javax.persistence.*;

@Entity
@Table(name = "permissions")
public class Permission1 extends Permission {
    
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( unique = true, nullable = false )
    private Integer id;
    
    private Integer action_id;
    private Integer rol_id;

    public Permission1() {
    }

    public Permission1(Integer action_id, Integer rol_id ) {
        this.action_id = action_id;
        this.rol_id = rol_id;
    }
    
    public Permission1(Integer id, Integer action_id, Integer rol_id) {
        this.id = id;
        this.action_id = action_id;
        this.rol_id = rol_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAction_id() {
        return action_id;
    }

    public void setAction_id(Integer action_id) {
        this.action_id = action_id;
    }

    public Integer getRol_id() {
        return rol_id;
    }

    public void setRol_id(Integer rol_id) {
        this.rol_id = rol_id;
    }

    @Override
    public String toString() {
        return "Permission1{" + "id=" + id + ", action_id=" + action_id + ", rol_id=" + rol_id + " "+ '}';
    }
    
    
}
