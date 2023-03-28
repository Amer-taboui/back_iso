package com.crm.operis_app.model.conformite.Utils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "NATURE_EXIGENCE")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class NatureExigence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NATURE_EXIGENCE_ID")
    private Long id;

    @Column(name = "NATURE_EXIGENCE")
    private String natureExigence;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name="ACTIVE")
    private Boolean active=true;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNatureExigence() {
        return natureExigence;
    }

    public void setNatureExigence(String natureExigence) {
        this.natureExigence = natureExigence;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
