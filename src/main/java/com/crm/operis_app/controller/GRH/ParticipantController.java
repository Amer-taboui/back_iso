package com.crm.operis_app.controller.GRH;

import com.crm.operis_app.model.GRH.EvaluationCriteria;
import com.crm.operis_app.model.GRH.Participant;
import com.crm.operis_app.model.GRH.Post;
import com.crm.operis_app.services.GRH.ParticipantService;
import com.crm.operis_app.services.GRH.ParticipantServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class ParticipantController {
    @Autowired
    ParticipantService participantServiceImp;
    @RequestMapping(value = "/participant", method = RequestMethod.GET)
    public List<Participant> getAllParticipant() {
        return participantServiceImp.getParticipant();
    }


    @RequestMapping(value = "participant/{participantId}", method = RequestMethod.GET)
    public Optional<Participant> getParticipantById(@PathVariable(value = "posteListeId") Long participantId) {
        return participantServiceImp.getParticipantById(participantId);
    }

    @PostMapping(value = "personal/{personalId}/Participant",  consumes = MediaType.APPLICATION_JSON_VALUE)
    public Participant createParticipant(@PathVariable(value = "personalId") Long personalId, @RequestBody Participant participant) {
        return participantServiceImp.createParticipantToPersonal(personalId, participant);
    }


    @RequestMapping(value = "/participant/{participantId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Participant updateParticipant(@PathVariable(value = "posteListeId") Long participantId, @RequestBody Participant participant) {
        return participantServiceImp.updateParticipantById(participantId, participant);
    }


    @RequestMapping(value = "/participant/{participantId}/{isDelete}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteParticipantById(@PathVariable(value = "posteListeId") Long participantId, @PathVariable(value = "isDelete") Boolean isDelete) {
        return participantServiceImp.deleteParticipantById(participantId, isDelete);
    }
}
