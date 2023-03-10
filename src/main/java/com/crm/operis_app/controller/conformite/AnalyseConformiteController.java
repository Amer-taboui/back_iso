package com.crm.operis_app.controller.conformite;

import com.crm.operis_app.model.conformite.AnalyseConformite;
import com.crm.operis_app.model.reclamation.AnalyseReclamation;
import com.crm.operis_app.services.conformite.AnalyseConformiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class AnalyseConformiteController {
    @Autowired
    AnalyseConformiteService analyseConformiteService;

    @GetMapping(value = "/getAnalyseConformite")
    public List<AnalyseConformite> getAnalyseConformite() {
        return analyseConformiteService.getAnalyseConformite();
    }


    @GetMapping(value = "/getAnalyseConformiteById/{analyseConformiteId}")
    public Optional<AnalyseConformite> getAnalyseConformiteById(@PathVariable(value = "analyseConformiteId") Long authorId) {
        return analyseConformiteService.getAnalyseConformiteById(authorId);
    }


    @PostMapping(value = "listeConformite/{listeConformiteId}/analyseConformite",  consumes = MediaType.APPLICATION_JSON_VALUE)
    public AnalyseConformite createEtatAction(@PathVariable(value = "listeConformiteId") Long listeActionId, @RequestBody AnalyseConformite etatAction) {
        return analyseConformiteService.createAnalyseConformite(listeActionId, etatAction);
    }


    @PutMapping(value = "/updateAnalyseConformiteById/{analyseConformiteId}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public AnalyseConformite updateEtatActionById(@PathVariable(value = "analyseConformiteId") Long listeId, @RequestBody AnalyseConformite etatAction) {
        return analyseConformiteService.updateAnalyseConformiteById(listeId, etatAction);
    }

    @DeleteMapping(value = "/deleteAnalyseConformite/{analyseConformiteId}")
    public ResponseEntity<Object> deleteAnalyseConformiteById(@PathVariable(value = "analyseConformiteId") long listeId) {
        return analyseConformiteService.deleteAnalyseConformiteById(listeId);
    }
    @GetMapping(value = "/analyseConformitePersonnelIds/reclamation/{reclamationId}")
    public List<BigInteger> getPersonnelIdListByConformite(@PathVariable(value = "reclamationId") Long reclamationId) {
        return analyseConformiteService.getPersonnelIdListByConformite(reclamationId);
    }
}
