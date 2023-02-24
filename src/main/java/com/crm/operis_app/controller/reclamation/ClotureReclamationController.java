package com.crm.operis_app.controller.reclamation;

import com.crm.operis_app.model.reclamation.ClotureReclamation;
import com.crm.operis_app.services.reclamation.ClotureReclamationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins="*", allowedHeaders="*")
@RestController
@RequestMapping("/api")
public class ClotureReclamationController {
    @Autowired
    ClotureReclamationService clotureReclamationService;


    @GetMapping(value = "/getClotureReclamations")
    public List<ClotureReclamation> getClotureReclamations() {
        return clotureReclamationService.getClotureReclamation();
    }


    @GetMapping(value = "/getClotureReclamation/{clotureReclamationId}")
    public Optional<ClotureReclamation> getClotureReclamationById(@PathVariable(value = "clotureReclamationId") Long authorId) {
        return clotureReclamationService.getClotureReclamationById(authorId);
    }


    @PostMapping(value = "listeReclamation/{listeReclamationId}/clotureReclamation",  consumes = MediaType.APPLICATION_JSON_VALUE)
    public ClotureReclamation createEtatAction(@PathVariable(value = "listeReclamationId") Long listeActionId, @RequestBody ClotureReclamation etatAction) {
        return clotureReclamationService.createClotureReclamation(listeActionId, etatAction);
    }


    @PutMapping(value = "/updateClotureReclamationById/{clotureReclamationId}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ClotureReclamation updateEtatActionById(@PathVariable(value = "clotureReclamationId") Long listeId, @RequestBody ClotureReclamation etatAction) {
        return clotureReclamationService.updateClotureReclamationById(listeId, etatAction);
    }

    @DeleteMapping(value = "/deleteClotureReclamation/{clotureReclamationId}")
    public ResponseEntity<Object> deleteEtatActionById(@PathVariable(value = "clotureReclamationId") long listeId) {
        return clotureReclamationService.deleteClotureReclamationById(listeId);
    }


}