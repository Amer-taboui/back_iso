package com.crm.operis_app.services.GRH;

import com.crm.operis_app.model.GRH.Skill;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface SkillService {
    List<Skill> getCompetences( );
    Skill createCompetences(Skill skill);
    Skill createCompetencesExigees(Long posteId, Skill skill);
    Optional<Skill> getCompetencesExigeesById(Long competencesExigeesId);
    Skill updateCompetencesExigees(Long competencesExigeesId, Skill skillRequest);
    ResponseEntity<Object> deleteCompetencesExigeesById(Long competencesExigeesId);
    void addPersonnalAvailableSkills(Long personnalId, Long skillId);
    void removePersonnalAvailableSkills(Long personalId, Long skillId);
}
