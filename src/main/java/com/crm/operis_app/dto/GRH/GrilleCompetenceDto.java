package com.crm.operis_app.dto.GRH;

public class GrilleCompetenceDto {
    private Long id;
    private String poste ;
    private String competencePersonnel ;
    private String nomPersonnel;
    private String prenomPersonnel;
    private String niveauxMaitrisePersonnel ;

    public GrilleCompetenceDto(Long id, String poste, String competencePersonnel, String nomPersonnel, String prenomPersonnel, String niveauxMaitrisePersonnel) {
        this.id = id;
        this.poste = poste;
        this.competencePersonnel = competencePersonnel;
        this.nomPersonnel = nomPersonnel;
        this.prenomPersonnel = prenomPersonnel;
        this.niveauxMaitrisePersonnel = niveauxMaitrisePersonnel;
    }

    public Long getId() {
        return id;
    }

    public String getNiveauxMaitrisePersonnel() {
        return niveauxMaitrisePersonnel;
    }

    public void setNiveauxMaitrisePersonnel(String niveauxMaitrisePersonnel) {
        this.niveauxMaitrisePersonnel = niveauxMaitrisePersonnel;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public String getCompetencePersonnel() {
        return competencePersonnel;
    }

    public void setCompetencePersonnel(String competencePersonnel) {
        this.competencePersonnel = competencePersonnel;
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
