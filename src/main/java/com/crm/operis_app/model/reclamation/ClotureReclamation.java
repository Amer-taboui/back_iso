package com.crm.operis_app.model.reclamation;

import com.crm.operis_app.model.GRH.Personal;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "CLOTURE_RECLAMATION")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ClotureReclamation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CLOTURE_RECLAMATION_ID")
    private Long id;
    @Column(name = "ETAT_RECLAMATION_CLOTURE")
    private Boolean etatReclamation = false;
    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "DATE_CLOTURE")
    private Date dateCloture;
    @Column(name = "COMMENTAIRE_CLOTURE")
    private String commentaire;

    //-----------------ListeReclamation-------------------------//

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "listeReclamationId")
    private ListeReclamation listeReclamation;

    //--------------personal------------------------------/
    @OrderBy("id ASC")
    @ManyToOne
    @JoinColumn(name = "PERSONNEL_ID")
    private Personal personal;
    //----------------------------//


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getEtatReclamation() {
        return etatReclamation;
    }

    public void setEtatReclamation(Boolean etatReclamation) {
        this.etatReclamation = etatReclamation;
    }

    public Date getDateCloture() {
        return dateCloture;
    }

    public void setDateCloture(Date dateCloture) {
        this.dateCloture = dateCloture;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }
    @JsonIgnore
    public ListeReclamation getListeReclamation() {
        return listeReclamation;
    }
    @JsonIgnore
    public void setListeReclamation(ListeReclamation listeReclamation) {
        this.listeReclamation = listeReclamation;
    }

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }
}
