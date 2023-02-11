package com.crm.operis_app.model.GRH;
import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "PLANNING_GANNT_FORMATION")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class PlanningFormation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long pID;

    int position;

    String pName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    Date pStart;

    @JsonFormat(pattern = "yyyy-MM-dd")
    Date pEnd;

    @JsonFormat(pattern = "yyyy-MM-dd")
    Date realEndDate;

    String pClass;

    int pComp;

    int pGroup;

    int pParent;

    int pOpen;

    String pNotes;

    Boolean taskIsParent;



@ManyToOne(fetch = FetchType.LAZY)
@JsonIgnore
@JoinColumn(name = "FORMATION_ID", nullable = false)
private Formation formation;

/*
    public PlanningfORMATION(String pName, String pClass, int pComp, int pParent, int pOpen,int pGroup) {
        this.pName = pName;
        this.pClass = pClass;
        this.pComp = pComp;
        this.pParent = pParent;
        this.pOpen = pOpen;
        this.pGroup = pGroup;

    }*/

    public PlanningFormation(int position, String pName, Date pStart, Date pEnd, String pClass, int pComp, Date realEndDate, int pGroup, int pParent, int pOpen, String pNotes, boolean taskIsParent, Formation formation) {
        this.position = position;
        this.pName = pName;
        this.pStart = pStart;
        this.pEnd = pEnd;
        this.pClass = pClass;
        this.realEndDate = realEndDate;
        this.pComp = pComp;
        this.pGroup = pGroup;
        this.pParent = pParent;
        this.pOpen = pOpen;
        this.pNotes = pNotes;
        this.taskIsParent = taskIsParent;
        this.formation = formation;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Long getpID() {
        return pID;
    }

    public void setpID(Long pID) {
        this.pID = pID;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public Date getpStart() {
        return pStart;
    }

    public void setpStart(Date pStart) {
        this.pStart = pStart;
    }

    public Date getpEnd() {
        return pEnd;
    }

    public void setpEnd(Date pEnd) {
        this.pEnd = pEnd;
    }

    public String getpClass() {
        return pClass;
    }

    public void setpClass(String pClass) {
        this.pClass = pClass;
    }

    public int getpComp() {
        return pComp;
    }

    public void setpComp(int pComp) {
        this.pComp = pComp;
    }

    public int getpParent() {
        return pParent;
    }

    public void setpParent(int pParent) {
        this.pParent = pParent;
    }

    public int getpOpen() {
        return pOpen;
    }

    public void setpOpen(int pOpen) {
        this.pOpen = pOpen;
    }

    public String getpNotes() {
        return pNotes;
    }

    public void setpNotes(String pNotes) {
        this.pNotes = pNotes;
    }

    public int getpGroup() {
        return pGroup;
    }

    public void setpGroup(int pGroup) {
        this.pGroup = pGroup;
    }



    public Boolean getTaskIsParent() {
        return taskIsParent;
    }

    public void setTaskIsParent(Boolean taskIsParent) {
        this.taskIsParent = taskIsParent;
    }


    public Formation getFormation() {
        return formation;
    }

    public void setFormation(Formation formation) {
        this.formation = formation;
    }

    public Date getRealEndDate() {
        return realEndDate;
    }

    public void setRealEndDate(Date realEndDate) {
        this.realEndDate = realEndDate;
    }



}
