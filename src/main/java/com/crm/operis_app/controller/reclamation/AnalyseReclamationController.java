package com.crm.operis_app.controller.reclamation;

import com.crm.operis_app.model.reclamation.AnalyseReclamation;
import com.crm.operis_app.services.reclamation.AnalyseReclamationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins="*", allowedHeaders="*")
@RestController
@RequestMapping("/api")
public class AnalyseReclamationController {
    @Autowired
    AnalyseReclamationService analyseReclamationService;


    @GetMapping(value = "/getAnalyseReclamation")
    public List<AnalyseReclamation> getAnalyseReclamation() {
        return analyseReclamationService.getAnalyseReclamation();
    }


    @GetMapping(value = "/getAnalyseReclamationById/{analyseReclamationId}")
    public Optional<AnalyseReclamation> getAnalyseReclamationById(@PathVariable(value = "analyseReclamationId") Long authorId) {
        return analyseReclamationService.getAnalyseReclamationById(authorId);
    }


    @PostMapping(value = "listeReclamation/{listeReclamationId}/analyseReclamation",  consumes = MediaType.APPLICATION_JSON_VALUE)
    public AnalyseReclamation createEtatAction(@PathVariable(value = "listeReclamationId") Long listeActionId, @RequestBody AnalyseReclamation etatAction) {
        return analyseReclamationService.createAnalyseReclamation(listeActionId, etatAction);
    }


    @PutMapping(value = "/updateAnalyseReclamationById/{analyseReclamationId}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public AnalyseReclamation updateEtatActionById(@PathVariable(value = "analyseReclamationId") Long listeId, @RequestBody AnalyseReclamation etatAction) {
        return analyseReclamationService.updateAnalyseReclamationById(listeId, etatAction);
    }

    @DeleteMapping(value = "/deleteAnalyseReclamation/{analyseReclamationId}")
    public ResponseEntity<Object> deleteAnalyseReclamationById(@PathVariable(value = "analyseReclamationId") long listeId) {
        return analyseReclamationService.deleteAnalyseReclamationById(listeId);
    }
    @GetMapping(value = "/analyseReclamationPersonnelIds/reclamation/{reclamationId}")
    public List<BigInteger> getPersonnelIdListByReclamation(@PathVariable(value = "reclamationId") Long reclamationId) {
        return analyseReclamationService.getPersonnelIdListByReclamation(reclamationId);
    }

}