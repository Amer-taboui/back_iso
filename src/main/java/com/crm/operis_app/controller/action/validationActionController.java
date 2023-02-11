package com.crm.operis_app.controller.action;

import com.crm.operis_app.model.action.actionCorrection.ValidationAction;
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
public class validationActionController {
    @Autowired
    ValidationActionService validationActionService;


    @GetMapping(value = "/getValidationAction")
    public List<ValidationAction> getValidationAction() {
        return validationActionService.getValidationAction();
    }


    @GetMapping(value = "/validationAction/{validationActionId}")
    public Optional<ValidationAction> getValidationActionById(@PathVariable(value = "validationActionId") Long authorId) {
        return validationActionService.getValidationActionById(authorId);
    }


    @PostMapping(value = "listeAction/{listeActionId}/validationAction",  consumes = MediaType.APPLICATION_JSON_VALUE)
    public ValidationAction createValidationAction(@PathVariable(value = "listeActionId") Long listeActionId, @RequestBody ValidationAction validationAction) {
        return validationActionService.createValidationAction(listeActionId, validationAction);
    }


    @PutMapping(value = "/updateValidationAction/{validationActionId}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ValidationAction updateValidationActionById(@PathVariable(value = "validationActionId") Long listeId, @RequestBody ValidationAction validationAction) {
        return validationActionService.updateValidationActionById(listeId, validationAction);
    }

    @DeleteMapping(value = "/deleteValidationAction/{validationActionId}")
    public ResponseEntity<Object> deleteValidationActionById(@PathVariable(value = "validationActionId") long listeId) {
        return validationActionService.deleteValidationActionById(listeId);
    }


}
