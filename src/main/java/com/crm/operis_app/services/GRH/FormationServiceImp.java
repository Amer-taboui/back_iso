package com.crm.operis_app.services.GRH;

import com.crm.operis_app.exception.ResourceNotFoundException;
import com.crm.operis_app.model.GRH.Formation;
import com.crm.operis_app.model.GRH.Participant;
import com.crm.operis_app.model.GRH.Post;
import com.crm.operis_app.repository.GRH.FormationRepository;
import com.crm.operis_app.repository.GRH.ParticipantRepository;
import com.crm.operis_app.repository.GRH.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FormationServiceImp implements FormationService{
    @Autowired
    FormationRepository formationRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    ParticipantRepository participantRepository;

@Override
    public List<Formation> getFormation() {
        return formationRepository.findByActiveIsTrueOrderByIdAsc();
    }
@Override
    public List<Formation> getArchivedFormation() {
        return formationRepository.findByActiveIsFalseOrderByIdAsc();
    }

@Override
    public Optional<Formation> getformationById(Long formationId) {
        if (!formationRepository.existsById(formationId)) {
            throw new ResourceNotFoundException("formation with id " + formationId + " not found");
        }
        return formationRepository.findById(formationId);
    }
@Override
    public Formation createformation(Formation formation) {
        return formationRepository.save(formation);
    }

@Override
    public Formation updateFormationById(Long formationId, Formation FormationRequest) {
        if (!formationRepository.existsById(formationId)) {
            throw new ResourceNotFoundException("formation with id " + formationId + " not found");
        }
        Optional<Formation> formation = formationRepository.findById(formationId);
        if (!formation.isPresent()) {
            throw new ResourceNotFoundException("formationId with id " + formationId + " not found");
        }
        Formation formation1 = formation.get();


        formation1.setTypeFormation(FormationRequest.getTypeFormation());
        formation1.setThemeFormation(FormationRequest.getThemeFormation());

    formation1.setCodeFormation(FormationRequest.getCodeFormation());
    formation1.setComment(FormationRequest.getComment());
    formation1.setDuration(FormationRequest.getDuration());
    formation1.setInstructorReport(FormationRequest.getInstructorReport());
    formation1.setInstructorScore(FormationRequest.getInstructorScore());
    formation1.setLocation(FormationRequest.getLocation());
    formation1.setObjectife(FormationRequest.getObjectife());
    formation1.setProvider(FormationRequest.getProvider());
    formation1.setRealizationDate(FormationRequest.getRealizationDate());
    formation1.setRealizationStatus(FormationRequest.getRealizationStatus());
    formation1.setRefund(FormationRequest.getRefund());
    formation1.setValid(FormationRequest.getValid());
    formation1.setValidator(FormationRequest.getValidator());
    formation1.setRefund(FormationRequest.getRefund());
    formation1.setEndRealizationDate(FormationRequest.getEndRealizationDate());
    formation1.setPlannedDate(FormationRequest.getPlannedDate());
    formation1.setPlannedEndDate(FormationRequest.getPlannedEndDate());

    return formationRepository.save(formation1);
    }

@Override

    public ResponseEntity<Object> deleteFormationById(Long formationId) {
        if (!formationRepository.existsById(formationId)) {
            throw new ResourceNotFoundException("formationId with id " + formationId + " not found");
        }
    formationRepository.deleteById(formationId);
        return ResponseEntity.ok().build();
    }
    //-------------------------------FormationRequirement----------------------//
    @Override
    public void addFormationRequirement(Long formationId, Long postId) {
        Optional<Post> personnelById = postRepository.findById(postId);
        if (!personnelById.isPresent()) {
            throw new ResourceNotFoundException("Post with id " + postId + " does not exist");
        }
        Post post = personnelById.get();
        Optional<Formation> formationById = formationRepository.findById(formationId);
        if (!formationById.isPresent()) {
            throw new ResourceNotFoundException("Foramtion with id " + formationId + " does not exist");
        }
        Formation formation = formationById.get();
        formation.getPost().add(post);
        formationRepository.save(formation);
    }

    @Override
    public void removeFormationRequirement(Long formationId, Long postId) {
        Optional<Post> postById = postRepository.findById(postId);
        if (!postById.isPresent()) {
            throw new ResourceNotFoundException("audite with id " + postId + " does not exist");
        }
        Post post = postById.get();

        Optional<Formation> formationById = formationRepository.findById(formationId);
        if (!formationById.isPresent()) {
            throw new ResourceNotFoundException("planningGannt  with id " + formationId + " does not exist");
        }
        Formation formation = formationById.get();
        formation.getPost().remove(post);
        formationRepository.save(formation);
    }
//-------------------------------Formation_Participant-------------------------//
@Override
public void addFormationParticipant(Long formationId, Long participantId) {
    Optional<Participant> participantById = participantRepository.findById(participantId);
    if (!participantById.isPresent()) {
        throw new ResourceNotFoundException("Participant with id " + participantId + " does not exist");
    }
    Participant participant = participantById.get();
    Optional<Formation> formationById = formationRepository.findById(formationId);
    if (!formationById.isPresent()) {
        throw new ResourceNotFoundException("Foramtion with id " + formationId + " does not exist");
    }
    Formation formation = formationById.get();
    formation.getParticipant().add(participant);
    formationRepository.save(formation);
}

    @Override
    public void removeFormationParticipant(Long formationId, Long participantId) {
        Optional<Participant> participantById = participantRepository.findById(participantId);
        if (!participantById.isPresent()) {
            throw new ResourceNotFoundException("Participant with id " + participantId + " does not exist");
        }
        Participant participant = participantById.get();

        Optional<Formation> formationById = formationRepository.findById(formationId);
        if (!formationById.isPresent()) {
            throw new ResourceNotFoundException("Formation  with id " + formationId + " does not exist");
        }
        Formation formation = formationById.get();
        formation.getParticipant().remove(participant);
        formationRepository.save(formation);
    }
}
