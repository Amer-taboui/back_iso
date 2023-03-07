package com.crm.operis_app.controller.NonConformite;

import com.crm.operis_app.model.NonConformite.ListeNonConformite;
import com.crm.operis_app.services.NonConformite.ListeNonConformiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class ListeNonConformiteController {
    @Autowired
    ListeNonConformiteService listeNonConformiteService;


    @RequestMapping(value = "/NonConformite", method = RequestMethod.GET)
    public List<ListeNonConformite> getListeNonConformite() {
        return listeNonConformiteService.getListeNonConformite();
    }

    @RequestMapping(value = "/archivedNonConformite", method = RequestMethod.GET)
    public List<ListeNonConformite> getArchivedListeNonConformite() {
        return listeNonConformiteService.getArchivedListeNonConformite();
    }

    @RequestMapping(value = "nonConformite/{nonConformiteId}", method = RequestMethod.GET)
    public Optional<ListeNonConformite> getListeNonConformiteById(@PathVariable(value = "nonConformiteId") Long reclamationId) {
        return listeNonConformiteService.getListeNonConformiteById(reclamationId);
    }

    @RequestMapping(value = "/createNonConformite", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ListeNonConformite createListeNonConformite(@RequestBody ListeNonConformite listeReclamation) {
        return listeNonConformiteService.createListeNonConformite(listeReclamation);
    }

    @RequestMapping(value = "/NonConformite/{nonConformiteId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ListeNonConformite updateListeNonConformiteById(@PathVariable(value = "nonConformiteId") Long reclamationId, @RequestBody ListeNonConformite listeReclamation) {
        return listeNonConformiteService.updateListeNonConformiteById(reclamationId, listeReclamation);
    }

    @DeleteMapping(value = "/NonConformite/{id}")
    public ResponseEntity<Object> deleteListeNonConformiteById(@PathVariable(value = "id") Long id) {
        return listeNonConformiteService.deleteListeNonConformiteById(id);
    }



}

