package com.crm.operis_app.controller.action;

import com.crm.operis_app.model.action.actionCorrection.EtatAction;
import com.crm.operis_app.model.action.actionCorrection.ValidationAction;
import com.crm.operis_app.services.action.EtatActionService;
import com.crm.operis_app.services.action.ValidationActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins="*", allowedHeaders="*")
@RestController
@RequestMapping("/api")
public class EtatActionController {


    @Autowired
    EtatActionService etatActionService;


    @GetMapping(value = "/getEtatAction")
    public List<EtatAction> getEtatAction() {
        return etatActionService.getEtatAction();
    }


    @GetMapping(value = "/getEtatActionById/{etatActionId}")
    public Optional<EtatAction> getEtatActionById(@PathVariable(value = "etatActionId") Long authorId) {
        return etatActionService.getEtatActionById(authorId);
    }


    @PostMapping(value = "listeAction/{listeActionId}/etatAction",  consumes = MediaType.APPLICATION_JSON_VALUE)
    public EtatAction createEtatAction(@PathVariable(value = "listeActionId") Long listeActionId, @RequestBody EtatAction etatAction) {
        return etatActionService.createEtatAction(listeActionId, etatAction);
    }


    @PutMapping(value = "/updateEtatActionById/{etatActionId}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public EtatAction updateEtatActionById(@PathVariable(value = "etatActionId") Long listeId, @RequestBody EtatAction etatAction) {
        return etatActionService.updateEtatActionById(listeId, etatAction);
    }

    @DeleteMapping(value = "/deleteEtatAction/{etatActionId}")
    public ResponseEntity<Object> deleteEtatActionById(@PathVariable(value = "etatActionId") long listeId) {
        return etatActionService.deleteEtatActionById(listeId);
    }


}
