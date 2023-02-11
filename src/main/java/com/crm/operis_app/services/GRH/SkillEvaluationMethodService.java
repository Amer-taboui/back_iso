package com.crm.operis_app.services.GRH;

import com.crm.operis_app.model.GRH.SkillEvaluationMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface SkillEvaluationMethodService {
    List<SkillEvaluationMethod> getSkillEvaluationMethod();
    Optional<SkillEvaluationMethod> getSkillEvaluationMethodById(Long skillEvaluationMethodId);
  //  List<SkillEvaluationMethod> getAllEvaluationMethodBySkillId(Long skillId);
    SkillEvaluationMethod createSkillEvaluationMethod(Long skillId, SkillEvaluationMethod required);
    SkillEvaluationMethod updateSkillEvaluationMethodById(Long skillEvaluationMethodId, SkillEvaluationMethod postRequest);
    ResponseEntity<Object> deleteSkillEvaluationMethodById(Long skillEvaluationMethodId, Boolean isDelete);
}
