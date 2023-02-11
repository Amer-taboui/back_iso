package com.crm.operis_app.services.GRH;

import com.crm.operis_app.model.GRH.requiredSkill;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface RequiredSkillService {
    List<requiredSkill> getRequiredSkill();
    Optional<requiredSkill> getRequiredSkillById(Long requiredSkillId);
    requiredSkill createRequiredSkill(Long skillId, requiredSkill required);
    requiredSkill updateRequiredSkillById(Long requiredSkillId, requiredSkill required);
    ResponseEntity<Object> deleteRequiredSkillById(Long requiredSkillId, Boolean isDelete);
}
