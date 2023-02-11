package com.crm.operis_app.services.action;

import com.crm.operis_app.model.action.actionCorrection.ListeActionCorrection;
import com.crm.operis_app.model.action.actionCorrection.ValidationAction;
import com.crm.operis_app.repository.action.ListeActionCorrectionRepository;
import com.crm.operis_app.repository.action.ValidationActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service

public class ValidationActionService {

    @Autowired
    ListeActionCorrectionRepository listeActionCorrectionRepository;

    @Autowired
    ValidationActionRepository validationActionRepository;



    public List<ValidationAction> getValidationAction() {
        return validationActionRepository.findAll();
    }


    public Optional<ValidationAction> getValidationActionById(Long validationActionId) {
        if (!validationActionRepository.existsById(validationActionId)) {
            throw new ResourceNotFoundException("validationAction with id " + validationActionId + " not found");
        }
        return validationActionRepository.findById(validationActionId);
    }

    public ValidationAction createValidationAction(Long liste_actionId, ValidationAction validationAction) {
        Set<ValidationAction> validationActions = new HashSet<>();

        Optional<ListeActionCorrection> byId = listeActionCorrectionRepository.findById(liste_actionId);
        if (!byId.isPresent()) {
            throw new ResourceNotFoundException("liste_action with id " + liste_actionId + " does not exist");
        }
        ListeActionCorrection liste_action = byId.get();
        validationAction.setListeActionCorrection(liste_action);

        ValidationAction validationAction1 = this.validationActionRepository.save(validationAction);
        validationActions.add(validationAction1);
        return validationAction1;

    }

    public ValidationAction updateValidationActionById(Long action1Id, ValidationAction validationAction) {
        if (! validationActionRepository.existsById(action1Id)) {
            throw new ResourceNotFoundException("ValidationAction  with id " + action1Id + " not found");
        }
        Optional<ValidationAction> Liste =  validationActionRepository.findById(action1Id);

        if (!Liste.isPresent()) {
            throw new ResourceNotFoundException("ValidationAction with id " + action1Id + " not found");
        }
        ValidationAction liste1 = Liste.get();
        liste1.setDateValidation(validationAction.getDateValidation());
        liste1.setEtatValidation(validationAction.isEtatValidation());


        return  validationActionRepository.save(liste1);
    }

    public ResponseEntity<Object> deleteValidationActionById(long action1Id) {
        if (! validationActionRepository.existsById(action1Id)) {
            throw new ResourceNotFoundException("validationAction with id " + action1Id + " not found");
        }
        validationActionRepository.deleteById(action1Id);
        return ResponseEntity.ok().build();
    }


}
