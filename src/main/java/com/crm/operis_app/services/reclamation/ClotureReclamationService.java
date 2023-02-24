package com.crm.operis_app.services.reclamation;

import com.crm.operis_app.model.action.actionCorrection.ListeActionCorrection;
import com.crm.operis_app.model.action.actionCorrection.ValidationAction;

import com.crm.operis_app.model.reclamation.ClotureReclamation;
import com.crm.operis_app.model.reclamation.ListeReclamation;
import com.crm.operis_app.repository.reclamation.ClotureReclamationRepository;
import com.crm.operis_app.repository.reclamation.ListeReclamationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
@Service
public class ClotureReclamationService {
    @Autowired
    ListeReclamationRepository listeReclamationRepository;

    @Autowired
    ClotureReclamationRepository clotureReclamationRepository;



    public List<ClotureReclamation> getClotureReclamation() {
        return clotureReclamationRepository.findAll();
    }


    public Optional<ClotureReclamation> getClotureReclamationById(Long validationActionId) {
        if (!clotureReclamationRepository.existsById(validationActionId)) {
            throw new ResourceNotFoundException("validationAction with id " + validationActionId + " not found");
        }
        return clotureReclamationRepository.findById(validationActionId);
    }

    public ClotureReclamation createClotureReclamation(Long liste_actionId, ClotureReclamation validationAction) {
        Set<ClotureReclamation> validationActions = new HashSet<>();

        Optional<ListeReclamation> byId = listeReclamationRepository.findById(liste_actionId);
        if (!byId.isPresent()) {
            throw new ResourceNotFoundException("liste_action with id " + liste_actionId + " does not exist");
        }
        ListeReclamation liste_action = byId.get();
        validationAction.setListeReclamation(liste_action);

        ClotureReclamation validationAction1 = this.clotureReclamationRepository.save(validationAction);
        validationActions.add(validationAction1);
        return validationAction1;

    }

    public ClotureReclamation updateClotureReclamationById(Long action1Id, ClotureReclamation validationAction) {
        if (! clotureReclamationRepository.existsById(action1Id)) {
            throw new ResourceNotFoundException("ValidationAction  with id " + action1Id + " not found");
        }
        Optional<ClotureReclamation> Liste =  clotureReclamationRepository.findById(action1Id);

        if (!Liste.isPresent()) {
            throw new ResourceNotFoundException("ValidationAction with id " + action1Id + " not found");
        }
        ClotureReclamation liste1 = Liste.get();
        liste1.setEtatReclamation(validationAction.getEtatReclamation());
        liste1.setDateCloture(validationAction.getDateCloture());
        liste1.setCommentaire(validationAction.getCommentaire());


        return  clotureReclamationRepository.save(liste1);
    }

    public ResponseEntity<Object> deleteClotureReclamationById(long action1Id) {
        if (! clotureReclamationRepository.existsById(action1Id)) {
            throw new ResourceNotFoundException("validationAction with id " + action1Id + " not found");
        }
        clotureReclamationRepository.deleteById(action1Id);
        return ResponseEntity.ok().build();
    }

}
