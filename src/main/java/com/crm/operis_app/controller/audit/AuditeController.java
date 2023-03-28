package com.crm.operis_app.controller.audit;

import com.crm.operis_app.model.audit.Audite;
import com.crm.operis_app.model.audit.ConstatAudit;
import com.crm.operis_app.services.audit.AuditeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins="*", allowedHeaders="*")
@RestController
@RequestMapping("/api")
public class AuditeController {
    @Autowired
    AuditeService auditeService;

    @GetMapping(value = "/getAudite")
    public List<Audite> getConstatAudit() {
        return auditeService.getAllAudit();
    }


    @GetMapping(value = "/audite/{auditeId}")
    public Optional<Audite> getConstatAuditById(@PathVariable(value = "auditeId") Long authorId) {
        return auditeService.getAuditeById(authorId);
    }

    @PostMapping(value = "listeAudit/{listeAuditId}/audite",  consumes = MediaType.APPLICATION_JSON_VALUE)
    public Audite createConstatAudit(@PathVariable(value = "listeAuditId") Long listeActionId, @RequestBody Audite audit) {
        return auditeService.createAudite(listeActionId, audit);
    }

    @PutMapping(value = "/updateAudite/{AuditeId}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Audite updateConstatAuditById(@PathVariable(value = "AuditeId") Long listeId, @RequestBody Audite validationAction) {
        return auditeService.updateAuditeById(listeId, validationAction);
    }

    @DeleteMapping(value = "/deleteAudite/{AuditeId}")
    public ResponseEntity<Object> deleteValidationActionById(@PathVariable(value = "AuditeId") long listeId) {
        return auditeService.deleteAuditeById(listeId);
    }

}
