package com.crm.operis_app.controller.NonConformite;

import com.crm.operis_app.model.NonConformite.ClotureNonConformite;
import com.crm.operis_app.model.reclamation.AnalyseReclamation;
import com.crm.operis_app.services.NonConformite.ClotureNonConformiteService;
import com.crm.operis_app.services.NonConformite.ValidationNonConformiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins="*", allowedHeaders="*")
@RestController
@RequestMapping("/api")
public class ClotureNonConformiteController {
    @Autowired
    ClotureNonConformiteService clotureNonConformiteService;


    @GetMapping(value = "/getClotureNonConformite")
    public List<ClotureNonConformite> getClotureNonConformite() {
        return clotureNonConformiteService.getClotureNonConformite();
    }


    @GetMapping(value = "/getClotureNonConformiteById/{clotureNonConformiteId}")
    public Optional<ClotureNonConformite> getClotureNonConformiteById(@PathVariable(value = "clotureNonConformiteId") Long authorId) {
        return clotureNonConformiteService.getClotureNonConformiteById(authorId);
    }


    @PostMapping(value = "listeNonConformite/{listeNonConformiteId}/clotureNonConformite",  consumes = MediaType.APPLICATION_JSON_VALUE)
    public ClotureNonConformite createEtatAction(@PathVariable(value = "listeNonConformiteId") Long listeActionId, @RequestBody ClotureNonConformite etatAction) {
        return clotureNonConformiteService.createClotureNonConformite(listeActionId, etatAction);
    }


    @PutMapping(value = "/updateClotureNonConformiteById/{clotureNonConformiteId}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ClotureNonConformite updateEtatActionById(@PathVariable(value = "clotureNonConformiteId") Long listeId, @RequestBody ClotureNonConformite etatAction) {
        return clotureNonConformiteService.updateClotureNonConformiteById(listeId, etatAction);
    }

    @DeleteMapping(value = "/deleteClotureNonConformite/{clotureNonConformiteId}")
    public ResponseEntity<Object> deleteClotureNonConformiteById(@PathVariable(value = "clotureNonConformiteId") long listeId) {
        return clotureNonConformiteService.deleteClotureNonConformiteById(listeId);
    }
}
