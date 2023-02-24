package com.crm.operis_app.services.GRH;

import com.crm.operis_app.model.GRH.Personal;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface PersonnelService {
    List<Personal> getListPersonnels();
    List<Personal> getArchivedListPersonnels();
    Optional<Personal> getPersonnelById(Long personnelId);
    Personal createPersonnel(Personal personnel);
    Personal updatePersonnelById(Long personnelId, Personal PersonnelRequest);
    void addPersonnelToUser(Long userId, Long personnelId);
    ResponseEntity<Object> deletePersonnelById(Long personnelId, Boolean isDelete);
    void addParticipantToFormation(Long formationId, Long personnelId);
    void addPersonalPost(Long personalId, Long postId);
    void removePersonalPost(Long personalId, Long postId);
    void addPersonalFormation(Long formationId, Long personalId);
    void removePersonalFormation(Long personalId, Long formationId);
    List<Map<String,Object>>getPersonals();
    void addPersonalAudit(Long auditId, Long personalId);
    void removePersonalAudit(Long personalId, Long auditId);
   void addPersonalPlanAction(Long planId, Long personalId);
    void addPersonalValidationAction(Long validationId, Long personnelId);
    void addPersonalEtatAction(Long etatId, Long personnelId);
    void addPersonalAnalyseReclamation(Long analyseId, Long personnelId);
    void addPersonalClotureReclamation(Long clotureId, Long personnelId);
    void addPersonalCreationReclamation(Long crationId, Long personnelId);

}
