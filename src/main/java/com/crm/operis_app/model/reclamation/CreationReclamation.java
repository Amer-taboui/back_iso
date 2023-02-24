package com.crm.operis_app.model.reclamation;

import com.crm.operis_app.model.GRH.Personal;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "CREATION_RECLAMATION")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CreationReclamation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CREATION_RECLAMATION_ID")
    private Long id;
    @Column(name = "ETAT_CREATION")
    private Boolean etatCreation = false;
    @Column(name = "COMMENTAIRE")
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

    public Boolean getEtatCreation() {
        return etatCreation;
    }

    public void setEtatCreation(Boolean etatCreation) {
        this.etatCreation = etatCreation;
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
