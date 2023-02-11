package com.crm.operis_app.controller.audit;

import com.crm.operis_app.model.audit.ListeAudit;
import com.crm.operis_app.services.audit.ListeAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins="*", allowedHeaders="*")
@RestController
@RequestMapping("/api")
public class ListeAuditController {
    @Autowired
    ListeAuditService listeAuditService;

    @RequestMapping(value = "/getAll_Liste_audit", method = RequestMethod.GET)
    public List<ListeAudit> getAllListeAudit() {
        return listeAuditService.getAllListeAudit();
    }

    @PostMapping(value = "/liste_audit",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ListeAudit createListe(@RequestBody ListeAudit liste_audit) {
        return listeAuditService.createListe(liste_audit);
    }

    @GetMapping(value = "/liste_audit/{listeId}")
    public Optional<ListeAudit> getListeById(@PathVariable(value = "listeId") Long authorId) {
        return listeAuditService.getListeById(authorId);
    }

    @PutMapping(value = "/liste_audit/{listeId}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ListeAudit updateListe(@PathVariable(value = "listeId") Long listeId, @RequestBody ListeAudit liste_audit) {
        return listeAuditService.updateListeById(listeId, liste_audit);
    }

    @DeleteMapping(value = "/archiveAudit/{liste_auditId}/{isDelete}")
    public ResponseEntity<Object> archiveAuditById(@PathVariable(value = "liste_auditId") Long liste_auditId, @PathVariable(value = "isDelete") Boolean isDelete) {
        return listeAuditService.archiveAuditById(liste_auditId,isDelete);
    }

    @DeleteMapping(value = "/liste_audit/{listeId}")
    public ResponseEntity<Object> deleteListeById(@PathVariable(value = "listeId") Long listeId) {
        return listeAuditService.deleteListeById(listeId);
    }

}

