package com.crm.operis_app.controller.reclamation;

import com.crm.operis_app.model.action.utils.ActionCost;
import com.crm.operis_app.model.reclamation.ReclamationCost;
import com.crm.operis_app.services.action.ActionCostService;
import com.crm.operis_app.services.reclamation.ReclamationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins="*", allowedHeaders="*")
@RestController
@RequestMapping("/api")
public class ReclamationCostController {

    @Autowired
    ReclamationService reclamationService;

    @RequestMapping(value = "/reclamationCost/{id}", method = RequestMethod.GET)
    public Optional<ReclamationCost> getActionCostById(@PathVariable(value = "id") Long id) {
        return reclamationService.getActionCostById(id);
    }


    @RequestMapping(value = "/listeReclamation/{listeReclamationId}/reclamationCost", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ReclamationCost createActionCorrectionCost(@PathVariable(value = "listeReclamationId") Long listeReclamationId, @RequestBody ReclamationCost reclamationCost) {
        return reclamationService.createActionCorrectionCost(listeReclamationId,reclamationCost);
    }



    @PutMapping(value = "/reclamationCost/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ReclamationCost updateActionCostById(@PathVariable(value = "id") Long id, @RequestBody ReclamationCost reclamationCost) {
        return reclamationService.updateActionCostById(id, reclamationCost);
    }

    @DeleteMapping(value = "/reclamationCost/{id}")
    public ResponseEntity<Object> deleteActionCostById(@PathVariable(value = "id") Long id) {
        return reclamationService.deleteActionCostById(id);
    }

}
