package com.crm.operis_app.services.GRH;

import com.crm.operis_app.exception.ResourceNotFoundException;
import com.crm.operis_app.model.GRH.Participant;
import com.crm.operis_app.model.GRH.Personal;
import com.crm.operis_app.repository.GRH.ParticipantRepository;
import com.crm.operis_app.repository.Utils.PersonnelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ParticipantServiceImp implements ParticipantService {
    @Autowired
    ParticipantRepository participantRepository;
    @Autowired
    PersonnelRepository personnelRepository;

    @Override
    public List<Participant> getParticipant() {
        return participantRepository.findAll();
    }

    @Override
    public Optional<Participant> getParticipantById(Long participantId) {
        if (!participantRepository.existsById(participantId)) {
            throw new ResourceNotFoundException("participant with id " + participantId + " not found");
        }
        return participantRepository.findById(participantId);
    }

    @Override
    public Participant createParticipantToPersonal(Long personalId, Participant participant) {
        Set<Participant> participants = new HashSet<>();

        Optional<Personal> byId = personnelRepository.findById(personalId);
        if (!byId.isPresent()) {
            throw new ResourceNotFoundException("personal with id " + personalId + " does not exist");
        }
        Personal personal = byId.get();
        participant.setPersonal(personal);

        Participant Pr = this.participantRepository.save(participant);
        participants.add(Pr);
        return Pr;

    }

    @Override
    public Participant updateParticipantById(Long participantId, Participant postRequest) {
        if (!participantRepository.existsById(participantId)) {
            throw new ResourceNotFoundException("participant with id " + participantId + " not found");
        }
        Optional<Participant> posteListe = participantRepository.findById(participantId);
        if (!posteListe.isPresent()) {
            throw new ResourceNotFoundException("participant with id " + participantId + " not found");
        }
        Participant post1 = posteListe.get();
        post1.setEfficiencyReport(postRequest.getEfficiencyReport());
        post1.setEfficiencyScore(postRequest.getEfficiencyScore());
        post1.setEvaluationCriteria(postRequest.getEvaluationCriteria());
        return participantRepository.save(post1);
    }

    @Override
    public ResponseEntity<Object> deleteParticipantById(Long participantId, Boolean isDelete) {
        Optional<Participant> PosteListe = participantRepository.findById(participantId);
        if (!PosteListe.isPresent()) {
            throw new ResourceNotFoundException("participantId with id " + participantId + " not found");
        }
        Participant post1 = PosteListe.get();

        participantRepository.save(post1);
        return ResponseEntity.ok().build();
    }
}
