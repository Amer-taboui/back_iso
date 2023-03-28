package com.crm.operis_app.model.conformite.Utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "DOMAINE_CONFORMITE")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class DomaineConformite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DOMAINE_CONFORMITE_ID")
    private Long id;

    @Column(name = "DomaineConformite")
    private String domaine;

    @Column(name = "SOUS_DOMAINE_CONFORMITE")
    private String sousDomaineConformite;

    @Column(name="ACTIVE")
    private Boolean active=true;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDomaine() {
        return domaine;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }

    public String getSousDomaineConformite() {
        return sousDomaineConformite;
    }

    public void setSousDomaineConformite(String sousDomaineConformite) {
        this.sousDomaineConformite = sousDomaineConformite;
    }


    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
