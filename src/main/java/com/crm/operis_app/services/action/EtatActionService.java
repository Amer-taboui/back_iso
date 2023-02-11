package com.crm.operis_app.services.action;

import com.crm.operis_app.model.action.actionCorrection.EtatAction;
import com.crm.operis_app.model.action.actionCorrection.ListeActionCorrection;
import com.crm.operis_app.model.action.actionCorrection.ValidationAction;
import com.crm.operis_app.repository.action.EtatActionRepository;
import com.crm.operis_app.repository.action.ListeActionCorrectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service

public class EtatActionService {

    @Autowired
    ListeActionCorrectionRepository listeActionCorrectionRepository;

    @Autowired
    EtatActionRepository etatActionRepository;

    public List<EtatAction> getEtatAction() {
        return etatActionRepository.findAll();
    }

    public Optional<EtatAction> getEtatActionById(Long validationActionId) {
        if (!etatActionRepository.existsById(validationActionId)) {
            throw new ResourceNotFoundException("validationAction with id " + validationActionId + " not found");
        }
        return etatActionRepository.findById(validationActionId);
    }
    public EtatAction createEtatAction(Long liste_actionId, EtatAction etatAction ) {
        Set<EtatAction> validationActions = new HashSet<>();
        Optional<ListeActionCorrection> byId = listeActionCorrectionRepository.findById(liste_actionId);
        if (!byId.isPresent()) {
            throw new ResourceNotFoundException("liste_action with id " + liste_actionId + " does not exist");
        }
        ListeActionCorrection liste_action = byId.get();
        etatAction.setListeActionCorrection(liste_action);
        EtatAction validationAction1 = this.etatActionRepository.save(etatAction);
        validationActions.add(validationAction1);
        return validationAction1;
    }

    public EtatAction updateEtatActionById(Long action1Id, EtatAction etatAction) {
        if (! etatActionRepository.existsById(action1Id)) {
            throw new ResourceNotFoundException("ValidationAction  with id " + action1Id + " not found");
        }
        Optional<EtatAction> Liste =  etatActionRepository.findById(action1Id);
        if (!Liste.isPresent()) {
            throw new ResourceNotFoundException("ValidationAction with id " + action1Id + " not found");
        }
        EtatAction liste1 = Liste.get();
        liste1.setCloture(etatAction.isCloture());
        liste1.setDateCloture(etatAction.getDateCloture());
        return  etatActionRepository.save(liste1);
    }

    public ResponseEntity<Object> deleteEtatActionById(long action1Id) {
        if (! etatActionRepository.existsById(action1Id)) {
            throw new ResourceNotFoundException("validationAction with id " + action1Id + " not found");
        }
        etatActionRepository.deleteById(action1Id);
        return ResponseEntity.ok().build();
    }
}


