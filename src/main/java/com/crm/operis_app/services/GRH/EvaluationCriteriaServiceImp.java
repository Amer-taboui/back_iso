package com.crm.operis_app.services.GRH;
import com.crm.operis_app.exception.ResourceNotFoundException;
import com.crm.operis_app.model.GRH.EvaluationCriteria;
import com.crm.operis_app.model.GRH.Participant;
import com.crm.operis_app.repository.GRH.EvaluationCriteriaRepository;
import com.crm.operis_app.repository.GRH.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class EvaluationCriteriaServiceImp {
    @Autowired
    EvaluationCriteriaRepository evaluationCriteriaRepository;
    @Autowired
    ParticipantRepository participantRepository;

    public List<EvaluationCriteria> getEvaluationCriteria() {
        return evaluationCriteriaRepository.findAll();
    }

    public Optional<EvaluationCriteria> getEvaluationCriteriaById(Long evaluationCriteriaId) {
        if (!evaluationCriteriaRepository.existsById(evaluationCriteriaId)) {
            throw new ResourceNotFoundException("evaluationCriteria with id " + evaluationCriteriaId + " not found");
        }
        return evaluationCriteriaRepository.findById(evaluationCriteriaId);
    }

    public EvaluationCriteria createEvaluationCriteria(Long participantId, EvaluationCriteria evaluationCriteria) {
        Set<EvaluationCriteria> evaluation = new HashSet<>();

        Optional<Participant> byId = participantRepository.findById(participantId);
        if (!byId.isPresent()) {
            throw new org.springframework.data.rest.webmvc.ResourceNotFoundException("participant with id " + participantId + " does not exist");
        }
        Participant liste_action = byId.get();
        evaluationCriteria.setParticipant(liste_action);

        EvaluationCriteria Evaluation = this.evaluationCriteriaRepository.save(evaluationCriteria);
        evaluation.add(Evaluation);
        return Evaluation;

    }


    public EvaluationCriteria updateEvaluationCriteriaById(Long evaluationCriteriaId, EvaluationCriteria postRequest) {
        if (!evaluationCriteriaRepository.existsById(evaluationCriteriaId)) {
            throw new ResourceNotFoundException("evaluationCriteria with id " + evaluationCriteriaId + " not found");
        }
        Optional<EvaluationCriteria> posteListe = evaluationCriteriaRepository.findById(evaluationCriteriaId);
        if (!posteListe.isPresent()) {
            throw new ResourceNotFoundException("evaluationCriteria with id " + evaluationCriteriaId + " not found");
        }
        EvaluationCriteria post1 = posteListe.get();
        post1.setCriteria(postRequest.getCriteria());
        post1.setComment(postRequest.getComment());
        post1.setEvaluationModel(postRequest.getEvaluationModel());
        post1.setPonderation(postRequest.getPonderation());
        post1.setScore(postRequest.getScore());

        return evaluationCriteriaRepository.save(post1);
    }
    public ResponseEntity<Object> deleteEvaluationCriteriaById(Long evaluationCriteriaId, Boolean isDelete) {
        Optional<EvaluationCriteria> PosteListe = evaluationCriteriaRepository.findById(evaluationCriteriaId);
        if (!PosteListe.isPresent()) {
            throw new ResourceNotFoundException("evaluationCriteria with id " + evaluationCriteriaId + " not found");
        }
        EvaluationCriteria evaluationCriteria = PosteListe.get();

        evaluationCriteriaRepository.save(evaluationCriteria);
        return ResponseEntity.ok().build();
    }
}
