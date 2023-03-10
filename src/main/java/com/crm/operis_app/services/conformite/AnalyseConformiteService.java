package com.crm.operis_app.services.conformite;

import com.crm.operis_app.model.conformite.AnalyseConformite;
import com.crm.operis_app.model.conformite.ListeConformite;
import com.crm.operis_app.model.reclamation.AnalyseReclamation;
import com.crm.operis_app.model.reclamation.ListeReclamation;
import com.crm.operis_app.repository.conformite.AnalyseConformiteRepository;
import com.crm.operis_app.repository.conformite.ListeConformiteRepository;
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
public class AnalyseConformiteService {
    @Autowired
    AnalyseConformiteRepository analyseConformiteRepository;
    @Autowired
    ListeConformiteRepository listeConformiteRepository;

    public List<AnalyseConformite> getAnalyseConformite() {
        return analyseConformiteRepository.findAll();
    }

    public Optional<AnalyseConformite> getAnalyseConformiteById(Long validationActionId) {
        if (!analyseConformiteRepository.existsById(validationActionId)) {
            throw new ResourceNotFoundException("AnalyseConformite with id " + validationActionId + " not found");
        }
        return analyseConformiteRepository.findById(validationActionId);
    }

    public AnalyseConformite createAnalyseConformite(Long liste_actionId, AnalyseConformite validationAction) {
        Set<AnalyseConformite> validationActions = new HashSet<>();

        Optional<ListeConformite> byId = listeConformiteRepository.findById(liste_actionId);
        if (!byId.isPresent()) {
            throw new ResourceNotFoundException("liste_action with id " + liste_actionId + " does not exist");
        }
        ListeConformite liste_action = byId.get();
        validationAction.setListeConformite(liste_action);

        AnalyseConformite validationAction1 = this.analyseConformiteRepository.save(validationAction);
        validationActions.add(validationAction1);
        return validationAction1;

    }

    public AnalyseConformite updateAnalyseConformiteById(Long action1Id, AnalyseConformite validationAction) {
        if (! analyseConformiteRepository.existsById(action1Id)) {
            throw new ResourceNotFoundException("AnalyseConformite  with id " + action1Id + " not found");
        }
        Optional<AnalyseConformite> Liste =  analyseConformiteRepository.findById(action1Id);

        if (!Liste.isPresent()) {
            throw new ResourceNotFoundException("ValidationAction with id " + action1Id + " not found");
        }
        AnalyseConformite liste1 = Liste.get();
        liste1.setResultatAnalyse(validationAction.getResultatAnalyse());
        liste1.setDateCreation(validationAction.getDateCreation());


        return  analyseConformiteRepository.save(liste1);
    }

    public ResponseEntity<Object> deleteAnalyseConformiteById(long action1Id) {
        if (! analyseConformiteRepository.existsById(action1Id)) {
            throw new ResourceNotFoundException("validationAction with id " + action1Id + " not found");
        }
        analyseConformiteRepository.deleteById(action1Id);
        return ResponseEntity.ok().build();
    }


    public List<BigInteger> getPersonnelIdListByConformite(Long actionId) {
        return analyseConformiteRepository.findPersonnelIdListByAnalyseConformite(actionId);
    }
}


