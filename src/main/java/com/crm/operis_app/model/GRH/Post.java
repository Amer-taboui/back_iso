package com.crm.operis_app.model.GRH;

import com.crm.operis_app.model.files.FileModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "POST")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POST_ID")
    private Long id;

    @Column(name = "POST_NAME")
    private String posteName ;

    @Column(name = "MISSION")
    private String mission;

    @Column(name = "DESCRIPTION")
    private String desciption ;


    @Column(name = "EXPERTISE")
    private String expertise ;

    @Column(name = "PROFESSIONAL_FAMILY")
    private String professionalFamily ;

    @Column(name = "PROFESSION")
    private String profession ;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name="CREATION_DATE")
    private Date creationDate;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name="UPDATED_DATE")
    private Date updatedDate;

    @Column(name = "COMMENT")
    private String comment ;

    @Column(name="ACTIVE")
    private Boolean active=true;


    //-------------file-------------------------------//
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "Post_FILE", joinColumns = { @JoinColumn(name = "Post_ID") }, inverseJoinColumns = {
            @JoinColumn(name = "FILE_ID") })
    private Set<FileModel> fileModels;


//----------------------personnel----------------------------//

    @OrderBy("id ASC")
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "PERSONNEL_POST", joinColumns = { @JoinColumn(name = "POST_ID") }, inverseJoinColumns = {
            @JoinColumn(name = "PERSONAL_ID") })
    private Set<Personal> personnels;


//-----------------------responsability---------------------------//

@OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
private Set<Responsability> responsability;

    /*----------------------requiredSkill----------------------------//
@OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
private Set<requiredSkill>  requiredSkill;*/

//----------------------------------skillEvaluationMethods----------------------------------//
@OrderBy("id ASC")
@OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
@OnDelete(action = OnDeleteAction.CASCADE)
private Set<SkillEvaluationMethod> skillEvaluationMethods;


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

    public String getPosteName() {
        return posteName;
    }

    public void setPosteName(String posteName) {
        this.posteName = posteName;
    }

    public String getMission() {
        return mission;
    }

    public void setMission(String mission) {
        this.mission = mission;
    }

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

    public String getExpertise() {
        return expertise;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    public String getProfessionalFamily() {
        return professionalFamily;
    }

    public void setProfessionalFamily(String professionalFamily) {
        this.professionalFamily = professionalFamily;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Set<Personal> getPersonnels() {
        return personnels;
    }

    public void setPersonnels(Set<Personal> personnels) {
        this.personnels = personnels;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Set<Responsability> getResponsability() {
        return responsability;
    }

    public void setResponsability(Set<Responsability> responsability) {
        this.responsability = responsability;
    }

    public Set<SkillEvaluationMethod> getSkillEvaluationMethods() {
        return skillEvaluationMethods;
    }

    public void setSkillEvaluationMethods(Set<SkillEvaluationMethod> skillEvaluationMethods) {
        this.skillEvaluationMethods = skillEvaluationMethods;
    }
}
