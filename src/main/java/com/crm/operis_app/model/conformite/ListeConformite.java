package com.crm.operis_app.model.conformite;

import com.crm.operis_app.model.GRH.Personal;

import com.crm.operis_app.model.NonConformite.ValidationNonConformite;
import com.crm.operis_app.model.files.FileModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Data
@Table(name = "LISTE_CONFORMITE")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ListeConformite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LISTE_CONFORMITE_ID")
    private Long id;


    @Column(name = "NATURE_EXIGENCE")
    private String natureExigence;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "DATE_APPLICATION")
    private Date dateApplication;

    @Column(name = "SYSTEME")
    private String systeme;

    @Column(name = "DOMAINE")
    private String domaine;

    @Column(name = "SOUS_DOMAINE")
    private String sousDomaine;

    @Column(name = "APPLICABILITE")
    private String applicabilite;

    @Column(name = "TAUX_TRAITEMENT")
    private String tauxTraitement;

    @Column(name = "COMMENTAIRE")
    private String commentaire;

    @Column(name = "CONFORMITE ")
    private String conformite ;

    @Column(name = "SYNTHESE_EXIGENCE")
    private String syntheseExigence ;

    @Column(name = "ACTIVE")
    private Boolean active = true;

    @OrderBy("id ASC")
    @ManyToOne
    @JoinColumn(name = "RESPONSABLE_VEILLE_ID")
    private Personal responsableVeille;




    //-----------------ValidationNonConformite-------------------------//
    @OrderBy("id ASC")
    @OneToMany(mappedBy = "listeConformite", cascade = CascadeType.ALL, fetch = FetchType.LAZY )
    private Set<AnalyseConformite> analyseConformite;


    //-------------file-------------------------------//
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "CONFORMITE_FILE", joinColumns = { @JoinColumn(name = "LISTE_CONFORMITE_ID") }, inverseJoinColumns = {
            @JoinColumn(name = "FILE_ID") })
    private Set<FileModel> fileModels;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNatureExigence() {
        return natureExigence;
    }

    public void setNatureExigence(String natureExigence) {
        this.natureExigence = natureExigence;
    }



    public String getSysteme() {
        return systeme;
    }

    public void setSysteme(String systeme) {
        this.systeme = systeme;
    }

    public String getSousDomaine() {
        return sousDomaine;
    }

    public void setSousDomaine(String sousDomaine) {
        this.sousDomaine = sousDomaine;
    }

    public String getApplicabilite() {
        return applicabilite;
    }

    public void setApplicabilite(String applicabilite) {
        this.applicabilite = applicabilite;
    }

    public String getTauxTraitement() {
        return tauxTraitement;
    }

    public void setTauxTraitement(String tauxTraitement) {
        this.tauxTraitement = tauxTraitement;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getConformite() {
        return conformite;
    }

    public void setConformite(String conformite) {
        this.conformite = conformite;
    }

    public String getSyntheseExigence() {
        return syntheseExigence;
    }

    public void setSyntheseExigence(String syntheseExigence) {
        this.syntheseExigence = syntheseExigence;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Personal getResponsableVeille() {
        return responsableVeille;
    }

    public void setResponsableVeille(Personal responsableVeille) {
        this.responsableVeille = responsableVeille;
    }

    public Set<AnalyseConformite> getAnalyseConformite() {
        return analyseConformite;
    }

    public void setAnalyseConformite(Set<AnalyseConformite> analyseConformite) {
        this.analyseConformite = analyseConformite;
    }

    public Set<FileModel> getFileModels() {
        return fileModels;
    }

    public void setFileModels(Set<FileModel> fileModels) {
        this.fileModels = fileModels;
    }

    public Date getDateApplication() {
        return dateApplication;
    }

    public void setDateApplication(Date dateApplication) {
        this.dateApplication = dateApplication;
    }

    public String getDomaine() {
        return domaine;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }
}
