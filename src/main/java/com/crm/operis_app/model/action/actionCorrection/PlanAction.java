package com.crm.operis_app.model.action.actionCorrection;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PLAN_ACTION")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PlanAction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PLAN_ACTION_ID")
    private Long id;
    @Column(name = "RESPONSABLE_SUIVI")
    private String responsableSuivi;
    @Column(name="TAUX_REALISATION")
    private int tauxRealisation;
    @Column(name="TAUX_EFFICACITE")
    private int tauxEfficacite;
    @Column(name="COMMENTAIRE_SUIVI")
    private String commentaireSuivi;


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

    public String getResponsableSuivi() {
        return responsableSuivi;
    }

    public void setResponsableSuivi(String responsableSuivi) {
        this.responsableSuivi = responsableSuivi;
    }

    public int getTauxRealisation() {
        return tauxRealisation;
    }

    public void setTauxRealisation(int tauxRealisation) {
        this.tauxRealisation = tauxRealisation;
    }

    public int getTauxEfficacite() {
        return tauxEfficacite;
    }

    public void setTauxEfficacite(int tauxEfficacite) {
        this.tauxEfficacite = tauxEfficacite;
    }

    public String getCommentaireSuivi() {
        return commentaireSuivi;
    }

    public void setCommentaireSuivi(String commentaireSuivi) {
        this.commentaireSuivi = commentaireSuivi;
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
