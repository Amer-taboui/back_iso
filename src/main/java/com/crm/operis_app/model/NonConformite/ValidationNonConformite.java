package com.crm.operis_app.model.NonConformite;

import com.crm.operis_app.model.GRH.Personal;
import com.crm.operis_app.model.reclamation.ListeReclamation;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "VALIDATION_NON_CONFORMITE")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ValidationNonConformite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VALIDATION_NON_CONFORMITE_ID")
    private Long id;
    @Column(name = "TRAITEMENT")
    private String traitement;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "DATE_VALIDATION")
    private Date dateValidation;

    @Column(name = "ETAT_VALIDATION")
    private Boolean etatValidation = false;

    //-----------------Listenc-------------------------//

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

    public String getTraitement() {
        return traitement;
    }
    public void setTraitement(String traitement) {
        this.traitement = traitement;
    }

    public Boolean getEtatValidation() {
        return etatValidation;
    }

    public void setEtatValidation(Boolean etatValidation) {
        this.etatValidation = etatValidation;
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

    public Date getDateValidation() {
        return dateValidation;
    }

    public void setDateValidation(Date dateValidation) {
        this.dateValidation = dateValidation;
    }
}
