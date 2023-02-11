package com.crm.operis_app.services.GRH;

import com.crm.operis_app.model.GRH.Formation;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface FormationService {
    List<Formation> getFormation();
    List<Formation> getArchivedFormation();
    Optional<Formation> getformationById(Long formationId);
    Formation createformation(Formation formation);
    Formation updateFormationById(Long formationId, Formation FormationRequest);
    ResponseEntity<Object> deleteFormationById(Long formationId);
    void addFormationRequirement(Long formationId, Long postId);
    void removeFormationRequirement(Long formationId, Long postId);
    void addFormationParticipant(Long formationId, Long participantId);
    void removeFormationParticipant(Long formationId, Long participantId);
}
