package com.crm.operis_app.model.action.actionCorrection;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "DOC_ACTION")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class DocAction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DOC_ACTION_ID")
     Long id;

    @Column(name = "NOM_DOCUMENT")
    private String nomDocument;

    @Column(name = "DESIGATION")
    private Date dateCreation;

    @Column(name = "OBJET")
    private String objet;

    @Column(name = "COMMENTAIRE_DOC")
    private String commentaireDoc;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomDocument() {
        return nomDocument;
    }

    public void setNomDocument(String nomDocument) {
        this.nomDocument = nomDocument;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public String getCommentaireDoc() {
        return commentaireDoc;
    }

    public void setCommentaireDoc(String commentaireDoc) {
        this.commentaireDoc = commentaireDoc;
    }
}

