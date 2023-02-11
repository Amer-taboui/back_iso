package com.crm.operis_app.services.action;
import com.crm.operis_app.model.action.actionCorrection.ListeActionCorrection;
import com.crm.operis_app.repository.action.ActionCostRepository;
import com.crm.operis_app.repository.action.ListeActionCorrectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ListeActionCorrectionServiceImp {
    @Autowired
    ListeActionCorrectionRepository liste_actionRepository;


    @Autowired
    ActionCostRepository actionCostRepository;

    public List<ListeActionCorrection> getAllListeActionCorrection() {
        return liste_actionRepository.findByActiveIsTrue();
    }




    public Optional<ListeActionCorrection> getListeById(Long liste_actionId) {
        if (!liste_actionRepository.existsById(liste_actionId)) {
            throw new ResourceNotFoundException("Liste Action with id " + liste_actionId + " not found");
        }
        return liste_actionRepository.findById(liste_actionId);
    }




    public ListeActionCorrection createListe(ListeActionCorrection liste_action) {
        return liste_actionRepository.save(liste_action);

    }

    public ListeActionCorrection updateListeById(Long liste_actionId, ListeActionCorrection listeRequest) {
        if (!liste_actionRepository.existsById(liste_actionId)) {
            throw new ResourceNotFoundException("liste Action with id " + liste_actionId + " not found");
        }
        Optional<ListeActionCorrection> Liste = liste_actionRepository.findById(liste_actionId);

        if (!Liste.isPresent()) {
            throw new ResourceNotFoundException("Liste Action with id " + liste_actionId + " not found");
        }

        ListeActionCorrection liste1 = Liste.get();
        liste1.setDateAction(listeRequest.getDateAction());
        liste1.setSource(listeRequest.getSource());
        liste1.setTypeAction(listeRequest.getTypeAction());

        return liste_actionRepository.save(liste1);
    }


    public ResponseEntity<Object> deleteListeById(Long liste_actionId) {
        if (!liste_actionRepository.existsById(liste_actionId)) {
            throw new ResourceNotFoundException("liste  Action with id " + liste_actionId + " not found");
        }

        liste_actionRepository.deleteById(liste_actionId);
        return ResponseEntity.ok().build();
    }


    public ResponseEntity<Object> archiveActionCorrectionById(Long liste_actionId,Boolean isDelete) {

        Optional<ListeActionCorrection> Liste = liste_actionRepository.findById(liste_actionId);
        if (!Liste.isPresent()) {
            throw new ResourceNotFoundException("Liste Action with id " + liste_actionId + " not found");
        }
        ListeActionCorrection listeAction = Liste.get();
        listeAction.setActive(isDelete);

        liste_actionRepository.save(listeAction);
        return ResponseEntity.ok().build();
    }




}
