package com.crm.operis_app.services.NonConformite;

import com.crm.operis_app.model.NonConformite.ClotureNonConformite;
import com.crm.operis_app.model.NonConformite.ListeNonConformite;
import com.crm.operis_app.repository.NonConformite.ClotureNonConformiteRepository;
import com.crm.operis_app.repository.NonConformite.ListeNonConformiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ClotureNonConformiteService {
    @Autowired
    ListeNonConformiteRepository listeNonConformiteRepository;

    @Autowired
    ClotureNonConformiteRepository clotureNonConformiteRepository;

    public List<ClotureNonConformite> getClotureNonConformite() {
        return clotureNonConformiteRepository.findAll();
    }

    public Optional<ClotureNonConformite> getClotureNonConformiteById(Long validationActionId) {
        if (!clotureNonConformiteRepository.existsById(validationActionId)) {
            throw new ResourceNotFoundException("ClotureNonConformite with id " + validationActionId + " not found");
        }
        return clotureNonConformiteRepository.findById(validationActionId);
    }

    public ClotureNonConformite createClotureNonConformite(Long liste_actionId, ClotureNonConformite validationAction) {
        Set<ClotureNonConformite> validationActions = new HashSet<>();

        Optional<ListeNonConformite> byId = listeNonConformiteRepository.findById(liste_actionId);
        if (!byId.isPresent()) {
            throw new ResourceNotFoundException("liste_NC with id " + liste_actionId + " does not exist");
        }
        ListeNonConformite liste_action = byId.get();
        validationAction.setListeNonConformite(liste_action);

        ClotureNonConformite validationAction1 = this.clotureNonConformiteRepository.save(validationAction);
        validationActions.add(validationAction1);
        return validationAction1;

    }

    public ClotureNonConformite updateClotureNonConformiteById(Long action1Id, ClotureNonConformite validationAction) {
        if (!clotureNonConformiteRepository.existsById(action1Id)) {
            throw new ResourceNotFoundException("ClotureNonConformite  with id " + action1Id + " not found");
        }
        Optional<ClotureNonConformite> Liste = clotureNonConformiteRepository.findById(action1Id);

        if (!Liste.isPresent()) {
            throw new ResourceNotFoundException("ClotureNonConformite with id " + action1Id + " not found");
        }
        ClotureNonConformite liste1 = Liste.get();
        liste1.setDateCloture(validationAction.getDateCloture());
        liste1.setEtatNonConformite(validationAction.getEtatNonConformite());
        liste1.setCommentaire(validationAction.getCommentaire());


        return clotureNonConformiteRepository.save(liste1);
    }

    public ResponseEntity<Object> deleteClotureNonConformiteById(long action1Id) {
        if (!clotureNonConformiteRepository.existsById(action1Id)) {
            throw new ResourceNotFoundException("ClotureNonConformite with id " + action1Id + " not found");
        }
        return ResponseEntity.ok().build();
    }

}
