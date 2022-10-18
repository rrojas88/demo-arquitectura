
package com.example.demo2.api.v1.local.proyecto1.users.adapters.bd1;

import com.example.demo2.api.v1.local.proyecto1.roles.adapters.bd1.Rol;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( unique = true, nullable = false )
    private Integer id;
    
    private String name;
    @Column( unique = true, nullable = false )
    private String email;
    private String password;
    //private Byte active;
    private boolean active;

    @ManyToMany( fetch = FetchType.EAGER )
    @JoinTable(name="user_roles", 
        joinColumns=@JoinColumn(name="user_id"),
        inverseJoinColumns = @JoinColumn( name = "rol_id")
    )
    private Set<Rol> roles = new HashSet<>();
    
    public User(){
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        //this.active = 1;
        this.active = true;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    //public Byte getActive() {
    public boolean getActive() {
        return active;
    }

    //public void setActive(Byte active) {
    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", active=" + active + ", roles=" + roles + '}';
    }
    
}
