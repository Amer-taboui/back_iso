package com.crm.operis_app.controller.GRH;

import com.crm.operis_app.model.GRH.Formation;
import com.crm.operis_app.services.GRH.FormationServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")

public class FormationController {
    @Autowired
    FormationServiceImp formationServiceImp;

    @RequestMapping(value = "/formation", method = RequestMethod.GET)
    public List<Formation> getAllFormation() {
        return formationServiceImp.getFormation();
    }

    @RequestMapping(value = "/archivedFormation", method = RequestMethod.GET)
    public List<Formation> getArchivedFormation() {
        return formationServiceImp.getArchivedFormation();
    }

    @RequestMapping(value = "formation/{formationId}", method = RequestMethod.GET)
    public Optional<Formation> getPosteListeById(@PathVariable(value = "formationId") Long formationId) {
        return formationServiceImp.getformationById(formationId);
    }

    @RequestMapping(value = "/createFormation", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Formation createFormation(@RequestBody Formation formation) {
        return formationServiceImp.createformation(formation);
    }

    @RequestMapping(value = "/formation/{formationId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Formation updatePosteListe(@PathVariable(value = "formationId") Long formationId, @RequestBody Formation formation) {
        return formationServiceImp.updateFormationById(formationId, formation);
    }



    @DeleteMapping(value = "/formation/{id}")
    public ResponseEntity<Object> deleteFormationById(@PathVariable(value = "id") Long id) {
        return formationServiceImp.deleteFormationById(id);
    }
//----------------------------FormationRequirement--------------------------------//
    @PostMapping(value = "add/formation/{formationId}/post/{postId}")
    public void addFormationRequirement(@PathVariable(value = "formationId") Long formationId, @PathVariable(value = "postId") Long postId) {
        formationServiceImp.addFormationRequirement(formationId, postId);
    }

    @PostMapping(value = "remove/formation/{formationId}/post/{postId}")
    public void removeFormationRequirement(@PathVariable(value = "formationId") Long formationId, @PathVariable(value = "postId") Long postId) {
        formationServiceImp.removeFormationRequirement(formationId, postId);
    }
//-----------------------------FormationParticipant-----------------------------------//
    @PostMapping(value = "add/formation/{formationId}/participant/{participantId}")
    public void addFormationParticipant(@PathVariable(value = "formationId") Long formationId, @PathVariable(value = "participantId") Long participantId) {
    formationServiceImp.addFormationParticipant(formationId, participantId);
    }

    @PostMapping(value = "remove/formation/{formationId}/participant/{participantId}")
    public void removeFormationParticipant(@PathVariable(value = "formationId") Long formationId, @PathVariable(value = "participantId") Long participantId) {
        formationServiceImp.removeFormationParticipant(formationId, participantId);
    }
}
