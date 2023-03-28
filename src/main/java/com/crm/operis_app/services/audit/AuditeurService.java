package com.crm.operis_app.services.audit;

import com.crm.operis_app.model.audit.Auditeur;
import com.crm.operis_app.model.audit.ListeAudit;
import com.crm.operis_app.repository.audit.AuditeurRepository;
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
public class AuditeurService {
    @Autowired
    AuditeurRepository auditeurRepository;
    @Autowired
    ListeAuditRepository listeAuditRepository;

    public List<Auditeur> getAllAuditeur(){
        return auditeurRepository.findAll();
    }

    public Optional<Auditeur> getAuditeurById(Long validationActionId) {
        if (!auditeurRepository.existsById(validationActionId)) {
            throw new ResourceNotFoundException("Auditeur with id " + validationActionId + " not found");
        }
        return auditeurRepository.findById(validationActionId);
    }

    public Auditeur createAuditeur(Long liste_actionId, Auditeur constatAudit) {
        Set<Auditeur> validationActions = new HashSet<>();

        Optional<ListeAudit> byId = listeAuditRepository.findById(liste_actionId);
        if (!byId.isPresent()) {
            throw new ResourceNotFoundException("liste_action with id " + liste_actionId + " does not exist");
        }
        ListeAudit liste_action = byId.get();
        constatAudit.setListeAudit(liste_action);

        Auditeur validationAction1 = this.auditeurRepository.save(constatAudit);
        validationActions.add(validationAction1);
        return validationAction1;

    }

    public Auditeur updateAuditeurById(Long action1Id, Auditeur validationAction) {
        if (! auditeurRepository.existsById(action1Id)) {
            throw new ResourceNotFoundException("Auditeur  with id " + action1Id + " not found");
        }
        Optional<Auditeur> Liste =  auditeurRepository.findById(action1Id);

        if (!Liste.isPresent()) {
            throw new ResourceNotFoundException("Auditeur with id " + action1Id + " not found");
        }
        Auditeur liste1 = Liste.get();
        liste1.setNameAuditeur(validationAction.getNameAuditeur());
        liste1.setPosteAuditeur(validationAction.getPosteAuditeur());


        return  auditeurRepository.save(liste1);
    }

    public ResponseEntity<Object> deleteAuditeurById(long action1Id) {
        if (! auditeurRepository.existsById(action1Id)) {
            throw new ResourceNotFoundException("Auditeur with id " + action1Id + " not found");
        }
        auditeurRepository.deleteById(action1Id);
        return ResponseEntity.ok().build();
    }

}
