package com.crm.operis_app.services.GRH;

import com.crm.operis_app.exception.ResourceNotFoundException;
import com.crm.operis_app.model.GRH.Participant;
import com.crm.operis_app.model.GRH.Personal;
import com.crm.operis_app.model.GRH.Skill;
import com.crm.operis_app.model.GRH.requiredSkill;
import com.crm.operis_app.repository.GRH.RequiredSkillRepository;
import com.crm.operis_app.repository.GRH.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RequiredSkillServiceImp implements RequiredSkillService {
    @Autowired
    RequiredSkillRepository requiredSkillRepository;
    @Autowired
    SkillRepository skillRepository;
    @Override
    public List<requiredSkill> getRequiredSkill() {
        return requiredSkillRepository.findAll();
    }
    @Override
    public Optional<requiredSkill> getRequiredSkillById(Long requiredSkillId) {
        if (!requiredSkillRepository.existsById(requiredSkillId)) {
            throw new ResourceNotFoundException("requiredSkill with id " + requiredSkillId + " not found");
        }
        return requiredSkillRepository.findById(requiredSkillId);
    }
    @Override
    public requiredSkill createRequiredSkill(Long skillId, requiredSkill required) {
        Set<requiredSkill> requiredSk = new HashSet<>();

        Optional<Skill> byId = skillRepository.findById(skillId);
        if (!byId.isPresent()) {
            throw new org.springframework.data.rest.webmvc.ResourceNotFoundException("skill with id " + skillId + " does not exist");
        }
        Skill skill = byId.get();
     //   required.setSkill(skill);

        requiredSkill r = this.requiredSkillRepository.save(required);
        requiredSk.add(r);
        return r;

    }
    @Override
    public requiredSkill updateRequiredSkillById(Long requiredSkillId, requiredSkill required) {
        if (!requiredSkillRepository.existsById(requiredSkillId)) {
            throw new ResourceNotFoundException("requiredSkill with id " + requiredSkillId + " not found");
        }
        Optional<requiredSkill> posteListe = requiredSkillRepository.findById(requiredSkillId);
        if (!posteListe.isPresent()) {
            throw new ResourceNotFoundException("requiredSkill with id " + requiredSkillId + " not found");
        }
        requiredSkill r = posteListe.get();
        r.setRequiredLevel(required.getRequiredLevel());
        r.setRequiredScore(required.getRequiredScore());


        return requiredSkillRepository.save(r);
    }
    @Override
    public ResponseEntity<Object> deleteRequiredSkillById(Long requiredSkillId, Boolean isDelete) {
        Optional<requiredSkill> PosteListe = requiredSkillRepository.findById(requiredSkillId);
        if (!PosteListe.isPresent()) {
            throw new ResourceNotFoundException("requiredSkill with id " + requiredSkillId + " not found");
        }
        requiredSkill post1 = PosteListe.get();

        requiredSkillRepository.save(post1);
        return ResponseEntity.ok().build();
    }
}
