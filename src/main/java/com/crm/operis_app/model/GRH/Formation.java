package com.crm.operis_app.model.GRH;

import com.crm.operis_app.model.files.FileModel;
import com.fasterxml.jackson.annotation.JsonFormat;
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
@Table(name = "FORMATION")
public class Formation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FORMATION_ID")
    private Long id;

    @Column(name = "CODE_FORMATION")
    private String codeFormation ;

    @Column(name = "THEME_FORMATION")
    private String themeFormation;

    @Column(name = "OBJECTIFE")
    private String objectife ;

    @Column(name = "TYPE_FORMATION")
    private String typeFormation ;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name="DATE_PLANNED")
    private Date plannedDate;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name="PLANNED_END_DATE")
    private Date plannedEndDate;


    @Column(name = "DURATION")
    private String duration ;

    @Column(name = "LOCATION")
    private String location ;

    @Column(name = "PROVIDER")
    private String provider ;

    @Column(name = "COST")
    private String cost ;

    @Column(name = "REFUND")
    private String refund ;

    @Column(name = "VALIDATOR")
    private String validator ;

    @Column(name = "IS_VALID")
    private Boolean isValid  ;

    @Column(name = "REALIZATION_STATUS")
    private String realizationStatus ;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name="REALIZATION_DATE")
    private Date realizationDate;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name="END_REALIZATION_DATE")
    private Date endRealizationDate;

    @Column(name = "COMMENT")
    private String comment ;

    @Column(name = "PROVIDER_REFERENCE")
    private String providerReference ;

    @Column(name = "PROVIDER_SCORE")
    private String providerScore ;

    @Column(name = "PROVIDER_REPORT")
    private String providerReport ;

    @Column(name = "INSTRUCTOR_SCORE")
    private String instructorScore ;

    @Column(name = "INSTRUCTOR_REPORT")
    private String instructorReport ;

    @Column(name = "ACTIVE")
    private Boolean active = true;
    //-------------file-------------------------------//
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "Formation_FILE", joinColumns = { @JoinColumn(name = "Formation_ID") }, inverseJoinColumns = {
            @JoinColumn(name = "FILE_ID") })
    private Set<FileModel> fileModels;

//----------------------------participant----------------------------------------------//
    @OrderBy("id ASC")
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "FORMATION_PARTICIPANT", joinColumns = { @JoinColumn(name = "FORMATION_ID") }, inverseJoinColumns = {
    @JoinColumn(name = "PARTICIPANT_ID") })
    private Set<Participant> participant;

//---------------------------Formation_Requirement----------------------------------//

@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
@JoinTable(name = "Formation_Requirement",
        joinColumns = { @JoinColumn(name = "Formation_ID") }, inverseJoinColumns = {
        @JoinColumn(name = "Post_ID") })
private Set<Post> post;
//----------------------personnel----------------------------//

    @OrderBy("id ASC")
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "PERSONNEL_FORMATION", joinColumns = { @JoinColumn(name = "FORMATION_ID") }, inverseJoinColumns = {
    @JoinColumn(name = "PERSONAL_ID") })
    private Set<Personal> personnels;

//----------------------------------------------//

    public Set<FileModel> getFileModels() {
        return fileModels;
    }

    public void setFileModels(Set<FileModel> fileModels) {
        this.fileModels = fileModels;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeFormation() {
        return codeFormation;
    }

    public void setCodeFormation(String codeFormation) {
        this.codeFormation = codeFormation;
    }

    public String getThemeFormation() {
        return themeFormation;
    }

    public void setThemeFormation(String themeFormation) {
        this.themeFormation = themeFormation;
    }

    public String getTypeFormation() {
        return typeFormation;
    }

    public void setTypeFormation(String typeFormation) {
        this.typeFormation = typeFormation;
    }

    public String getObjectife() {
        return objectife;
    }

    public void setObjectife(String objectife) {
        this.objectife = objectife;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getRefund() {
        return refund;
    }

    public void setRefund(String refund) {
        this.refund = refund;
    }

    public String getValidator() {
        return validator;
    }

    public void setValidator(String validator) {
        this.validator = validator;
    }

    public Boolean getValid() {
        return isValid;
    }

    public void setValid(Boolean valid) {
        isValid = valid;
    }

    public String getRealizationStatus() {
        return realizationStatus;
    }

    public void setRealizationStatus(String realizationStatus) {
        this.realizationStatus = realizationStatus;
    }

    public Date getRealizationDate() {
        return realizationDate;
    }

    public void setRealizationDate(Date realizationDate) {
        this.realizationDate = realizationDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getProviderReference() {
        return providerReference;
    }

    public void setProviderReference(String providerReference) {
        this.providerReference = providerReference;
    }

    public String getProviderScore() {
        return providerScore;
    }

    public void setProviderScore(String providerScore) {
        this.providerScore = providerScore;
    }

    public String getProviderReport() {
        return providerReport;
    }

    public void setProviderReport(String providerReport) {
        this.providerReport = providerReport;
    }

    public String getInstructorScore() {
        return instructorScore;
    }

    public void setInstructorScore(String instructorScore) {
        this.instructorScore = instructorScore;
    }

    public String getInstructorReport() {
        return instructorReport;
    }

    public void setInstructorReport(String instructorReport) {
        this.instructorReport = instructorReport;
    }

    public Set<Participant> getParticipant() {
        return participant;
    }

    public void setParticipant(Set<Participant> participant) {
        this.participant = participant;
    }

    public Set<Post> getPost() {
        return post;
    }

    public void setPost(Set<Post> post) {
        this.post = post;
    }

    public Set<Personal> getPersonnels() {
        return personnels;
    }

    public void setPersonnels(Set<Personal> personnels) {
        this.personnels = personnels;
    }

    public Date getEndRealizationDate() {
        return endRealizationDate;
    }

    public void setEndRealizationDate(Date endRealizationDate) {
        this.endRealizationDate = endRealizationDate;
    }

    public Date getPlannedDate() {
        return plannedDate;
    }

    public void setPlannedDate(Date plannedDate) {
        this.plannedDate = plannedDate;
    }

    public Date getPlannedEndDate() {
        return plannedEndDate;
    }

    public void setPlannedEndDate(Date plannedEndDate) {
        this.plannedEndDate = plannedEndDate;
    }
}
