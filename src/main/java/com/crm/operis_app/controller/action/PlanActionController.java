package com.crm.operis_app.controller.action;

import com.crm.operis_app.model.action.actionCorrection.PlanAction;
import com.crm.operis_app.services.action.PlanActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins="*", allowedHeaders="*")
@RestController
@RequestMapping("/api")
public class PlanActionController {

    @Autowired
    PlanActionService planActionService;


    @GetMapping(value = "/getPlanAction")
    public List<PlanAction> getPlanAction() {
        return planActionService.getPlanAction();
    }


    @GetMapping(value = "/getPlanActionById/{planActionId}")
    public Optional<PlanAction> getPlanActionById(@PathVariable(value = "planActionId") Long authorId) {
        return planActionService.getPlanActionById(authorId);
    }


    @PostMapping(value = "listeAction/{listeActionId}/planAction",  consumes = MediaType.APPLICATION_JSON_VALUE)
    public PlanAction createPlanAction(@PathVariable(value = "listeActionId") Long listeActionId, @RequestBody PlanAction planAction) {
        return planActionService.createPlanAction(listeActionId, planAction);
    }


    @PutMapping(value = "/updatePlanActionById/{planActionId}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public PlanAction updatePlanActionById(@PathVariable(value = "planActionId") Long listeId, @RequestBody PlanAction validationAction) {
        return planActionService.updatePlanActionById(listeId, validationAction);
    }

    @DeleteMapping(value = "/deletePlanAction/{planActionId}")
    public ResponseEntity<Object> deletePlanActionById(@PathVariable(value = "planActionId") long listeId) {
        return planActionService.deletePlanActionById(listeId);
    }


}
