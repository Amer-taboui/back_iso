package com.crm.operis_app.model.NonConformite;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Data
@Table(name = "LISTE_NON_CONFORMITE")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ListeNonConformite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LISTE_NON_CONFORMITE_ID")
    private Long id;

    @Column(name = "RESPONSABLE_DECOUVERTE")
    private String responsableDecouverte;

    @Column(name = "DESIGNATION_NC")
    private String designationNc;

    @Column(name = "RESPONSABLE_ANALYSE")
    private String responsableAnalyse;

    @Column(name = "ANALYSE_RESULTAT")
    private String analyseResultat;

    @Column(name = "ETAT_ANALYSE")
    private Boolean etatAnalyse = true;

    @Column(name = "TAUX_TRAITEMENT")
    private String tauxTraitement;

    @Column(name = "COMMENTAIRE")
    private String commentaire;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "DATE_CREATION")
    private Date dateCreation;

    @Column(name = "ACTIVE")
    private Boolean active = true;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResponsableDecouverte() {
        return responsableDecouverte;
    }

    public void setResponsableDecouverte(String responsableDecouverte) {
        this.responsableDecouverte = responsableDecouverte;
    }

    public String getDesignationNc() {
        return designationNc;
    }

    public void setDesignationNc(String designationNc) {
        this.designationNc = designationNc;
    }

    public String getResponsableAnalyse() {
        return responsableAnalyse;
    }

    public void setResponsableAnalyse(String responsableAnalyse) {
        this.responsableAnalyse = responsableAnalyse;
    }

    public String getAnalyseResultat() {
        return analyseResultat;
    }

    public void setAnalyseResultat(String analyseResultat) {
        this.analyseResultat = analyseResultat;
    }

    public Boolean getEtatAnalyse() {
        return etatAnalyse;
    }

    public void setEtatAnalyse(Boolean etatAnalyse) {
        this.etatAnalyse = etatAnalyse;
    }

    public String getTauxTraitement() {
        return tauxTraitement;
    }

    public void setTauxTraitement(String tauxTraitement) {
        this.tauxTraitement = tauxTraitement;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
