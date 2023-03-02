package com.crm.operis_app.model.NonConformite.Utils;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ORIGINE")
public class OrigineNonConformite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ORIGINE_ID")
    private Long origineId;

    @Column(name="ORIGINE_NC")
    private String origineNc;

    @Column(name="DESCRIPTION_ORIGINE")
    private String descriptionOrigine;

    @Column(name="ACTIVE")
    private Boolean active=true;

    public Long getOrigineId() {
        return origineId;
    }

    public void setOrigineId(Long origineId) {
        this.origineId = origineId;
    }

    public String getOrigineNc() {
        return origineNc;
    }

    public void setOrigineNc(String origineNc) {
        this.origineNc = origineNc;
    }

    public String getDescriptionOrigine() {
        return descriptionOrigine;
    }

    public void setDescriptionOrigine(String descriptionOrigine) {
        this.descriptionOrigine = descriptionOrigine;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
