package com.crm.operis_app.services.audit;
import com.crm.operis_app.model.audit.ConstatAudit;
import com.crm.operis_app.model.audit.ListeAudit;
import com.crm.operis_app.repository.audit.ConstatAuditRepository;
import com.crm.operis_app.repository.audit.ListeAuditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ConstatAuditService {


    @Autowired
    ListeAuditRepository listeAuditRepository;

    @Autowired
    ConstatAuditRepository constatAuditRepository;



    public List<ConstatAudit> getConstatAudit() {
        return constatAuditRepository.findAll();
    }


    public Optional<ConstatAudit> getConstatAuditById(Long validationActionId) {
        if (!constatAuditRepository.existsById(validationActionId)) {
            throw new ResourceNotFoundException("validationAction with id " + validationActionId + " not found");
        }
        return constatAuditRepository.findById(validationActionId);
    }

    public ConstatAudit createConstatAudit(Long liste_actionId, ConstatAudit constatAudit) {
        Set<ConstatAudit> validationActions = new HashSet<>();

        Optional<ListeAudit> byId = listeAuditRepository.findById(liste_actionId);
        if (!byId.isPresent()) {
            throw new ResourceNotFoundException("liste_action with id " + liste_actionId + " does not exist");
        }
        ListeAudit liste_action = byId.get();
        constatAudit.setListeAudit(liste_action);

        ConstatAudit validationAction1 = this.constatAuditRepository.save(constatAudit);
        validationActions.add(validationAction1);
        return validationAction1;

    }

    public ConstatAudit updateConstatAuditById(Long action1Id, ConstatAudit validationAction) {
        if (! constatAuditRepository.existsById(action1Id)) {
            throw new ResourceNotFoundException("ValidationAction  with id " + action1Id + " not found");
        }
        Optional<ConstatAudit> Liste =  constatAuditRepository.findById(action1Id);

        if (!Liste.isPresent()) {
            throw new ResourceNotFoundException("ValidationAction with id " + action1Id + " not found");
        }
        ConstatAudit liste1 = Liste.get();
        liste1.setConstatAudit(validationAction.getConstatAudit());
        liste1.setCategoryConstat(validationAction.getCategoryConstat());
        liste1.setEtatConstat(validationAction.isEtatConstat());
        liste1.setChapitre(validationAction.getChapitre());
        liste1.setLibelle(validationAction.getLibelle());
        liste1.setResponsableCloture(validationAction.getResponsableCloture());
        liste1.setTypeConstat(validationAction.getTypeConstat());
        liste1.setRisque(validationAction.getRisque());
        liste1.setNorme(validationAction.getNorme());
        liste1.setDateClotureConstat(validationAction.getDateClotureConstat());

        return  constatAuditRepository.save(liste1);
    }

    public ResponseEntity<Object> deleteConstatAuditById(long action1Id) {
        if (! constatAuditRepository.existsById(action1Id)) {
            throw new ResourceNotFoundException("validationAction with id " + action1Id + " not found");
        }
        constatAuditRepository.deleteById(action1Id);
        return ResponseEntity.ok().build();
    }


}

