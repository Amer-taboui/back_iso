package com.crm.operis_app.controller.audit;

import com.crm.operis_app.model.audit.Audite;
import com.crm.operis_app.model.audit.Auditeur;
import com.crm.operis_app.services.audit.AuditeService;
import com.crm.operis_app.services.audit.AuditeurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins="*", allowedHeaders="*")
@RestController
@RequestMapping("/api")
public class AuditeurController {
    @Autowired
    AuditeurService auditeService;



    @GetMapping(value = "/getAuditeur")
    public List<Auditeur> getAuditeur() {
        return auditeService.getAllAuditeur();
    }


    @GetMapping(value = "/auditeur/{auditeurId}")
    public Optional<Auditeur> getAuditeurById(@PathVariable(value = "auditeurId") Long authorId) {
        return auditeService.getAuditeurById(authorId);
    }

    @PostMapping(value = "listeAudit/{listeAuditId}/auditeur",  consumes = MediaType.APPLICATION_JSON_VALUE)
    public Auditeur createAuditeur(@PathVariable(value = "listeAuditId") Long listeActionId, @RequestBody Auditeur audit) {
        return auditeService.createAuditeur(listeActionId, audit);
    }

    @PutMapping(value = "/updateAuditeur/{auditeurId}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Auditeur updateAuditeurById(@PathVariable(value = "auditeurId") Long listeId, @RequestBody Auditeur validationAction) {
        return auditeService.updateAuditeurById(listeId, validationAction);
    }

    @DeleteMapping(value = "/deleteAuditeur/{auditeurId}")
    public ResponseEntity<Object> deleteAuditeurById(@PathVariable(value = "auditeurId") long listeId) {
        return auditeService.deleteAuditeurById(listeId);
    }
}
