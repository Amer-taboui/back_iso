package com.crm.operis_app.model.authUser;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "ACCES_LOG")
public class AccessLog implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @Column(name = "access_token")
    @NotNull
    private String accessToken;

    @Column(name = "login_at")
    @NotNull
    private Date loginAt;

    @Column(name = "logout_at")
    private Date logoutAt;

    @Column(name = "IP_ADDRESS")
    private String ipAdress;

    public AccessLog() {
    }

    public AccessLog(User user, @NotNull String accessToken, @NotNull Date loginAt, @NotNull String ipAdress) {
        this.user = user;
        this.accessToken = accessToken;
        this.loginAt = loginAt;
        this.ipAdress = ipAdress;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getIpAdress() {
        return ipAdress;
    }

    public void setIpAdress(String ipAdress) {
        this.ipAdress = ipAdress;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Date getLoginAt() {
        return loginAt;
    }

    public void setLoginAt(Date loginAt) {
        this.loginAt = loginAt;
    }

    public Date getLogoutAt() {
        return logoutAt;
    }

    public void setLogoutAt(Date logoutAt) {
        this.logoutAt = logoutAt;
    }

}
