package com.crm.operis_app.model.reclamation;

import com.crm.operis_app.model.action.actionCorrection.ListeActionCorrection;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "RECLAMATION_COST")
public class ReclamationCost implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RECLAMATION_COST_ID")
    private Long id;

    @Lob
    @Column(name = "WORKFORCE")
    private String workforce;

    @Lob
    @Column(name = "PIECE")
    private String piece;

    @Lob
    @Column(name = "VARIOUS")
    private String various;

    @Lob
    @Column(name = "TRANSPORT")
    private String transport;

    @Column(name = "WORKFORCE_COST")
    private int workforceCost = 0;

    @Column(name = "PIECE_COST")
    private int pieceCost = 0;

    @Column(name = "VARIOUS_COST")
    private int variousCost = 0;

    @Column(name = "TRANSPORT_COST")
    private int transportCost = 0;

    @Column(name = "COST_TOTAL")
    private int costTotal = 0;


    @OneToOne(mappedBy = "reclamationCost")
    @JsonIgnore
    private ListeReclamation listeReclamation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWorkforce() {
        return workforce;
    }

    public void setWorkforce(String workforce) {
        this.workforce = workforce;
    }

    public String getPiece() {
        return piece;
    }

    public void setPiece(String piece) {
        this.piece = piece;
    }

    public String getVarious() {
        return various;
    }

    public void setVarious(String various) {
        this.various = various;
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    public int getWorkforceCost() {
        return workforceCost;
    }

    public void setWorkforceCost(int workforceCost) {
        this.workforceCost = workforceCost;
    }

    public int getPieceCost() {
        return pieceCost;
    }

    public void setPieceCost(int pieceCost) {
        this.pieceCost = pieceCost;
    }

    public int getVariousCost() {
        return variousCost;
    }

    public void setVariousCost(int variousCost) {
        this.variousCost = variousCost;
    }

    public int getTransportCost() {
        return transportCost;
    }

    public void setTransportCost(int transportCost) {
        this.transportCost = transportCost;
    }

    public int getCostTotal() {
        return costTotal;
    }

    public void setCostTotal(int costTotal) {
        this.costTotal = costTotal;
    }

    public ListeReclamation getListeReclamation() {
        return listeReclamation;
    }

    public void setListeReclamation(ListeReclamation listeReclamation) {
        this.listeReclamation = listeReclamation;
    }
}