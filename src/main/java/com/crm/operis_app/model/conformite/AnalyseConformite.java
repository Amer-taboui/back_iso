package com.crm.operis_app.model.conformite;

import com.crm.operis_app.model.GRH.Personal;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "ANALYSE_CONFORMITE")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AnalyseConformite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ANALYSE_CONFORMITE_ID")
    private Long id;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "DATE_CREATION")
    private Date dateCreation;

    @Column(name = "RESULTAT_ANALYSE")
    private String resultatAnalyse;

    //-----------------ListeReclamation-------------------------//

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "listeConformiteId")
    private ListeConformite listeConformite;

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

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getResultatAnalyse() {
        return resultatAnalyse;
    }

    public void setResultatAnalyse(String resultatAnalyse) {
        this.resultatAnalyse = resultatAnalyse;
    }
    @JsonIgnore
    public ListeConformite getListeConformite() {
        return listeConformite;
    }
    @JsonIgnore
    public void setListeConformite(ListeConformite listeConformite) {
        this.listeConformite = listeConformite;
    }

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }



}


