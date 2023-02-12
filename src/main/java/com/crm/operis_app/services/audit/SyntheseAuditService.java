package com.crm.operis_app.services.audit;

import com.crm.operis_app.model.audit.ListeAudit;
import com.crm.operis_app.model.audit.SyntheseAudit;
import com.crm.operis_app.repository.audit.ListeAuditRepository;
import com.crm.operis_app.repository.audit.SyntheseAuditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class SyntheseAuditService {

    @Autowired
    ListeAuditRepository listeAuditRepository;

    @Autowired
    SyntheseAuditRepository syntheseAuditRepository;

    public List<SyntheseAudit> getSyntheseAudit() {
        return syntheseAuditRepository.findAll();
    }


    public Optional<SyntheseAudit> getSyntheseAuditById(Long validationActionId) {
        if (!syntheseAuditRepository.existsById(validationActionId)) {
            throw new ResourceNotFoundException("SyntheseAudit with id " + validationActionId + " not found");
        }
        return syntheseAuditRepository.findById(validationActionId);
    }

    public SyntheseAudit createSyntheseAudit(Long liste_actionId, SyntheseAudit constatAudit) {
        Set<SyntheseAudit> syntheseAudits = new HashSet<>();
        Optional<ListeAudit> byId = listeAuditRepository.findById(liste_actionId);
        if (!byId.isPresent()) {
            throw new ResourceNotFoundException("liste_action with id " + liste_actionId + " does not exist");
        }
        ListeAudit liste_action = byId.get();
        constatAudit.setListeAudit(liste_action);
        SyntheseAudit validationAction1 = this.syntheseAuditRepository.save(constatAudit);
        syntheseAudits.add(validationAction1);
        return validationAction1;

    }

    public SyntheseAudit updateSyntheseAuditById(Long action1Id, SyntheseAudit validationAction) {
        if (! syntheseAuditRepository.existsById(action1Id)) {
            throw new ResourceNotFoundException("SyntheseAudit  with id " + action1Id + " not found");
        }
        Optional<SyntheseAudit> Liste =  syntheseAuditRepository.findById(action1Id);

        if (!Liste.isPresent()) {
            throw new ResourceNotFoundException("SyntheseAudit with id " + action1Id + " not found");
        }
        SyntheseAudit liste1 = Liste.get();
        liste1.setCommentaire(validationAction.getCommentaire());
        liste1.setFaiblesses(validationAction.getFaiblesses());
        liste1.setForces(validationAction.getForces());
        liste1.setObservations(validationAction.getObservations());
        return  syntheseAuditRepository.save(liste1);
    }

    public ResponseEntity<Object> deleteSyntheseAuditById(long action1Id) {
        if (! syntheseAuditRepository.existsById(action1Id)) {
            throw new ResourceNotFoundException("SyntheseAudit with id " + action1Id + " not found");
        }
        syntheseAuditRepository.deleteById(action1Id);
        return ResponseEntity.ok().build();
    }


}

