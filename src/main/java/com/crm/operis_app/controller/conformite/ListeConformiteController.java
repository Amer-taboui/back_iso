package com.crm.operis_app.controller.conformite;

import com.crm.operis_app.model.conformite.ListeConformite;
import com.crm.operis_app.services.conformite.ListeConformiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class ListeConformiteController {
    @Autowired
    ListeConformiteService listeConformiteService;



    @RequestMapping(value = "/conformite", method = RequestMethod.GET)
    public List<ListeConformite> getListeConformite() {
        return listeConformiteService.getListeConformite();
    }

    @RequestMapping(value = "/archivedConformite", method = RequestMethod.GET)
    public List<ListeConformite> getArchivedListeConformite() {
        return listeConformiteService.getArchivedListeConformite();
    }

    @RequestMapping(value = "conformite/{conformiteId}", method = RequestMethod.GET)
    public Optional<ListeConformite> getListeConformiteById(@PathVariable(value = "conformiteId") Long reclamationId) {
        return listeConformiteService.getListeConformiteById(reclamationId);
    }

    @RequestMapping(value = "/createConformite", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ListeConformite createListeConformite(@RequestBody ListeConformite listeReclamation) {
        return listeConformiteService.createListeConformite(listeReclamation);
    }

    @RequestMapping(value = "/conformite/{conformiteId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ListeConformite updateListeConformiteById(@PathVariable(value = "conformiteId") Long reclamationId, @RequestBody ListeConformite listeReclamation) {
        return listeConformiteService.updateListeConformiteById(reclamationId, listeReclamation);
    }

    @DeleteMapping(value = "/conformite/{id}")
    public ResponseEntity<Object> deleteListeConformiteById(@PathVariable(value = "id") Long id) {
        return listeConformiteService.deleteListeConformiteById(id);
    }

}
