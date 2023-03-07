package com.crm.operis_app.model.NonConformite.Utils;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "GRAVITE")
public class GraviteNonConformite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="GRAVITE_ID")
    private Long graviteId;

    @Column(name="GRAVITE_NC")
    private String graviteNc;

    @Column(name="DESCRIPTION_GRAVITE")
    private String descriptionGravite;

    @Column(name="ACTIVE")
    private Boolean active=true;

    public Long getGraviteId() {
        return graviteId;
    }

    public void setGraviteId(Long graviteId) {
        this.graviteId = graviteId;
    }

    public String getGraviteNc() {
        return graviteNc;
    }

    public void setGraviteNc(String graviteNc) {
        this.graviteNc = graviteNc;
    }

    public String getDescriptionGravite() {
        return descriptionGravite;
    }

    public void setDescriptionGravite(String descriptionGravite) {
        this.descriptionGravite = descriptionGravite;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
