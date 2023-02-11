package com.crm.operis_app.model.action.actionCorrection;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "VALIDATION_ACTION")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ValidationAction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VALIDATION_ACTION_ID")
    private Long id;
    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "DATE_VALIDATION")
    private Date dateValidation;
    @Column(name="ETAT_VALIDATION")
    private boolean etatValidation = false;

    //-----------------ListeActionCorrection-------------------------//

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "listeActionCorrectionId")
    private ListeActionCorrection listeActionCorrection;

    //-------------------------------------------------------------//


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateValidation() {
        return dateValidation;
    }

    public void setDateValidation(Date dateValidation) {
        this.dateValidation = dateValidation;
    }

    public boolean isEtatValidation() {
        return etatValidation;
    }

    public void setEtatValidation(boolean etatValidation) {
        this.etatValidation = etatValidation;
    }
    @JsonIgnore
    public ListeActionCorrection getListeActionCorrection() {
        return listeActionCorrection;
    }
    @JsonIgnore
    public void setListeActionCorrection(ListeActionCorrection listeActionCorrection) {
        this.listeActionCorrection = listeActionCorrection;
    }
}
