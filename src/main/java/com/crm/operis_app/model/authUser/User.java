package com.crm.operis_app.model.authUser;

import com.crm.operis_app.model.GRH.Personal;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "username"
        }),
        @UniqueConstraint(columnNames = {
                "email"
        })
})
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;


    @Column(name = "USERNAME")
    @NotBlank
    @Size(min = 3, max = 50)
    private String username;

    @Column(name = "EMAIL")
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @Column(name = "PASSWORD")
    @NotBlank
    @Size(min = 6, max = 100)
    private String password;


    @ManyToOne
    @JoinColumn(name = "PERSONNEL_ID", unique = true)
    private Personal personnel;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "USER_ROLES",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    private Set<Role> roles = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL )
    @JoinColumn(name = "IMAGE_USER_ID" )
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("imageUserId")
    private ImageUser imageUser;


    @Column(name = "LANGUAGE")
    private String language = "fr";


    @Column(name = "CURRENT_IP_ADRESS")
    private String currentIpAdress;

    @Column(name = "ACTIVE")
    private Boolean active = true;

    @Column(name = "BLOCKED")
    private Boolean blocked = false;

    public User() {
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


    public Personal getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Personal personnel) {
        this.personnel = personnel;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCurrentIpAdress() {
        return currentIpAdress;
    }

    public void setCurrentIpAdress(String currentIpAdress) {
        this.currentIpAdress = currentIpAdress;
    }


    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getBlocked() {
        return blocked;
    }

    public void setBlocked(Boolean blocked) {
        this.blocked = blocked;
    }

    @JsonProperty("imageUserId")
    public void setImageUser(Long imageUserId) {
        imageUser = imageUser.fromId(imageUserId);
    }

    public ImageUser getImageUser() {
        return imageUser;
    }

    public void addImageUser(ImageUser imageUser) {
        this.imageUser = imageUser;
    }

}