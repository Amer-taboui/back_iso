package com.crm.operis_app.services.authUser;

import com.crm.operis_app.model.authUser.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserPrinciple implements UserDetails {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;
    private String lastName;

    private String username;

    private String email;

    private Long personnelId;

    private String language;

    @JsonIgnore
    private String password;

    private Boolean blocked;

    private Collection<? extends GrantedAuthority> authorities;

    public UserPrinciple(Long id,
                         String username, String email, String password, Long personnelId, String language, Boolean blocked,
                         Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.personnelId = personnelId;

        this.language = language;
        this.blocked = blocked;
        this.authorities = authorities;
    }

    public static UserPrinciple build(User user, List<? extends GrantedAuthority> privileges) {
        List<GrantedAuthority> roles = user.getRoles().stream().map(role ->
                new SimpleGrantedAuthority(role.getName())
        ).collect(Collectors.toList());

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.addAll(roles);
        authorities.addAll(privileges);
        return new UserPrinciple(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getPersonnel().getId(),
                user.getLanguage(),
                user.getBlocked(),
                authorities
        );
    }

    public Long getId() {
        return id;
    }


    public String getEmail() {
        return email;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public String getLanguage() {
        return language;
    }

    public Boolean getBlocked() {
        return blocked;
    }

    public Long getPersonnelId() {
        return personnelId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserPrinciple user = (UserPrinciple) o;
        return Objects.equals(id, user.id);
    }

}