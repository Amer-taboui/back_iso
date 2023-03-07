package com.crm.operis_app.controller.NonConformite;

import com.crm.operis_app.model.NonConformite.ValidationNonConformite;
import com.crm.operis_app.model.reclamation.AnalyseReclamation;
import com.crm.operis_app.services.NonConformite.ValidationNonConformiteService;
import com.crm.operis_app.services.reclamation.AnalyseReclamationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins="*", allowedHeaders="*")
@RestController
@RequestMapping("/api")
public class ValidationNonConformiteController {
    @Autowired
    ValidationNonConformiteService validationNonConformiteService;


    @GetMapping(value = "/getValidationNonConformite")
    public List<ValidationNonConformite> getValidationNonConformite() {
        return validationNonConformiteService.getValidationNonConformite();
    }


    @GetMapping(value = "/getValidationNonConformiteById/{validationNonConformiteId}")
    public Optional<ValidationNonConformite> getValidationNonConformiteById(@PathVariable(value = "validationNonConformiteId") Long authorId) {
        return validationNonConformiteService.getValidationNonConformiteById(authorId);
    }


    @PostMapping(value = "listeNonConformite/{listeNonConformiteId}/validationNonConformite",  consumes = MediaType.APPLICATION_JSON_VALUE)
    public ValidationNonConformite createEtatAction(@PathVariable(value = "listeNonConformiteId") Long listeActionId, @RequestBody ValidationNonConformite etatAction) {
        return validationNonConformiteService.createValidationNonConformite(listeActionId, etatAction);
    }


    @PutMapping(value = "/updateValidationNonConformiteById/{validationNonConformiteId}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ValidationNonConformite updateEtatActionById(@PathVariable(value = "validationNonConformiteId") Long listeId, @RequestBody ValidationNonConformite etatAction) {
        return validationNonConformiteService.updateValidationNonConformiteById(listeId, etatAction);
    }

    @DeleteMapping(value = "/deleteValidationNonConformite/{validationNonConformiteId}")
    public ResponseEntity<Object> deleteValidationNonConformiteById(@PathVariable(value = "validationNonConformiteId") long listeId) {
        return validationNonConformiteService.deleteValidationNonConformiteById(listeId);
    }
}
