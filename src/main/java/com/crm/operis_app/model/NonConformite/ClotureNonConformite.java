package com.crm.operis_app.model.NonConformite;

import com.crm.operis_app.model.GRH.Personal;
import com.crm.operis_app.model.reclamation.ListeReclamation;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "CLOTURE_NON_CONFORMITE")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ClotureNonConformite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CLOTURE_NON_CONFORMITE_ID")
    private Long id;

    @Column(name = "ETAT_NON_CONFORMITE_CLOTURE")
    private Boolean etatNonConformite = false;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "DATE_CLOTURE")
    private Date dateCloture;

    @Column(name = "COMMENTAIRE_CLOTURE")
    private String commentaire;

    //-----------------ListeReclamation-------------------------//

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "listeNonConformiteId")
    private ListeNonConformite listeNonConformite;

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

    public Boolean getEtatNonConformite() {
        return etatNonConformite;
    }

    public void setEtatNonConformite(Boolean etatNonConformite) {
        this.etatNonConformite = etatNonConformite;
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
    public ListeNonConformite getListeNonConformite() {
        return listeNonConformite;
    }
    @JsonIgnore
    public void setListeNonConformite(ListeNonConformite listeNonConformite) {
        this.listeNonConformite = listeNonConformite;
    }

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }
}
