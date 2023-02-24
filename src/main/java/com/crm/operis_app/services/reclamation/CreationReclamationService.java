package com.crm.operis_app.services.reclamation;
import com.crm.operis_app.model.reclamation.CreationReclamation;
import com.crm.operis_app.model.reclamation.ListeReclamation;
import com.crm.operis_app.repository.reclamation.CreationReclamationRepository;
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
public class CreationReclamationService {
    @Autowired
    ListeReclamationRepository listeReclamationRepository;

    @Autowired
    CreationReclamationRepository creationReclamationRepository;



    public List<CreationReclamation> getCreationReclamation() {
        return creationReclamationRepository.findAll();
    }


    public Optional<CreationReclamation> getCreationReclamationById(Long validationActionId) {
        if (!creationReclamationRepository.existsById(validationActionId)) {
            throw new ResourceNotFoundException("validationAction with id " + validationActionId + " not found");
        }
        return creationReclamationRepository.findById(validationActionId);
    }

    public CreationReclamation createCreationReclamation(Long liste_actionId, CreationReclamation validationAction) {
        Set<CreationReclamation> validationActions = new HashSet<>();

        Optional<ListeReclamation> byId = listeReclamationRepository.findById(liste_actionId);
        if (!byId.isPresent()) {
            throw new ResourceNotFoundException("liste_action with id " + liste_actionId + " does not exist");
        }
        ListeReclamation liste_action = byId.get();
        validationAction.setListeReclamation(liste_action);

        CreationReclamation validationAction1 = this.creationReclamationRepository.save(validationAction);
        validationActions.add(validationAction1);
        return validationAction1;

    }

    public CreationReclamation updateCreationReclamationById(Long action1Id, CreationReclamation validationAction) {
        if (! creationReclamationRepository.existsById(action1Id)) {
            throw new ResourceNotFoundException("ValidationAction  with id " + action1Id + " not found");
        }
        Optional<CreationReclamation> Liste =  creationReclamationRepository.findById(action1Id);

        if (!Liste.isPresent()) {
            throw new ResourceNotFoundException("ValidationAction with id " + action1Id + " not found");
        }
        CreationReclamation liste1 = Liste.get();
        liste1.setEtatCreation(validationAction.getEtatCreation());
        liste1.setCommentaire(validationAction.getCommentaire());


        return  creationReclamationRepository.save(liste1);
    }

    public ResponseEntity<Object> deleteCreationReclamationById(long action1Id) {
        if (! creationReclamationRepository.existsById(action1Id)) {
            throw new ResourceNotFoundException("validationAction with id " + action1Id + " not found");
        }
        creationReclamationRepository.deleteById(action1Id);
        return ResponseEntity.ok().build();
    }

}
