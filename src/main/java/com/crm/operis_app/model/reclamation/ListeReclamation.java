package com.crm.operis_app.model.reclamation;

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
@Table(name = "LISTE_RECLAMATION")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ListeReclamation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LIST_RECLAMATION_ID")
    private Long id;

    @Column(name = "TYPE_RECLAMATION")
    private String typeReclamation;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "DATE_CREATION")
    private Date dateCreation;

    @Column(name = "RECLAMANT")
    private String reclamant;

    @Column(name = "CONTACT")
    private String contact;

    @Column(name = "TELEPHONE")
    private String telephone;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "ADRESSE")
    private String adresse;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "PRIORITE")
    private String priorite;

    @Column(name = "COMMENTAIRE")
    private String commentaire;

    @Column(name = "TAUX_TRAITEMENT")
    private String tauxTraitement;

    @Column(name = "COMMENTAIRE_TRAITEMENT")
    private String commentaireTraitement;

    @Column(name = "ACTIVE")
    private Boolean active = true;

    //----------------------------Reclamation-COST--------------------------------------//
    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "RECLAMATION_COST_ID" ,nullable = true)
    private ReclamationCost reclamationCost;

    //-----------------analyseReclamation-------------------------//
    @OrderBy("id ASC")
    @OneToMany(mappedBy = "listeReclamation", cascade = CascadeType.ALL, fetch = FetchType.LAZY )
    private Set<AnalyseReclamation> analyseReclamation;
    //-----------------creationReclamation-------------------------//
    @OrderBy("id ASC")
    @OneToMany(mappedBy = "listeReclamation", cascade = CascadeType.ALL, fetch = FetchType.LAZY )
    private Set<CreationReclamation> creationReclamation;

    //-----------------clotureReclamation-------------------------//

    @OrderBy("id ASC")
    @OneToMany(mappedBy = "listeReclamation", cascade = CascadeType.ALL, fetch = FetchType.LAZY )
    private Set<ClotureReclamation> clotureReclamation;


    //-------------file-------------------------------//
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "Reclamation_FILE", joinColumns = { @JoinColumn(name = "Reclamation_ID") }, inverseJoinColumns = {
            @JoinColumn(name = "FILE_ID") })
    private Set<FileModel> fileModels;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeReclamation() {
        return typeReclamation;
    }

    public void setTypeReclamation(String typeReclamation) {
        this.typeReclamation = typeReclamation;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getReclamant() {
        return reclamant;
    }

    public void setReclamant(String reclamant) {
        this.reclamant = reclamant;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriorite() {
        return priorite;
    }

    public void setPriorite(String priorite) {
        this.priorite = priorite;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getTauxTraitement() {
        return tauxTraitement;
    }

    public void setTauxTraitement(String tauxTraitement) {
        this.tauxTraitement = tauxTraitement;
    }

    public String getCommentaireTraitement() {
        return commentaireTraitement;
    }

    public void setCommentaireTraitement(String commentaireTraitement) {
        this.commentaireTraitement = commentaireTraitement;
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

    public Set<AnalyseReclamation> getAnalyseReclamation() {
        return analyseReclamation;
    }

    public void setAnalyseReclamation(Set<AnalyseReclamation> analyseReclamation) {
        this.analyseReclamation = analyseReclamation;
    }

    public Set<CreationReclamation> getCreationReclamation() {
        return creationReclamation;
    }

    public void setCreationReclamation(Set<CreationReclamation> creationReclamation) {
        this.creationReclamation = creationReclamation;
    }

    public Set<ClotureReclamation> getClotureReclamation() {
        return clotureReclamation;
    }

    public void setClotureReclamation(Set<ClotureReclamation> clotureReclamation) {
        this.clotureReclamation = clotureReclamation;
    }

    public ReclamationCost getReclamationCost() {
        return reclamationCost;
    }

    public void setReclamationCost(ReclamationCost reclamationCost) {
        this.reclamationCost = reclamationCost;
    }
}
