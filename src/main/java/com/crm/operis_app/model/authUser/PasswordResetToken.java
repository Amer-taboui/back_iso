package com.crm.operis_app.model.authUser;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "PASSWORD_RESET_TOKEN")
public class PasswordResetToken implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RESET_TOKEN_ID")
    Long reset_token_id;


    @Column(name = "TOKEN", nullable = false, unique = true)
    String token;

    @OneToOne(targetEntity = User.class)
    @JoinColumn(nullable = false, name = "USER_ID")
    User user;

    @Column(name = "EXP_DATE", nullable = false)
    Date expiryDate;

    public Long getReset_token_id() {
        return reset_token_id;
    }

    public void setReset_token_id(Long reset_token_id) {
        this.reset_token_id = reset_token_id;

    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {

        this.user = user;
    }


    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setExpiryDate(int minutes) {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.MINUTE, minutes);
        this.expiryDate = now.getTime();
    }

    public boolean isExpired() {
        return new Date().after(this.expiryDate);
    }


}
