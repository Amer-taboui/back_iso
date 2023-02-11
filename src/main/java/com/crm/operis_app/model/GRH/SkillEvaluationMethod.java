package com.crm.operis_app.model.GRH;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "SKILL_EVALUATION_METHOD")
public class SkillEvaluationMethod implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SKILL_EVALUATION_METHOD_ID")
    private Long id;

    @Column(name = "lEVEL")
    private String level ;

    @Column(name = "NOTE")
    private int note ;

    @Column(name = "METHODE_NAME")
    private String methodName ;

    @Column(name = "COMMENT")
    private String comment ;
    //-----------------------------Skill-------------------------//
    @OrderBy("id ASC")
    @ManyToOne
    @JoinColumn(name = "skill_Id")
    private Skill skill;


    //------------------------personal-----------------------------------//
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personalId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Personal personal;



    //------------------------Post-----------------------------------//
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Post post;

    //-----------------------------------------------//


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    @JsonIgnore

    public Personal getPersonal() {
        return personal;
    }
    @JsonIgnore

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }
    @JsonIgnore

    public Post getPost() {
        return post;
    }
    @JsonIgnore

    public void setPost(Post post) {
        this.post = post;
    }
}
