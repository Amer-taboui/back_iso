package com.crm.operis_app.model.GRH;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "EVALUATION_CRITERIA")
public class EvaluationCriteria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RESPONSABILITY_ID")
    private Long id;

    @Column(name = "CRITERIA")
    private String criteria ;

    @Column(name = "PONDERATION")
    private String ponderation ;

    @Column(name = "SCORE")
    private String score ;

    @Column(name = "COMMENT")
    private String comment ;

    @Column(name = "EVALUATION_MODEL")
    private String evaluationModel ;

    public Long getId() {
        return id;
    }

//------------------------participant-----------------------//
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "participant_Id")
    private Participant participant;
//----------------------------------------------------------------------//
    public void setId(Long id) {
        this.id = id;
    }

    public String getCriteria() {
        return criteria;
    }

    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }

    public String getPonderation() {
        return ponderation;
    }

    public void setPonderation(String ponderation) {
        this.ponderation = ponderation;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getEvaluationModel() {
        return evaluationModel;
    }

    public void setEvaluationModel(String evaluationModel) {
        this.evaluationModel = evaluationModel;
    }
    @JsonIgnore
    public Participant getParticipant() {
        return participant;
    }
    @JsonIgnore
    public void setParticipant(Participant participant) {
        this.participant = participant;
    }
}
