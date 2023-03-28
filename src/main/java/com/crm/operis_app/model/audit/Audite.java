package com.crm.operis_app.model.audit;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "Audite")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Audite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AUDITE_ID")
    private Long id;
    @Column(name = "NAME_AUDITE")
    private String nameAudite;
    @Column(name = "POSTE_AUDITE")
    private String posteAudite;

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

    public String getNameAudite() {
        return nameAudite;
    }

    public void setNameAudite(String nameAudite) {
        this.nameAudite = nameAudite;
    }

    public String getPosteAudite() {
        return posteAudite;
    }

    public void setPosteAudite(String posteAudite) {
        this.posteAudite = posteAudite;
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
