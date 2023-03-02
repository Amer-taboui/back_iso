package com.crm.operis_app.model.audit;

import com.crm.operis_app.model.action.actionCorrection.ListeActionCorrection;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Constat_Audit")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ConstatAudit {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "CONSTAT_AUDIT_ID")
        private Long id;
        @Column(name = "TYPE_CONSTAT")
        private String typeConstat;
        @Column(name = "CATEGORY_CONSTAT")
        private String categoryConstat;
        @Column(name = "NORME")
        private String norme;
        @Column(name = "CHAPITRE")
        private String chapitre;
        @Column(name = "CONSTAT")
        private String constatAudit;
        @Column(name = "LIBELLE")
        private String libelle;
        @Column(name = "RISQUE")
        private String risque;
        @Column(name="ETAT_CONSTAT")
        private boolean etatConstat = false;
        @JsonFormat(pattern="yyyy-MM-dd")
        @Column(name = "DATE_CLOTURE_CONSTAT")
        private Date dateClotureConstat;
        @Column(name = "RESPONSABLE_CLOTURE")
        private String responsableCloture;


        //-----------------ListeAudit-------------------------//

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "listeAuditId")
        private ListeAudit listeAudit;

        //-------------------------------------------------------------//

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getTypeConstat() {
                return typeConstat;
        }

        public void setTypeConstat(String typeConstat) {
                this.typeConstat = typeConstat;
        }

        public String getCategoryConstat() {
                return categoryConstat;
        }

        public void setCategoryConstat(String categoryConstat) {
                this.categoryConstat = categoryConstat;
        }

        public String getNorme() {
                return norme;
        }

        public void setNorme(String norme) {
                this.norme = norme;
        }

        public String getChapitre() {
                return chapitre;
        }

        public void setChapitre(String chapitre) {
                this.chapitre = chapitre;
        }

        public String getConstatAudit() {
                return constatAudit;
        }

        public void setConstatAudit(String constatAudit) {
                this.constatAudit = constatAudit;
        }

        public String getLibelle() {
                return libelle;
        }

        public void setLibelle(String libelle) {
                this.libelle = libelle;
        }

        public String getRisque() {
                return risque;
        }

        public void setRisque(String risque) {
                this.risque = risque;
        }

        public boolean isEtatConstat() {
                return etatConstat;
        }

        public void setEtatConstat(boolean etatConstat) {
                this.etatConstat = etatConstat;
        }

        public Date getDateClotureConstat() {
                return dateClotureConstat;
        }

        public void setDateClotureConstat(Date dateClotureConstat) {
                this.dateClotureConstat = dateClotureConstat;
        }

        public String getResponsableCloture() {
                return responsableCloture;
        }

        public void setResponsableCloture(String responsableCloture) {
                this.responsableCloture = responsableCloture;
        }
        @JsonIgnore

        public ListeAudit getListeAudit() {
                return listeAudit;
        }
        @JsonIgnore

        public void setListeAudit(ListeAudit listeAudit) {
                this.listeAudit = listeAudit;
        }
}
