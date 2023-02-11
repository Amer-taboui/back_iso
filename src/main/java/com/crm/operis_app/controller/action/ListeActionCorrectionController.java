package com.crm.operis_app.controller.action;
import com.crm.operis_app.model.GRH.Post;
import com.crm.operis_app.model.action.actionCorrection.ListeActionCorrection;
import com.crm.operis_app.services.action.ListeActionCorrectionServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins="*", allowedHeaders="*")
@RestController
@RequestMapping("/api")
public class ListeActionCorrectionController {
    @Autowired
    ListeActionCorrectionServiceImp liste_actionService;

    @RequestMapping(value = "/getAll_Liste_action", method = RequestMethod.GET)
    public List<ListeActionCorrection> getAllListeActionCorrection() {
        return liste_actionService.getAllListeActionCorrection();
    }

    @PostMapping(value = "/liste_action",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ListeActionCorrection createListe(@RequestBody ListeActionCorrection liste_action) {
        return liste_actionService.createListe(liste_action);
    }

    @GetMapping(value = "/liste_action/{listeId}")
    public Optional<ListeActionCorrection> getListeById(@PathVariable(value = "listeId") Long authorId) {
        return liste_actionService.getListeById(authorId);
    }

    @PutMapping(value = "/liste_action/{listeId}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ListeActionCorrection updateListe(@PathVariable(value = "listeId") Long listeId, @RequestBody ListeActionCorrection liste_action) {
        return liste_actionService.updateListeById(listeId, liste_action);
    }

    @DeleteMapping(value = "/archiveActionCorrection/{liste_actionId}/{isDelete}")
    public ResponseEntity<Object> archiveActionCorrectionById(@PathVariable(value = "liste_actionId") Long liste_actionId, @PathVariable(value = "isDelete") Boolean isDelete) {
        return liste_actionService.archiveActionCorrectionById(liste_actionId,isDelete);
    }

    @DeleteMapping(value = "/liste_action/{listeId}")
    public ResponseEntity<Object> deleteListeById(@PathVariable(value = "listeId") Long listeId) {
        return liste_actionService.deleteListeById(listeId);
    }

}
