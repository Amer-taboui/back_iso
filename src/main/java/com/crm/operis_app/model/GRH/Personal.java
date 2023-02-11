package com.crm.operis_app.model.GRH;

import com.crm.operis_app.model.authUser.User;
import com.crm.operis_app.model.files.FileModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PERSONNEL")
public class Personal implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PERSONNEL_ID")
    private Long id;

    @Column(name = "NOM_PERSONNEL")
    private String nomPersonnel;

    @Column(name = "PRENOM_PERSONNEL")
    private String prenomPersonnel;

    @Column(name = "MATRICULE")
    private Long matricule;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name="BIRTH_DATE")
    private Date birthDate;

    @Column(name = "CIN")
    private Long cin;

    @Column(name = "PHONE")
    private Long phone;

    @Column(name = "ADRESS")
    private String adress;

    @Column(name = "ADRESSMAIL")
    private String adressEmail;

    @Column(name = "CITY")
    private String city;

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "COMMENT")
    private String comment;

    @Column(name = "TYPE_CONTRAT")
    private String typeContrat;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name="DATE_FIN_AFFECTATION")
    private Date dateFinAff;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name="DATE_AFFECTATION")
    private Date dateAff;

    @Column(name = "CHAMP_INTERIM")
    private String champInterim ;

    @Column(name="ACTIVE")
    private Boolean active=true;


    @OneToMany(mappedBy = "personnel", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<User> user;

    //-------------file-------------------------------//
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "Formation_FILE", joinColumns = { @JoinColumn(name = "Formation_ID") }, inverseJoinColumns = {
            @JoinColumn(name = "FILE_ID") })
    private Set<FileModel> fileModels;

    //-----------------------PERSONNAL_AVAILABLE_SKILLS----------------------------//
    @OrderBy("id ASC")
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "PERSONNAL_AVAILABLE_SKILLS", joinColumns = { @JoinColumn(name = "Personnal_ID") }, inverseJoinColumns = {@JoinColumn(name = "Skill_ID") })
    private Set<Skill> skill;
    //---------------------------participant--------------------------------//

    @OneToMany(mappedBy = "personal", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Participant> participant;

    //--------------------------------------------------------------------------//
    @OrderBy("id ASC")
    @OneToMany(mappedBy = "personal", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<SkillEvaluationMethod> skillEvaluationMethods;

    //-----------------------------//
    public Long getId() {
        return id;
    }

    public String getAdressEmail() {
        return adressEmail;
    }

    public void setAdressEmail(String adressEmail) {
        this.adressEmail = adressEmail;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomPersonnel() {
        return nomPersonnel;
    }

    public void setNomPersonnel(String nomPersonnel) {
        this.nomPersonnel = nomPersonnel;
    }

    public String getPrenomPersonnel() {
        return prenomPersonnel;
    }

    public void setPrenomPersonnel(String prenomPersonnel) {
        this.prenomPersonnel = prenomPersonnel;
    }

    public Set<User> getUser() {
        return user;
    }

    public void setUser(Set<User> user) {
        this.user = user;
    }

    public Long getMatricule() {
        return matricule;
    }

    public void setMatricule(Long matricule) {
        this.matricule = matricule;
    }

    public Date getDateAff() {
        return dateAff;
    }

    public void setDateAff(Date dateAff) {
        this.dateAff = dateAff;
    }

    public Date getDateFinAff() {
        return dateFinAff;
    }

    public void setDateFinAff(Date dateFinAff) {
        this.dateFinAff = dateFinAff;
    }

    public String getChampInterim() {
        return champInterim;
    }

    public void setChampInterim(String champInterim) {
        this.champInterim = champInterim;
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

    public Long getCin() {
        return cin;
    }

    public void setCin(Long cin) {
        this.cin = cin;
    }

    public String getTypeContrat() {
        return typeContrat;
    }

    public void setTypeContrat(String typeContrat) {
        this.typeContrat = typeContrat;
    }

    public Set<Skill> getSkill() {
        return skill;
    }

    public void setSkill(Set<Skill> skill) {
        this.skill = skill;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Set<Participant> getParticipant() {
        return participant;
    }

    public void setParticipant(Set<Participant> participant) {
        this.participant = participant;
    }

    public Set<SkillEvaluationMethod> getSkillEvaluationMethods() {
        return skillEvaluationMethods;
    }

    public void setSkillEvaluationMethods(Set<SkillEvaluationMethod> skillEvaluationMethods) {
        this.skillEvaluationMethods = skillEvaluationMethods;
    }
}


