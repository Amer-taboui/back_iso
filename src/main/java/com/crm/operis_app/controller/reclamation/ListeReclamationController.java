package com.crm.operis_app.controller.reclamation;

import com.crm.operis_app.model.reclamation.ListeReclamation;
import com.crm.operis_app.services.reclamation.ListeReclamationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class ListeReclamationController {

    @Autowired
    ListeReclamationService listeReclamationService;


    @RequestMapping(value = "/reclamation", method = RequestMethod.GET)
    public List<ListeReclamation> getAllReclamation() {
        return listeReclamationService.getReclamation();
    }

    @RequestMapping(value = "/archivedReclamation", method = RequestMethod.GET)
    public List<ListeReclamation> getArchivedReclamation() {
        return listeReclamationService.getArchivedReclamation();
    }

    @RequestMapping(value = "reclamation/{reclamationId}", method = RequestMethod.GET)
    public Optional<ListeReclamation> getReclamationById(@PathVariable(value = "reclamationId") Long reclamationId) {
        return listeReclamationService.getReclamationById(reclamationId);
    }

    @RequestMapping(value = "/createReclamation", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ListeReclamation createReclamation(@RequestBody ListeReclamation listeReclamation) {
        return listeReclamationService.createReclamation(listeReclamation);
    }

    @RequestMapping(value = "/reclamation/{reclamationId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ListeReclamation updateReclamationById(@PathVariable(value = "reclamationId") Long reclamationId, @RequestBody ListeReclamation listeReclamation) {
        return listeReclamationService.updateReclamationById(reclamationId, listeReclamation);
    }


    @DeleteMapping(value = "/reclamation/{id}")
    public ResponseEntity<Object> deleteReclamationById(@PathVariable(value = "id") Long id) {
        return listeReclamationService.deleteReclamationById(id);
    }


}
