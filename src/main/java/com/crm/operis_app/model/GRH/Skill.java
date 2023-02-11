package com.crm.operis_app.model.GRH;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "SKILL")
public class Skill implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SKILL_ID")
    private Long id;

    @Column(name = "CODE")
    private String code ;

    @Column(name = "SKILL_NAME")
    private String skillName ;

    @Column(name = "CATEGORY")
    private String category ;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name="CREATION_DATE")
    private Date creationDate;

    @Column(name = "DESCRIPTION")
    private String description ;

    @Column(name = "COMMENT")
    private String comment ;

    @Column(name = "ACTIVE")
    private Boolean active = true;

//-----------------------SkillEVALUATIONMETHOD----------------------------//
@OneToMany(mappedBy = "skill", fetch = FetchType.LAZY)
@JsonIgnore
private Set<SkillEvaluationMethod> skillEvaluationMethod;
//--------------------------------------------------------------------//


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }



    public Set<SkillEvaluationMethod> getSkillEvaluationMethod() {
        return skillEvaluationMethod;
    }

    public void setSkillEvaluationMethod(Set<SkillEvaluationMethod> skillEvaluationMethod) {
        this.skillEvaluationMethod = skillEvaluationMethod;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
