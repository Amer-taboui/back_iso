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
    public List<Map<String,Object>>getPersonals();
}
