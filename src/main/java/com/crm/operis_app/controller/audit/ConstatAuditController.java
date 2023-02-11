package com.crm.operis_app.controller.audit;

import com.crm.operis_app.model.audit.ConstatAudit;
import com.crm.operis_app.services.audit.ConstatAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins="*", allowedHeaders="*")
@RestController
@RequestMapping("/api")
public class ConstatAuditController {
    @Autowired
    ConstatAuditService constatAuditService;


    @GetMapping(value = "/getConstatAudit")
    public List<ConstatAudit> getConstatAudit() {
        return constatAuditService.getConstatAudit();
    }


    @GetMapping(value = "/ConstatAudit/{constatAuditId}")
    public Optional<ConstatAudit> getConstatAuditById(@PathVariable(value = "constatAuditId") Long authorId) {
        return constatAuditService.getConstatAuditById(authorId);
    }


    @PostMapping(value = "listeAudit/{listeAuditId}/ConstatAudit",  consumes = MediaType.APPLICATION_JSON_VALUE)
    public ConstatAudit createConstatAudit(@PathVariable(value = "listeAuditId") Long listeActionId, @RequestBody ConstatAudit constatAudit) {
        return constatAuditService.createConstatAudit(listeActionId, constatAudit);
    }


    @PutMapping(value = "/updateConstatAudit/{constatAuditId}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ConstatAudit updateConstatAuditById(@PathVariable(value = "constatAuditId") Long listeId, @RequestBody ConstatAudit validationAction) {
        return constatAuditService.updateConstatAuditById(listeId, validationAction);
    }

    @DeleteMapping(value = "/deleteConstatAudit/{constatAuditId}")
    public ResponseEntity<Object> deleteValidationActionById(@PathVariable(value = "constatAuditId") long listeId) {
        return constatAuditService.deleteConstatAuditById(listeId);
    }

}
