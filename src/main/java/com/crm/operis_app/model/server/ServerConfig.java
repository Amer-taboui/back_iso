package com.crm.operis_app.model.server;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;


@Entity
@Table(name = "SERVER_CONFIG")
public class ServerConfig implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "SERVER_PORT")
    private int serverPort;

    @Email
    @Column(name = "EMAIL")
    private String email;

    @Column(name = "SMTP")
    private String smtp;

    @Column(name = "transport_protocol")
    private String transportProtocol;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "SERVER_SSL")
    private Boolean ssl;

    @Column(name = "USERS_NUMBER")
    private String usersNumber;

    @Column(name = "NOTIFICATION")
    private Boolean notification;

    @Column(name = "DELAI_RAPPEL_NOTIFICATION")
    private int delaiRappelNotification = 15;

    @Column(name = "DB_NAME")
    private String dbName;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getServerPort() {
        return serverPort;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSmtp() {
        return smtp;
    }

    public void setSmtp(String smtp) {
        this.smtp = smtp;
    }

    public String getTransportProtocol() {
        return transportProtocol;
    }

    public void setTransportProtocol(String transportProtocol) {
        this.transportProtocol = transportProtocol;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getSsl() {
        return ssl;
    }

    public void setSsl(Boolean ssl) {
        this.ssl = ssl;
    }


    public String getUsersNumber() {
        return usersNumber;
    }

    public void setUsersNumber(String usersNumber) {
        this.usersNumber = usersNumber;
    }

    public Boolean getNotification() {
        return notification;
    }

    public void setNotification(Boolean notification) {
        this.notification = notification;
    }

    public int getDelaiRappelNotification() {
        return delaiRappelNotification;
    }

    public void setDelaiRappelNotification(int delaiRappelNotification) {
        this.delaiRappelNotification = delaiRappelNotification;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }
}