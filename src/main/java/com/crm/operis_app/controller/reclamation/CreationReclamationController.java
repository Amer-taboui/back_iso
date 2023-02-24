package com.crm.operis_app.controller.reclamation;

import com.crm.operis_app.model.action.actionCorrection.EtatAction;
import com.crm.operis_app.model.reclamation.ClotureReclamation;
import com.crm.operis_app.model.reclamation.CreationReclamation;
import com.crm.operis_app.services.action.EtatActionService;
import com.crm.operis_app.services.reclamation.ClotureReclamationService;
import com.crm.operis_app.services.reclamation.CreationReclamationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins="*", allowedHeaders="*")
@RestController
@RequestMapping("/api")
public class CreationReclamationController {

    @Autowired
    CreationReclamationService creationReclamationService;


    @GetMapping(value = "/getCreationReclamation")
    public List<CreationReclamation> getClotureReclamations() {
        return creationReclamationService.getCreationReclamation();
    }


    @GetMapping(value = "/getCreationReclamation/{creationReclamationId}")
    public Optional<CreationReclamation> getClotureReclamationById(@PathVariable(value = "creationReclamationId") Long authorId) {
        return creationReclamationService.getCreationReclamationById(authorId);
    }


    @PostMapping(value = "listeReclamation/{listeReclamationId}/creationReclamation",  consumes = MediaType.APPLICATION_JSON_VALUE)
    public CreationReclamation createEtatAction(@PathVariable(value = "listeReclamationId") Long listeActionId, @RequestBody CreationReclamation etatAction) {
        return creationReclamationService.createCreationReclamation(listeActionId, etatAction);
    }


    @PutMapping(value = "/updateCreationReclamation/{creationReclamationId}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public CreationReclamation updateEtatActionById(@PathVariable(value = "creationReclamationId") Long listeId, @RequestBody CreationReclamation etatAction) {
        return creationReclamationService.updateCreationReclamationById(listeId, etatAction);
    }

    @DeleteMapping(value = "/deleteCreationReclamation/{creationReclamationId}")
    public ResponseEntity<Object> deleteEtatActionById(@PathVariable(value = "creationReclamationId") long listeId) {
        return creationReclamationService.deleteCreationReclamationById(listeId);
    }

}