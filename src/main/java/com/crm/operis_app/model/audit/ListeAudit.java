package com.crm.operis_app.model.audit;

import com.crm.operis_app.model.GRH.Personal;
import com.crm.operis_app.model.files.FileModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Data
@Table(name = "LISTE_Audit")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class ListeAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LIST_AUDIT_ID")
    private Long id;
    @Column(name = "TYPE_ACTION")
    private String typeAudit;
    @Column(name = "SITE")
    private String site;
    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "DATE_DEBUT_PLANIFIEE")
    private Date dateDebutPlanifiee;
    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "DATE_FIN_PLANIFIEE")
    private Date dateFinPlanifiee;
    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "DATE_DEBUT_REELE")
    private Date dateDebutReelle;
    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "DATE_FIN_REELE")
    private Date dateFinRelle;
    @Column(name = "ETAT_AUDIT")
    private Boolean etatAudit = false;
    @Column(name = "COMMENTAIRE")
    private String commentaire;
    @Column(name="ACTIVE")
    private Boolean active=true;
//------------------------Constat-Audit------------------------------//
    @OrderBy("id ASC")
    @OneToMany(mappedBy = "listeAudit", cascade = CascadeType.ALL, fetch = FetchType.LAZY )
    private Set<ConstatAudit> constatAudit;
    //------------------------Synthese-Audit------------------------------//
    @OrderBy("id ASC")
    @OneToMany(mappedBy = "listeAudit", cascade = CascadeType.ALL, fetch = FetchType.LAZY )
    private Set<SyntheseAudit> syntheseAudit;
    //----------------------personnel----------------------------//

    @OrderBy("id ASC")
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "PERSONNEL_AUDITEUR", joinColumns = { @JoinColumn(name = "AUDIT_ID") }, inverseJoinColumns = {
            @JoinColumn(name = "PERSONAL_ID") })
    private Set<Personal> personnels;

    //-------------file-------------------------------//
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "Audit_FILE", joinColumns = { @JoinColumn(name = "LIST_AUDIT_ID") }, inverseJoinColumns = {
            @JoinColumn(name = "FILE_ID") })
    private Set<FileModel> fileModels;
    //------------------------Audite------------------------------//
    @OrderBy("id ASC")
    @OneToMany(mappedBy = "listeAudit", cascade = CascadeType.ALL, fetch = FetchType.LAZY )
    private Set<Audite> audite;
    //------------------------Constat-Audit------------------------------//
    @OrderBy("id ASC")
    @OneToMany(mappedBy = "listeAudit", cascade = CascadeType.ALL, fetch = FetchType.LAZY )
    private Set<Auditeur> auditeur;


}

