package com.crm.operis_app.model.NonConformite.Utils;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CATEGORIE")
public class CategorieNonConformite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="CATEGORIE_ID")
    private Long categorieId;

    @Column(name="CATEGORIE_NC")
    private String categorieNc;

    @Column(name="DESCRIPTION_NC")
    private String descriptionNc;

    @Column(name="ACTIVE")
    private Boolean active=true;

    public Long getCategorieId() {
        return categorieId;
    }

    public void setCategorieId(Long categorieId) {
        this.categorieId = categorieId;
    }

    public String getCategorieNc() {
        return categorieNc;
    }

    public void setCategorieNc(String categorieNc) {
        this.categorieNc = categorieNc;
    }

    public String getDescriptionNc() {
        return descriptionNc;
    }

    public void setDescriptionNc(String descriptionNc) {
        this.descriptionNc = descriptionNc;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
