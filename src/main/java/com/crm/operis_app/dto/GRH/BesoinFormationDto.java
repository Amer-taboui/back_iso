package com.crm.operis_app.dto.GRH;

public class BesoinFormationDto {
    private Long id;
    private String Competence ;
    private String nomPersonnel;
    private String prenomPersonnel;

    public BesoinFormationDto(Long id, String competence, String nomPersonnel, String prenomPersonnel) {
        this.id = id;
        Competence = competence;
        this.nomPersonnel = nomPersonnel;
        this.prenomPersonnel = prenomPersonnel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompetence() {
        return Competence;
    }

    public void setCompetence(String competence) {
        Competence = competence;
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
}
