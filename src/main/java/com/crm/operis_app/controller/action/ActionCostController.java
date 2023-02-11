package com.crm.operis_app.controller.action;


import com.crm.operis_app.model.action.utils.ActionCost;
import com.crm.operis_app.services.action.ActionCostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins="*", allowedHeaders="*")
@RestController
@RequestMapping("/api")

public class ActionCostController {

    @Autowired
    ActionCostService actionCostService;

    @RequestMapping(value = "/actionCost/{id}", method = RequestMethod.GET)
    public Optional<ActionCost> getActionCostById(@PathVariable(value = "id") Long id) {
        return actionCostService.getActionCostById(id);
    }


    @RequestMapping(value = "/actionCorrection/{actionCorrectionId}/cost", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ActionCost createActionCorrectionCost(@PathVariable(value = "actionCorrectionId") Long actionCorrectionId, @RequestBody ActionCost actionCost) {
        return actionCostService.createActionCorrectionCost(actionCorrectionId,actionCost);
    }




    @PutMapping(value = "/actionCost/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ActionCost updateActionCostById(@PathVariable(value = "id") Long id, @RequestBody ActionCost actionCost) {
        return actionCostService.updateActionCostById(id, actionCost);
    }

    @DeleteMapping(value = "/actionCost/{id}")
    public ResponseEntity<Object> deleteActionCostById(@PathVariable(value = "id") Long id) {
        return actionCostService.deleteActionCostById(id);
    }



//------AMEUR-----------------------------//

}
