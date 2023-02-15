package com.crm.operis_app.controller.GRH;

import com.crm.operis_app.model.GRH.Post;
import com.crm.operis_app.model.GRH.PostPersonnelCount;
import com.crm.operis_app.model.GRH.SkillEvaluationMethod;
import com.crm.operis_app.repository.GRH.SkillEvaluationMethodRepository;
import com.crm.operis_app.services.GRH.SkillEvaluationMethodServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class SkillEvaluationMethodController {

    @Autowired
    SkillEvaluationMethodServiceImp skillEvaluationMethodServiceImp;

    @Autowired
    SkillEvaluationMethodRepository skillEvaluationMethodRepository;

    @RequestMapping(value = "/skillEvaluationMethod", method = RequestMethod.GET)
    public List<SkillEvaluationMethod> getAllSkillEvaluationMethod() {
        return skillEvaluationMethodServiceImp.getSkillEvaluationMethod();
    }

    @RequestMapping(value = "skillEvaluationMethod/{skillEvaluationMethodId}", method = RequestMethod.GET)
    public Optional<SkillEvaluationMethod> getSkillEvaluationMethodById(@PathVariable(value = "skillEvaluationMethodId") Long skillEvaluationMethodId) {
        return skillEvaluationMethodServiceImp.getSkillEvaluationMethodById(skillEvaluationMethodId);
    }

    @PostMapping(value = "skill/{skillId}/skillEvaluationMethod",  consumes = MediaType.APPLICATION_JSON_VALUE)
    public SkillEvaluationMethod createSkillEvaluationMethod(@PathVariable(value = "skillId") Long skillId, @RequestBody SkillEvaluationMethod skillEvaluationMethod) {
        return skillEvaluationMethodServiceImp.createSkillEvaluationMethod(skillId, skillEvaluationMethod);
    }
//    @RequestMapping(value = "/skillEvaluationMethod/Skill/{skillId}", method = RequestMethod.GET)
//    public List<SkillEvaluationMethod> getAllEvaluationMethodBySkillId(@PathVariable(value = "skillId") Long skillId) {
//        return skillEvaluationMethodServiceImp.getAllEvaluationMethodBySkillId(skillId);
//    }

    //---------------------------------createSkillEvaluationMethodToPersonnal------------------------------//

    @GetMapping(value = "/skillEvaluationPersonnelId/personal/{personalId}")
    public List<BigInteger> getSkillIdListBySkillEvaluation(@PathVariable(value = "personalId") Long personalId) {
        return skillEvaluationMethodServiceImp.getSkillIdListBySkillEvaluation(personalId);
    }
    @PostMapping(value = "personal/{personalId}/skillEvaluationMethod",  consumes = MediaType.APPLICATION_JSON_VALUE)
    public SkillEvaluationMethod createSkillEvaluationMethodToPersonnal(@PathVariable(value = "personalId") Long personalId, @RequestBody SkillEvaluationMethod skillEvaluationMethod) {
        return skillEvaluationMethodServiceImp.createSkillEvaluationMethodToPersonnal(personalId, skillEvaluationMethod);
    }
    //---------------------------------------createSkillEvaluationMethodToPost------------------------------//
    @GetMapping(value = "/skillEvaluationPostId/post/{postId}")
    public List<BigInteger> getSkillIdBySkillEvaluation(@PathVariable(value = "postId") Long postId) {
        return skillEvaluationMethodServiceImp.getSkillIdListBySkillEvaluation(postId);
    }
    @PostMapping(value = "post/{postId}/skillEvaluationMethod",  consumes = MediaType.APPLICATION_JSON_VALUE)
    public SkillEvaluationMethod createSkillEvaluationMethodToPost(@PathVariable(value = "postId") Long postId, @RequestBody SkillEvaluationMethod skillEvaluationMethod) {
        return skillEvaluationMethodServiceImp.createSkillEvaluationMethodToPost(postId, skillEvaluationMethod);
    }
//----------------------------------------------------------------------------------------------//

    @RequestMapping(value = "/skillEvaluationMethod/{skillEvaluationMethodId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public SkillEvaluationMethod updateSkillEvaluationMethod(@PathVariable(value = "skillEvaluationMethodId") Long skillEvaluationMethodId, @RequestBody SkillEvaluationMethod post) {
        return skillEvaluationMethodServiceImp.updateSkillEvaluationMethodById(skillEvaluationMethodId, post);
    }

//    @RequestMapping(value = "/posteListe/{skillEvaluationMethodId}/{isDelete}", method = RequestMethod.DELETE)
//    public ResponseEntity<Object> deleteSkillEvaluationMethodById(@PathVariable(value = "skillEvaluationMethodId") Long skillEvaluationMethodId, @PathVariable(value = "isDelete") Boolean isDelete) {
//        return skillEvaluationMethodServiceImp.deleteSkillEvaluationMethodById(skillEvaluationMethodId, isDelete);
//    }

    @DeleteMapping(value = "delete/skillEvaluationMethod/{skillEvaluationMethodId}")
    public ResponseEntity<Object> deleteTeamById(@PathVariable(value = "skillEvaluationMethodId") long skillEvaluationMethodId) {
        return skillEvaluationMethodServiceImp.deleteSkillEvaluationMethodById(skillEvaluationMethodId);
    }
    @GetMapping("/evaluation/{personalId}/{postId}/sum")
    public ResponseEntity<Double> calculateSumNoteByPersonalIdAndNomPersonnelAndPostId(@PathVariable Long personalId, @PathVariable Long postId) {
        double sum = skillEvaluationMethodServiceImp.calculateSumNoteByPersonalIdAndNomPersonnelAndPostId(personalId, postId);
        return new ResponseEntity<>(sum, HttpStatus.OK);
    }



}
