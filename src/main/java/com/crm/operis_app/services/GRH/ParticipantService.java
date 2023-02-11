package com.crm.operis_app.services.GRH;

import com.crm.operis_app.model.GRH.Participant;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ParticipantService {
    List<Participant> getParticipant();
    Optional<Participant> getParticipantById(Long participantId);
    Participant createParticipantToPersonal(Long personalId, Participant participant);
    Participant updateParticipantById(Long participantId, Participant postRequest);
    ResponseEntity<Object> deleteParticipantById(Long participantId, Boolean isDelete);
}
