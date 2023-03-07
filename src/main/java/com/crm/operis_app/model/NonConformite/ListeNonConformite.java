package com.crm.operis_app.model.NonConformite;
import com.crm.operis_app.model.GRH.Personal;
import com.crm.operis_app.model.NonConformite.Utils.CategorieNonConformite;
import com.crm.operis_app.model.NonConformite.Utils.GraviteNonConformite;
import com.crm.operis_app.model.NonConformite.Utils.OrigineNonConformite;
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
@Table(name = "LISTE_NON_CONFORMITE")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ListeNonConformite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LISTE_NON_CONFORMITE_ID")
    private Long id;


    @Column(name = "DESIGNATION_NC")
    private String designationNc;

    @Column(name = "RESPONSABLE_ANALYSE")
    private String responsableAnalyse;

    @Column(name = "ANALYSE_RESULTAT")
    private String analyseResultat;

    @Column(name = "ETAT_ANALYSE")
    private Boolean etatAnalyse = true;

    @Column(name = "TAUX_TRAITEMENT")
    private String tauxTraitement;

    @Column(name = "COMMENTAIRE")
    private String commentaire;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "DATE_CREATION")
    private Date dateCreation;

    @Column(name = "ACTIVE")
    private Boolean active = true;

    @OrderBy("id ASC")
    @ManyToOne
    @JoinColumn(name = "RESPONSABLE_DECOUVERTE_ID")
    private Personal responsableDecouverte;


    //---------------------------Categorie----------------------------//
    @ManyToMany
    @JoinTable(name = "NON_CONFORMITE_CATEGORIE", joinColumns = {@JoinColumn(name = "LISTE_NON_CONFORMITE_ID")}, inverseJoinColumns = {
            @JoinColumn(name = "CATEGORIE_ID")})
    private Set<CategorieNonConformite> categorieNonConformite;

    //---------------------------Origine----------------------------//
    @ManyToMany
    @JoinTable(name = "NON_CONFORMITE_ORIGINE", joinColumns = {@JoinColumn(name = "LISTE_NON_CONFORMITE_ID")}, inverseJoinColumns = {
            @JoinColumn(name = "ORIGINE_ID")})
    private Set<OrigineNonConformite> origineNonConformite;


    //---------------------------gravite----------------------------//
    @ManyToMany
    @JoinTable(name = "NON_CONFORMITE_GRAVITE", joinColumns = {@JoinColumn(name = "LISTE_NON_CONFORMITE_ID")}, inverseJoinColumns = {
            @JoinColumn(name = "GRAVITE_ID")})
    private Set<GraviteNonConformite> graviteNonConformite;

    //-----------------ValidationNonConformite-------------------------//
    @OrderBy("id ASC")
    @OneToMany(mappedBy = "listeNonConformite", cascade = CascadeType.ALL, fetch = FetchType.LAZY )
    private Set<ValidationNonConformite> validationNonConformite;

    //-----------------ClotureNonConformite-------------------------//
    @OrderBy("id ASC")
    @OneToMany(mappedBy = "listeNonConformite", cascade = CascadeType.ALL, fetch = FetchType.LAZY )
    private Set<ClotureNonConformite> clotureNonConformite;

    //-------------file-------------------------------//
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "NON_CONFORMITE_FILE", joinColumns = { @JoinColumn(name = "LISTE_NON_CONFORMITE_ID") }, inverseJoinColumns = {
            @JoinColumn(name = "FILE_ID") })
    private Set<FileModel> fileModels;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Personal getResponsableDecouverte() {
        return responsableDecouverte;
    }

    public void setResponsableDecouverte(Personal responsableDecouverte) {
        this.responsableDecouverte = responsableDecouverte;
    }

    public String getDesignationNc() {
        return designationNc;
    }

    public void setDesignationNc(String designationNc) {
        this.designationNc = designationNc;
    }

    public String getResponsableAnalyse() {
        return responsableAnalyse;
    }

    public void setResponsableAnalyse(String responsableAnalyse) {
        this.responsableAnalyse = responsableAnalyse;
    }

    public String getAnalyseResultat() {
        return analyseResultat;
    }

    public void setAnalyseResultat(String analyseResultat) {
        this.analyseResultat = analyseResultat;
    }

    public Boolean getEtatAnalyse() {
        return etatAnalyse;
    }

    public void setEtatAnalyse(Boolean etatAnalyse) {
        this.etatAnalyse = etatAnalyse;
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

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Set<FileModel> getFileModels() {
        return fileModels;
    }

    public void setFileModels(Set<FileModel> fileModels) {
        this.fileModels = fileModels;
    }

    public Set<ValidationNonConformite> getValidationNonConformite() {
        return validationNonConformite;
    }

    public void setValidationNonConformite(Set<ValidationNonConformite> validationNonConformite) {
        this.validationNonConformite = validationNonConformite;
    }

    public Set<ClotureNonConformite> getClotureNonConformite() {
        return clotureNonConformite;
    }

    public void setClotureNonConformite(Set<ClotureNonConformite> clotureNonConformite) {
        this.clotureNonConformite = clotureNonConformite;
    }

    public Set<CategorieNonConformite> getCategorieNonConformite() {
        return categorieNonConformite;
    }

    public void setCategorieNonConformite(Set<CategorieNonConformite> categorieNonConformite) {
        this.categorieNonConformite = categorieNonConformite;
    }

    public Set<OrigineNonConformite> getOrigineNonConformite() {
        return origineNonConformite;
    }

    public void setOrigineNonConformite(Set<OrigineNonConformite> origineNonConformite) {
        this.origineNonConformite = origineNonConformite;
    }

    public Set<GraviteNonConformite> getGraviteNonConformite() {
        return graviteNonConformite;
    }

    public void setGraviteNonConformite(Set<GraviteNonConformite> graviteNonConformite) {
        this.graviteNonConformite = graviteNonConformite;
    }
}
