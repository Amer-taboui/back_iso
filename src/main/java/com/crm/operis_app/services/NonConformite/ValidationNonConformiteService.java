package com.crm.operis_app.services.NonConformite;

import com.crm.operis_app.model.NonConformite.ListeNonConformite;
import com.crm.operis_app.model.NonConformite.ValidationNonConformite;
import com.crm.operis_app.repository.NonConformite.ListeNonConformiteRepository;
import com.crm.operis_app.repository.NonConformite.ValidationNonConformiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ValidationNonConformiteService {
    @Autowired
    ListeNonConformiteRepository listeNonConformiteRepository;

    @Autowired
    ValidationNonConformiteRepository validationNonConformiteRepository;

    public List<ValidationNonConformite> getValidationNonConformite() {
        return validationNonConformiteRepository.findAll();
    }

    public Optional<ValidationNonConformite> getValidationNonConformiteById(Long validationActionId) {
        if (!validationNonConformiteRepository.existsById(validationActionId)) {
            throw new ResourceNotFoundException("validationAction with id " + validationActionId + " not found");
        }
        return validationNonConformiteRepository.findById(validationActionId);
    }

    public ValidationNonConformite createValidationNonConformite(Long liste_actionId, ValidationNonConformite validationAction) {
        Set<ValidationNonConformite> validationActions = new HashSet<>();

        Optional<ListeNonConformite> byId = listeNonConformiteRepository.findById(liste_actionId);
        if (!byId.isPresent()) {
            throw new ResourceNotFoundException("liste_action with id " + liste_actionId + " does not exist");
        }
        ListeNonConformite liste_action = byId.get();
        validationAction.setListeNonConformite(liste_action);

        ValidationNonConformite validationAction1 = this.validationNonConformiteRepository.save(validationAction);
        validationActions.add(validationAction1);
        return validationAction1;

    }

    public ValidationNonConformite updateValidationNonConformiteById(Long action1Id, ValidationNonConformite validationAction) {
        if (!validationNonConformiteRepository.existsById(action1Id)) {
            throw new ResourceNotFoundException("ValidationAction  with id " + action1Id + " not found");
        }
        Optional<ValidationNonConformite> Liste = validationNonConformiteRepository.findById(action1Id);

        if (!Liste.isPresent()) {
            throw new ResourceNotFoundException("ValidationAction with id " + action1Id + " not found");
        }
        ValidationNonConformite liste1 = Liste.get();
        liste1.setTraitement(validationAction.getTraitement());
        liste1.setDateValidation(validationAction.getDateValidation());
        liste1.setEtatValidation(validationAction.getEtatValidation());



        return validationNonConformiteRepository.save(liste1);
    }

    public ResponseEntity<Object> deleteValidationNonConformiteById(long action1Id) {
        if (!validationNonConformiteRepository.existsById(action1Id)) {
            throw new ResourceNotFoundException("validationAction with id " + action1Id + " not found");
        }
        validationNonConformiteRepository.deleteById(action1Id);
        return ResponseEntity.ok().build();
    }

}
