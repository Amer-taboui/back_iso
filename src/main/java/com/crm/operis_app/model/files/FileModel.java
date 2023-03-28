package com.crm.operis_app.model.files;


import com.crm.operis_app.model.GRH.Formation;
import com.crm.operis_app.model.GRH.Post;
import com.crm.operis_app.model.NonConformite.ListeNonConformite;
import com.crm.operis_app.model.action.actionCorrection.ListeActionCorrection;
import com.crm.operis_app.model.audit.ListeAudit;
import com.crm.operis_app.model.conformite.ListeConformite;
import com.crm.operis_app.model.reclamation.ListeReclamation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@AllArgsConstructor
@Table(name = "file_model")
public class FileModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonView(View.FileInfo.class)
    private Long id;

    @Column(name = "name")
    @JsonView(View.FileInfo.class)
    private String name;

    @Column(name = "mimetype")
    private String mimetype;

    @Lob
    @Column(name = "pic")
    private byte[] pic;

    @ManyToMany(fetch = FetchType.LAZY,	cascade = CascadeType.ALL,mappedBy = "fileModels")
    @JsonIgnore
    private Set<Formation> formation;


    @ManyToMany(fetch = FetchType.LAZY,	cascade = CascadeType.ALL,mappedBy = "fileModels")
    @JsonIgnore
    private Set<Post> post;
    public FileModel() {
    }

    @ManyToMany(fetch = FetchType.LAZY,	cascade = CascadeType.ALL,mappedBy = "fileModels")
    @JsonIgnore
    private Set<ListeActionCorrection> listeActionCorrection;

    @ManyToMany(fetch = FetchType.LAZY,	cascade = CascadeType.ALL,mappedBy = "fileModels")
    @JsonIgnore
    private Set<ListeReclamation> listeReclamation;

    @ManyToMany(fetch = FetchType.LAZY,	cascade = CascadeType.ALL,mappedBy = "fileModels")
    @JsonIgnore
    private Set<ListeNonConformite> listeNonConformite;

    @ManyToMany(fetch = FetchType.LAZY,	cascade = CascadeType.ALL,mappedBy = "fileModels")
    @JsonIgnore
    private Set<ListeConformite> listeConformite;

    @ManyToMany(fetch = FetchType.LAZY,	cascade = CascadeType.ALL,mappedBy = "fileModels")
    @JsonIgnore
    private Set<ListeAudit> listeAudit;

    public FileModel(String name, String mimetype, byte[] pic) {
        this.name = name;
        this.mimetype = mimetype;
        this.pic = pic;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMimetype() {
        return this.mimetype;
    }

    public void setMimetype(String mimetype) {
        this.mimetype = mimetype;
    }

    public byte[] getPic() {
        return this.pic;
    }

    public void setPic(byte[] pic) {
        this.pic = pic;
    }

    public Set<Formation> getFormation() {
        return formation;
    }

    public void setFormation(Set<Formation> formation) {
        this.formation = formation;
    }

    public Set<Post> getPost() {
        return post;
    }

    public void setPost(Set<Post> post) {
        this.post = post;

    }

    public Set<ListeActionCorrection> getListeActionCorrection() {
        return listeActionCorrection;
    }

    public void setListeActionCorrection(Set<ListeActionCorrection> listeActionCorrection) {
        this.listeActionCorrection = listeActionCorrection;
    }

    public Set<ListeReclamation> getListeReclamation() {
        return listeReclamation;
    }

    public void setListeReclamation(Set<ListeReclamation> listeReclamation) {
        this.listeReclamation = listeReclamation;
    }

    public Set<ListeNonConformite> getListeNonConformite() {
        return listeNonConformite;
    }

    public void setListeNonConformite(Set<ListeNonConformite> listeNonConformite) {
        this.listeNonConformite = listeNonConformite;
    }

    public Set<ListeConformite> getListeConformite() {
        return listeConformite;
    }

    public void setListeConformite(Set<ListeConformite> listeConformite) {
        this.listeConformite = listeConformite;
    }

    public Set<ListeAudit> getListeAudit() {
        return listeAudit;
    }

    public void setListeAudit(Set<ListeAudit> listeAudit) {
        this.listeAudit = listeAudit;
    }
}