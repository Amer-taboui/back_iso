package com.crm.operis_app.model.reclamation;

import com.crm.operis_app.model.GRH.Personal;
import com.crm.operis_app.model.audit.ListeAudit;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "ANALYSE_RECLAMATION")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AnalyseReclamation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ANALYSE_RECLAMATION_ID")
    private Long id;
    @Column(name = "ANALYSE_CAUSE")
    private String analyseCause;
    @Column(name = "ETAT_ANALYSE")
    private Boolean etatAnalyse = false;

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

    public String getAnalyseCause() {
        return analyseCause;
    }

    public void setAnalyseCause(String analyseCause) {
        this.analyseCause = analyseCause;
    }

    public Boolean getEtatAnalyse() {
        return etatAnalyse;
    }

    public void setEtatAnalyse(Boolean etatAnalyse) {
        this.etatAnalyse = etatAnalyse;
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
