package com.crm.operis_app.model.audit;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "Auditeur")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Auditeur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AUDITEUR_ID")
    private Long id;
    @Column(name = "NAME_AUDITEUR")
    private String nameAuditeur;
    @Column(name = "POSTE_AUDITEUR")
    private String posteAuditeur;
    //-----------------ListeAudit-------------------------//

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "listeAuditId")
    private ListeAudit listeAudit;

    //-------------------------------------------------------------//


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameAuditeur() {
        return nameAuditeur;
    }

    public void setNameAuditeur(String nameAuditeur) {
        this.nameAuditeur = nameAuditeur;
    }

    public String getPosteAuditeur() {
        return posteAuditeur;
    }

    public void setPosteAuditeur(String posteAuditeur) {
        this.posteAuditeur = posteAuditeur;
    }
    @JsonIgnore
    public ListeAudit getListeAudit() {
        return listeAudit;
    }
    @JsonIgnore
    public void setListeAudit(ListeAudit listeAudit) {
        this.listeAudit = listeAudit;
    }
}
