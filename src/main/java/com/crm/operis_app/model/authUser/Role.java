package com.crm.operis_app.model.authUser;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ROLES")
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROLE_ID")
    private Long id;


    @Column(name = "ROLE_NAME", length = 60)
    private String name;

    @ManyToMany
    @JoinTable(name = "roles_privileges", joinColumns = {@JoinColumn(name = "ROLE_ID")}, inverseJoinColumns = {
            @JoinColumn(name = "PRIVILEGE_ID")})
    private Collection<Privilege> privileges;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(Collection<Privilege> privileges) {
        this.privileges = privileges;
    }
}