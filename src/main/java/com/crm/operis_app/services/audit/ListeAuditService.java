package com.crm.operis_app.services.audit;

import com.crm.operis_app.model.audit.ListeAudit;

import com.crm.operis_app.repository.audit.ListeAuditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ListeAuditService {
    @Autowired
    ListeAuditRepository listeAuditRepository;


    public List<ListeAudit> getAllListeAudit() {
        return listeAuditRepository.findByActiveIsTrue();
    }


    public Optional<ListeAudit> getListeById(Long liste_auditId) {
        if (!listeAuditRepository.existsById(liste_auditId)) {
            throw new ResourceNotFoundException("Liste Audit with id " + liste_auditId + " not found");
        }
        return listeAuditRepository.findById(liste_auditId);
    }

    public ListeAudit createListe(ListeAudit listeAudit) {
        return listeAuditRepository.save(listeAudit);

    }

    public ListeAudit updateListeById(Long liste_auditId, ListeAudit listeRequest) {
        if (!listeAuditRepository.existsById(liste_auditId)) {
            throw new ResourceNotFoundException("liste Audit with id " + liste_auditId + " not found");
        }
        Optional<ListeAudit> Liste = listeAuditRepository.findById(liste_auditId);

        if (!Liste.isPresent()) {
            throw new ResourceNotFoundException("Liste Audit with id " + liste_auditId + " not found");
        }

        ListeAudit liste1 = Liste.get();
        liste1.setSite(listeRequest.getSite());
        liste1.setTypeAudit(listeRequest.getTypeAudit());
        liste1.setDateDebutPlanifiee(listeRequest.getDateDebutPlanifiee());
        liste1.setDateFinPlanifiee(listeRequest.getDateFinPlanifiee());
        liste1.setDateDebutReelle(listeRequest.getDateDebutReelle());
        liste1.setDateFinRelle(listeRequest.getDateFinRelle());
        liste1.setCommentaire(listeRequest.getCommentaire());

        return listeAuditRepository.save(liste1);
    }


    public ResponseEntity<Object> deleteListeById(Long liste_auditId) {
        if (!listeAuditRepository.existsById(liste_auditId)) {
            throw new ResourceNotFoundException("liste  Audit with id " + liste_auditId + " not found");
        }

        listeAuditRepository.deleteById(liste_auditId);
        return ResponseEntity.ok().build();
    }


    public ResponseEntity<Object> archiveAuditById(Long liste_auditId,Boolean isDelete) {

        Optional<ListeAudit> Liste = listeAuditRepository.findById(liste_auditId);
        if (!Liste.isPresent()) {
            throw new ResourceNotFoundException("Liste Audit with id " + liste_auditId + " not found");
        }
        ListeAudit listeAudit = Liste.get();
        listeAudit.setActive(isDelete);

        listeAuditRepository.save(listeAudit);
        return ResponseEntity.ok().build();
    }




}

