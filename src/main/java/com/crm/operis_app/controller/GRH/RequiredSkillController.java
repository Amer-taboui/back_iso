package com.crm.operis_app.controller.GRH;

import com.crm.operis_app.model.GRH.EvaluationCriteria;
import com.crm.operis_app.model.GRH.Post;
import com.crm.operis_app.model.GRH.requiredSkill;
import com.crm.operis_app.services.GRH.RequiredSkillServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class RequiredSkillController {
    @Autowired
    RequiredSkillServiceImp requiredSkillServiceImp;

    @RequestMapping(value = "/requiredSkill", method = RequestMethod.GET)
    public List<requiredSkill> getAllRequiredSkill() {
        return requiredSkillServiceImp.getRequiredSkill();
    }


    @RequestMapping(value = "requiredSkill/{posteListeId}", method = RequestMethod.GET)
    public Optional<requiredSkill> getRequiredSkillById(@PathVariable(value = "posteListeId") Long requiredSkillId) {
        return requiredSkillServiceImp.getRequiredSkillById(requiredSkillId);
    }

    @PostMapping(value = "skill/{skillId}/requiredSkill",  consumes = MediaType.APPLICATION_JSON_VALUE)
    public requiredSkill createRequiredSkillToSkill(@PathVariable(value = "skillId") Long skillId, @RequestBody requiredSkill required) {
        return requiredSkillServiceImp.createRequiredSkill(skillId, required);
    }

    @RequestMapping(value = "/requiredSkill/{posteListeId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public requiredSkill updateRequiredSkill(@PathVariable(value = "posteListeId") Long requiredSkillId, @RequestBody requiredSkill post) {
        return requiredSkillServiceImp.updateRequiredSkillById(requiredSkillId, post);
    }


    @RequestMapping(value = "/requiredSkill/{requiredSkillId}/{isDelete}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteRequiredSkillById(@PathVariable(value = "requiredSkillId") Long requiredSkillId, @PathVariable(value = "isDelete") Boolean isDelete) {
        return requiredSkillServiceImp.deleteRequiredSkillById(requiredSkillId, isDelete);
    }
}
