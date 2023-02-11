package com.crm.operis_app.model.GRH;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "REQUIRED_SKILL")
public class requiredSkill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REQUIRED_SKILL_ID")
    private Long id;

    @Column(name = "REQUIRED_SCORE")
    private String requiredScore ;

    @Column(name = "REQUIRED_LEVEL")
    private int requiredLevel ;

    /*--------Post--------//
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_Id")
    private Post post;
    //---------Skill---------//
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "skill_Id")
    private Skill skill;
    //------------------------*/



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRequiredScore() {
        return requiredScore;
    }

    public void setRequiredScore(String requiredScore) {
        this.requiredScore = requiredScore;
    }

    public int getRequiredLevel() {
        return requiredLevel;
    }

    public void setRequiredLevel(int requiredLevel) {
        this.requiredLevel = requiredLevel;
    }


}
