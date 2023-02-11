package com.crm.operis_app.model.action.actionCorrection;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ETAT_ACTION")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class EtatAction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ETAT_ACTION_ID")
    private Long id;

    @Column(name = "RESPONSABLE_CLOTURE")
    private String responsableCloture;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name="DATE_CLOTURE")
    private Date dateCloture;

    @Column(name="CLOTURE",nullable = true)
    private boolean cloture = false;

    //-----------------ListeActionCorrection-------------------------//

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "listeActionCorrectionId")
    private ListeActionCorrection listeActionCorrection;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateCloture() {
        return dateCloture;
    }

    public void setDateCloture(Date dateCloture) {
        this.dateCloture = dateCloture;
    }

    public boolean isCloture() {
        return cloture;
    }

    public void setCloture(boolean cloture) {
        this.cloture = cloture;
    }

    public String getResponsableCloture() {
        return responsableCloture;
    }

    public void setResponsableCloture(String responsableCloture) {
        this.responsableCloture = responsableCloture;
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
