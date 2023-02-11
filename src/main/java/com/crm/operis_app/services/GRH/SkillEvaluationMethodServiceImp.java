package com.crm.operis_app.services.GRH;

import com.crm.operis_app.exception.ResourceNotFoundException;
import com.crm.operis_app.model.GRH.*;
import com.crm.operis_app.repository.GRH.PostRepository;
import com.crm.operis_app.repository.GRH.SkillEvaluationMethodRepository;
import com.crm.operis_app.repository.GRH.SkillRepository;
import com.crm.operis_app.repository.Utils.PersonnelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.*;

@Service
public class SkillEvaluationMethodServiceImp implements SkillEvaluationMethodService {

    @Autowired
    SkillEvaluationMethodRepository skillEvaluationMethodRepository;

    @Autowired
    SkillRepository skillRepository;

    @Autowired
    PersonnelRepository personnelRepository;

    @Autowired
    PostRepository postRepository;
    @Override
    public List<SkillEvaluationMethod> getSkillEvaluationMethod() {
        return skillEvaluationMethodRepository.findAll();
    }

    @Override
    public Optional<SkillEvaluationMethod> getSkillEvaluationMethodById(Long skillEvaluationMethodId) {
        if (!skillEvaluationMethodRepository.existsById(skillEvaluationMethodId)) {
            throw new ResourceNotFoundException("skillEvaluationMethod with id " + skillEvaluationMethodId + " not found");
        }
        return skillEvaluationMethodRepository.findById(skillEvaluationMethodId);
    }

    @Override
    public SkillEvaluationMethod createSkillEvaluationMethod(Long skillId, SkillEvaluationMethod required) {
        Set<SkillEvaluationMethod> SkillEvaluation = new HashSet<>();

        Optional<Skill> byId = skillRepository.findById(skillId);
        if (!byId.isPresent()) {
            throw new org.springframework.data.rest.webmvc.ResourceNotFoundException("skill with id " + skillId + " does not exist");
        }
        Skill skill = byId.get();
        required.setSkill(skill);

        SkillEvaluationMethod skillEvaluation = this.skillEvaluationMethodRepository.save(required);
        SkillEvaluation.add(skillEvaluation);
        return skillEvaluation;

    }
//------------------createSkillEvaluationMethodToPersonnal---------------------------
public List<BigInteger> getSkillIdListBySkillEvaluation(Long personalId) {
    return skillEvaluationMethodRepository.findSkillIdListBySkillEvaluation(personalId);
}

    public SkillEvaluationMethod createSkillEvaluationMethodToPersonnal(Long personnelId, SkillEvaluationMethod team) {
        Set<SkillEvaluationMethod> skillEvaluationMethods = new HashSet<>();

        Optional<Personal> byId = personnelRepository.findById(personnelId);
        if (!byId.isPresent()) {
            throw new org.springframework.data.rest.webmvc.ResourceNotFoundException("personnel with id " + personnelId + " does not exist");
        }
        Personal personal = byId.get();
        team.setPersonal(personal);
        SkillEvaluationMethod skillEvaluationMethod = this.skillEvaluationMethodRepository.save(team);
        skillEvaluationMethods.add(skillEvaluationMethod);
        return skillEvaluationMethod;

    }

//--------------------createSkillEvaluationMethodToPost-----------------------------------------------
public List<BigInteger> getSkillIdBySkillEvaluation(Long postId) {
    return skillEvaluationMethodRepository.findSkillIdBySkillEvaluation(postId);
}
    public SkillEvaluationMethod createSkillEvaluationMethodToPost(Long postId, SkillEvaluationMethod team) {
        Set<SkillEvaluationMethod> skillEvaluationMethods = new HashSet<>();

        Optional<Post> byId = postRepository.findById(postId);
        if (!byId.isPresent()) {
            throw new org.springframework.data.rest.webmvc.ResourceNotFoundException("post with id " + postId + " does not exist");
        }
        Post post = byId.get();
        team.setPost(post);
        SkillEvaluationMethod skillEvaluationMethod = this.skillEvaluationMethodRepository.save(team);
        skillEvaluationMethods.add(skillEvaluationMethod);
        return skillEvaluationMethod;

    }

//    public List<SkillEvaluationMethod> getAllEvaluationMethodBySkillId(Long skillId) {
//        Optional<Skill> skillById = skillRepository.findById(skillId);
//        if (!skillById.isPresent()) {
//            throw new ResourceNotFoundException("skill with id " + skillId + " does not exist");
//        }
//        return skillEvaluationMethodRepository.findBySkillId(skillId);
//    }


    @Override
    public SkillEvaluationMethod updateSkillEvaluationMethodById(Long skillEvaluationMethodId, SkillEvaluationMethod postRequest) {
        if (!skillEvaluationMethodRepository.existsById(skillEvaluationMethodId)) {
            throw new ResourceNotFoundException("skillEvaluationMethod with id " + skillEvaluationMethodId + " not found");
        }
        Optional<SkillEvaluationMethod> posteListe = skillEvaluationMethodRepository.findById(skillEvaluationMethodId);
        if (!posteListe.isPresent()) {
            throw new ResourceNotFoundException("skillEvaluationMethod with id " + skillEvaluationMethodId + " not found");
        }
        SkillEvaluationMethod post1 = posteListe.get();
    //    post1.setDesciption(postRequest.getDesciption());

        return skillEvaluationMethodRepository.save(post1);
    }
    @Override
    public ResponseEntity<Object> deleteSkillEvaluationMethodById(Long skillEvaluationMethodId, Boolean isDelete) {
        Optional<SkillEvaluationMethod> SkillEvaluationMethod = skillEvaluationMethodRepository.findById(skillEvaluationMethodId);
        if (!SkillEvaluationMethod.isPresent()) {
            throw new ResourceNotFoundException("skillEvaluationMethod with id " + skillEvaluationMethodId + " not found");
        }
        SkillEvaluationMethod post1 = SkillEvaluationMethod.get();

        skillEvaluationMethodRepository.save(post1);
        return ResponseEntity.ok().build();
    }

    public double calculateSumNoteByPersonalIdAndNomPersonnelAndPostId(Long personalId, Long postId) {
        return skillEvaluationMethodRepository.sumNoteByPersonalIdAndNomPersonnelAndPostId(personalId, postId);
    }


}
