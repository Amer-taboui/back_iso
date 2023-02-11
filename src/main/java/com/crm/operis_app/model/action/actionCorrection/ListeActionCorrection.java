package com.crm.operis_app.model.action.actionCorrection;

import com.crm.operis_app.model.Utils.Site;
import com.crm.operis_app.model.action.utils.ActionCost;
import com.crm.operis_app.model.files.FileModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "LISTE_ACTION_CORRECTION")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class ListeActionCorrection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LIST_ACTION_ID")
    private Long id;
    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "DATE_ACTION")
    private Date dateAction;

    @Column(name = "TYPE_ACTION")
    private String typeAction;

    @Column(name = "PRIORITY")
    private String priority;

    @Column(name = "DEMANDEUR")
    private String demandeur;

    @Column(name = "DESIGATION")
    private String designation;


    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "CODE")
    private String code;


    @Column(name = "ANALYSE_CAUSE")
    private String analyseCause;


    @Column(name = "SOURCE")
    private String source;
    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name="DATE_CLOTURE_PLANIFIE")
    private Date dateCloturePlanifie;
    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name="DATE_CLOTURE")
    private Date dateCloture;

    @Column(name="ACTIVE")
    private Boolean active=true;

    @Column(name="CLOTURE",nullable = true)
    private boolean cloture = false;


    //-----------------validationAction-------------------------//
    @OrderBy("id ASC")
    @OneToMany(mappedBy = "listeActionCorrection", cascade = CascadeType.ALL, fetch = FetchType.LAZY )
    private Set<ValidationAction> validationAction;

    //-----------------PlanAction-------------------------//
    @OrderBy("id ASC")
    @OneToMany(mappedBy = "listeActionCorrection", cascade = CascadeType.ALL, fetch = FetchType.LAZY )
    private Set<PlanAction> PlanAction;


    //-----------------EtatAction-------------------------//
    @OrderBy("id ASC")
    @OneToMany(mappedBy = "listeActionCorrection", cascade = CascadeType.ALL, fetch = FetchType.LAZY )
    private Set<EtatAction> EtatAction;


    //----------------------------ACTION-CORRECTION-COST--------------------------------------//
    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "ACTION_COST_ID" ,nullable = true)
    private ActionCost actionCorrectionCost;

    //-------------file-------------------------------//
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "Action_FILE", joinColumns = { @JoinColumn(name = "LIST_ACTION_ID") }, inverseJoinColumns = {
            @JoinColumn(name = "FILE_ID") })
    private Set<FileModel> fileModels;


    //--------------------------------------------------------------------------------------//
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateAction() {
        return dateAction;
    }

    public void setDateAction(Date dateAction) {
        this.dateAction = dateAction;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getDemandeur() {
        return demandeur;
    }

    public void setDemandeur(String demandeur) {
        this.demandeur = demandeur;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAnalyseCause() {
        return analyseCause;
    }

    public void setAnalyseCause(String analyseCause) {
        this.analyseCause = analyseCause;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Date getDateCloturePlanifie() {
        return dateCloturePlanifie;
    }

    public void setDateCloturePlanifie(Date dateCloturePlanifie) {
        this.dateCloturePlanifie = dateCloturePlanifie;
    }

    public Date getDateCloture() {
        return dateCloture;
    }

    public void setDateCloture(Date dateCloture) {
        this.dateCloture = dateCloture;
    }

    public String getTypeAction() {
        return typeAction;
    }

    public void setTypeAction(String typeAction) {
        this.typeAction = typeAction;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public boolean isCloture() {
        return cloture;
    }

    public void setCloture(boolean cloture) {
        this.cloture = cloture;
    }


    public ActionCost getActionCorrectionCost() {
        return actionCorrectionCost;
    }

    public void setActionCorrectionCost(ActionCost actionCorrectionCost) {
        this.actionCorrectionCost = actionCorrectionCost;
    }

    public Set<ValidationAction> getValidationAction() {
        return validationAction;
    }

    public void setValidationAction(Set<ValidationAction> validationAction) {
        this.validationAction = validationAction;
    }

    public Set<com.crm.operis_app.model.action.actionCorrection.PlanAction> getPlanAction() {
        return PlanAction;
    }

    public void setPlanAction(Set<com.crm.operis_app.model.action.actionCorrection.PlanAction> planAction) {
        PlanAction = planAction;
    }

    public Set<com.crm.operis_app.model.action.actionCorrection.EtatAction> getEtatAction() {
        return EtatAction;
    }

    public void setEtatAction(Set<com.crm.operis_app.model.action.actionCorrection.EtatAction> etatAction) {
        EtatAction = etatAction;
    }

    public Set<FileModel> getFileModels() {
        return fileModels;
    }

    public void setFileModels(Set<FileModel> fileModels) {
        this.fileModels = fileModels;
    }
}
