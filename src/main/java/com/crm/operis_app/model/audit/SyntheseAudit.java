package com.crm.operis_app.model.audit;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Synthese_Audit")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SyntheseAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CONSTAT_AUDIT_ID")
    private Long id;
    @Column(name = "COMMENTAIRE")
    private String commentaire;
    @Column(name = "FORCES")
    private String forces;
    @Column(name = "FAIBLESSES")
    private String faiblesses;
    @Column(name = "OBSERVATION")
    private String observations;

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

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getForces() {
        return forces;
    }

    public void setForces(String forces) {
        this.forces = forces;
    }

    public String getFaiblesses() {
        return faiblesses;
    }

    public void setFaiblesses(String faiblesses) {
        this.faiblesses = faiblesses;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
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
