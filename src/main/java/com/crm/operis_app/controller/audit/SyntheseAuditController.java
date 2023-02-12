package com.crm.operis_app.controller.audit;

import com.crm.operis_app.model.audit.SyntheseAudit;
import com.crm.operis_app.services.audit.SyntheseAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins="*", allowedHeaders="*")
@RestController
@RequestMapping("/api")
public class SyntheseAuditController {

    @Autowired
    SyntheseAuditService constatAuditService;


    @GetMapping(value = "/getSyntheseAudit")
    public List<SyntheseAudit> getSyntheseAudit() {
        return constatAuditService.getSyntheseAudit();
    }


    @GetMapping(value = "/SyntheseAudit/{constatAuditId}")
    public Optional<SyntheseAudit> getSyntheseAuditById(@PathVariable(value = "constatAuditId") Long authorId) {
        return constatAuditService.getSyntheseAuditById(authorId);
    }


    @PostMapping(value = "listeAudit/{listeAuditId}/SyntheseAudit",  consumes = MediaType.APPLICATION_JSON_VALUE)
    public SyntheseAudit createSyntheseAudit(@PathVariable(value = "listeAuditId") Long listeActionId, @RequestBody SyntheseAudit constatAudit) {
        return constatAuditService.createSyntheseAudit(listeActionId, constatAudit);
    }


    @PutMapping(value = "/updateSyntheseAudit/{constatAuditId}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public SyntheseAudit updateConstatAuditById(@PathVariable(value = "constatAuditId") Long listeId, @RequestBody SyntheseAudit validationAction) {
        return constatAuditService.updateSyntheseAuditById(listeId, validationAction);
    }

    @DeleteMapping(value = "/deleteSyntheseAudit/{constatAuditId}")
    public ResponseEntity<Object> deleteSyntheseAuditById(@PathVariable(value = "constatAuditId") long listeId) {
        return constatAuditService.deleteSyntheseAuditById(listeId);
    }

}

