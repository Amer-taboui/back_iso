package com.crm.operis_app.model.GRH;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "PARTICIPANT")
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PARTICIPANT_ID")
    private Long id;

    @Column(name = "IS_PRESENT")
    private Boolean isPresent = true;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "EXPECTED_HOT_EVALUATION_DATE")
    private String expectedHotEvaluationDate ;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "REAL_HOT_EVALUATION_DATE")
    private String realHotEvaluationDate ;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "EXPECTED_EFFECTIVENSS_EVALTION_DATE")
    private String expectedEffectivenessEvaluationDate ;

    @Column(name = "EVALUATION_RESPONSIBLE")
    private String evaluationResponsible ;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "REAL_EFFECTIVENESS_EVALUATION_DATE")
    private String realEffectivenessEvaluationDate ;

    @Column(name = "EFFICIENCY_SCORE")
    private String efficiencyScore ;

    @Column(name = "EFFICIENY_REPORT")
    private String efficiencyReport ;

    @Column(name = "IS_EFFICIENT")
    private Boolean isEfficient = true;

//---------------------------EvaluationCriteria--------------------------------//
@OrderBy("id ASC")
    @OneToMany(mappedBy = "participant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<EvaluationCriteria> evaluationCriteria;
//-------------------------------Personal--------------------------------------//

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personal_Id")
    private Personal personal;
//-----------------------------------------------------------------------------//


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getPresent() {
        return isPresent;
    }

    public void setPresent(Boolean present) {
        isPresent = present;
    }

    public String getExpectedHotEvaluationDate() {
        return expectedHotEvaluationDate;
    }

    public void setExpectedHotEvaluationDate(String expectedHotEvaluationDate) {
        this.expectedHotEvaluationDate = expectedHotEvaluationDate;
    }

    public String getRealHotEvaluationDate() {
        return realHotEvaluationDate;
    }

    public void setRealHotEvaluationDate(String realHotEvaluationDate) {
        this.realHotEvaluationDate = realHotEvaluationDate;
    }

    public String getExpectedEffectivenessEvaluationDate() {
        return expectedEffectivenessEvaluationDate;
    }

    public void setExpectedEffectivenessEvaluationDate(String expectedEffectivenessEvaluationDate) {
        this.expectedEffectivenessEvaluationDate = expectedEffectivenessEvaluationDate;
    }

    public String getEvaluationResponsible() {
        return evaluationResponsible;
    }

    public void setEvaluationResponsible(String evaluationResponsible) {
        this.evaluationResponsible = evaluationResponsible;
    }

    public String getRealEffectivenessEvaluationDate() {
        return realEffectivenessEvaluationDate;
    }

    public void setRealEffectivenessEvaluationDate(String realEffectivenessEvaluationDate) {
        this.realEffectivenessEvaluationDate = realEffectivenessEvaluationDate;
    }

    public String getEfficiencyScore() {
        return efficiencyScore;
    }

    public void setEfficiencyScore(String efficiencyScore) {
        this.efficiencyScore = efficiencyScore;
    }

    public String getEfficiencyReport() {
        return efficiencyReport;
    }

    public void setEfficiencyReport(String efficiencyReport) {
        this.efficiencyReport = efficiencyReport;
    }

    public Boolean getEfficient() {
        return isEfficient;
    }

    public void setEfficient(Boolean efficient) {
        isEfficient = efficient;
    }

    public Set<EvaluationCriteria> getEvaluationCriteria() {
        return evaluationCriteria;
    }

    public void setEvaluationCriteria(Set<EvaluationCriteria> evaluationCriteria) {
        this.evaluationCriteria = evaluationCriteria;
    }

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

}
