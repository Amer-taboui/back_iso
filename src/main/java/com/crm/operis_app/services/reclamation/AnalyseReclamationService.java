package com.crm.operis_app.services.reclamation;
import com.crm.operis_app.model.reclamation.AnalyseReclamation;
import com.crm.operis_app.model.reclamation.ListeReclamation;
import com.crm.operis_app.repository.reclamation.AnalyseReclamationRepository;
import com.crm.operis_app.repository.reclamation.ListeReclamationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
@Service
public class AnalyseReclamationService {
    @Autowired
    ListeReclamationRepository listeReclamationRepository;

    @Autowired
    AnalyseReclamationRepository analyseReclamationRepository;

    public List<AnalyseReclamation> getAnalyseReclamation() {
        return analyseReclamationRepository.findAll();
    }

    public Optional<AnalyseReclamation> getAnalyseReclamationById(Long validationActionId) {
        if (!analyseReclamationRepository.existsById(validationActionId)) {
            throw new ResourceNotFoundException("validationAction with id " + validationActionId + " not found");
        }
        return analyseReclamationRepository.findById(validationActionId);
    }

    public AnalyseReclamation createAnalyseReclamation(Long liste_actionId, AnalyseReclamation validationAction) {
        Set<AnalyseReclamation> validationActions = new HashSet<>();

        Optional<ListeReclamation> byId = listeReclamationRepository.findById(liste_actionId);
        if (!byId.isPresent()) {
            throw new ResourceNotFoundException("liste_action with id " + liste_actionId + " does not exist");
        }
        ListeReclamation liste_action = byId.get();
        validationAction.setListeReclamation(liste_action);

        AnalyseReclamation validationAction1 = this.analyseReclamationRepository.save(validationAction);
        validationActions.add(validationAction1);
        return validationAction1;

    }

    public AnalyseReclamation updateAnalyseReclamationById(Long action1Id, AnalyseReclamation validationAction) {
        if (! analyseReclamationRepository.existsById(action1Id)) {
            throw new ResourceNotFoundException("ValidationAction  with id " + action1Id + " not found");
        }
        Optional<AnalyseReclamation> Liste =  analyseReclamationRepository.findById(action1Id);

        if (!Liste.isPresent()) {
            throw new ResourceNotFoundException("ValidationAction with id " + action1Id + " not found");
        }
        AnalyseReclamation liste1 = Liste.get();
        liste1.setAnalyseCause(validationAction.getAnalyseCause());
        liste1.setEtatAnalyse(validationAction.getEtatAnalyse());


        return  analyseReclamationRepository.save(liste1);
    }

    public ResponseEntity<Object> deleteAnalyseReclamationById(long action1Id) {
        if (! analyseReclamationRepository.existsById(action1Id)) {
            throw new ResourceNotFoundException("validationAction with id " + action1Id + " not found");
        }
        analyseReclamationRepository.deleteById(action1Id);
        return ResponseEntity.ok().build();
    }


    public List<BigInteger> getPersonnelIdListByReclamation(Long actionId) {
        return analyseReclamationRepository.findPersonnelIdListByAnalyseReclamation(actionId);
    }
}
