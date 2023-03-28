package com.crm.operis_app.services.audit;

import com.crm.operis_app.model.audit.Audite;
import com.crm.operis_app.model.audit.ListeAudit;
import com.crm.operis_app.repository.audit.AuditeRepository;
import com.crm.operis_app.repository.audit.ListeAuditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AuditeService {
    @Autowired
    AuditeRepository auditeRepository;
    @Autowired
    ListeAuditRepository listeAuditRepository;


   public List<Audite> getAllAudit() {
       return auditeRepository.findAll();
   }

    public Optional<Audite> getAuditeById(Long validationActionId) {
        if (!auditeRepository.existsById(validationActionId)) {
            throw new ResourceNotFoundException("Audit with id " + validationActionId + " not found");
        }
        return auditeRepository.findById(validationActionId);
    }

    public Audite createAudite(Long liste_actionId, Audite constatAudit) {
        Set<Audite> validationActions = new HashSet<>();

        Optional<ListeAudit> byId = listeAuditRepository.findById(liste_actionId);
        if (!byId.isPresent()) {
            throw new ResourceNotFoundException("liste_audit with id " + liste_actionId + " does not exist");
        }
        ListeAudit liste_audit = byId.get();
        constatAudit.setListeAudit(liste_audit);

        Audite validationAction1 = this.auditeRepository.save(constatAudit);
        validationActions.add(validationAction1);
        return validationAction1;

    }

    public Audite updateAuditeById(Long action1Id, Audite validationAction) {
        if (! auditeRepository.existsById(action1Id)) {
            throw new ResourceNotFoundException("Audit  with id " + action1Id + " not found");
        }
        Optional<Audite> Liste =  auditeRepository.findById(action1Id);

        if (!Liste.isPresent()) {
            throw new ResourceNotFoundException("Audit with id " + action1Id + " not found");
        }
        Audite liste1 = Liste.get();
        liste1.setNameAudite(validationAction.getNameAudite());
        liste1.setPosteAudite(validationAction.getPosteAudite());


        return  auditeRepository.save(liste1);
    }

    public ResponseEntity<Object> deleteAuditeById(long action1Id) {
        if (! auditeRepository.existsById(action1Id)) {
            throw new ResourceNotFoundException("Audit with id " + action1Id + " not found");
        }
        auditeRepository.deleteById(action1Id);
        return ResponseEntity.ok().build();
    }




}
