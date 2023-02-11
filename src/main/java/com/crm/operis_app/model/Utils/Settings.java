package com.crm.operis_app.model.Utils;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "SETTINGS")

public class Settings {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SETTING_ID")
    private Long id;

    @Column(name = "GLOBAL_COST")
    private BigDecimal globalCost;

    @Column(name = "HUMAN_COST")
    private BigDecimal humanCost;

    @Column(name = "IS_HORS_TAXES")
    private Boolean isHorsTaxes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getGlobalCost() {
        return globalCost;
    }

    public void setGlobalCost(BigDecimal globalCost) {
        this.globalCost = globalCost;
    }

    public BigDecimal getHumanCost() {
        return humanCost;
    }

    public void setHumanCost(BigDecimal humanCost) {
        this.humanCost = humanCost;
    }

    public Boolean getHorsTaxes() {
        return isHorsTaxes;
    }

    public void setHorsTaxes(Boolean horsTaxes) {
        isHorsTaxes = horsTaxes;
    }
}