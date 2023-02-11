package com.crm.operis_app.controller.GRH;

import com.crm.operis_app.model.GRH.EvaluationCriteria;
import com.crm.operis_app.model.GRH.Participant;
import com.crm.operis_app.model.GRH.Post;
import com.crm.operis_app.services.GRH.EvaluationCriteriaServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class EvaluationCriteriaController {
    @Autowired
    EvaluationCriteriaServiceImp evaluationCriteriaServiceImp;

    @RequestMapping(value = "/evaluationCriteria", method = RequestMethod.GET)
    public List<EvaluationCriteria> getAllEvaluationCriteria() {
        return evaluationCriteriaServiceImp.getEvaluationCriteria();
    }



 //   @RequestMapping(value = "/createPosteListe", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
   // public Post createPosteListe(@RequestBody Post post) {
     //   return evaluationCriteriaServiceImp.createPosteListe(post);
    //}
 @PostMapping(value = "participant/{participantId}/evaluationCriteria",  consumes = MediaType.APPLICATION_JSON_VALUE)
 public EvaluationCriteria createEvaluationCriteria(@PathVariable(value = "participantId") Long participantId, @RequestBody EvaluationCriteria evaluationCriteria) {
     return evaluationCriteriaServiceImp.createEvaluationCriteria(participantId, evaluationCriteria);
 }

    @RequestMapping(value = "evaluationCriteria/{evaluationCriteriaId}", method = RequestMethod.GET)
    public Optional<EvaluationCriteria> getEvaluationCriteriaById(@PathVariable(value = "evaluationCriteriaId") Long evaluationCriteriaId) {
        return evaluationCriteriaServiceImp.getEvaluationCriteriaById(evaluationCriteriaId);
    }

    @RequestMapping(value = "/evaluationCriteria/{evaluationCriteriaId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public EvaluationCriteria updateEvaluationCriteria(@PathVariable(value = "evaluationCriteriaId") Long evaluationCriteriaId, @RequestBody EvaluationCriteria evaluationCriteria) {
        return evaluationCriteriaServiceImp.updateEvaluationCriteriaById(evaluationCriteriaId, evaluationCriteria);
    }


    @RequestMapping(value = "/evaluationCriteria/{evaluationCriteriaId}/{isDelete}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteEvaluationCriteriaById(@PathVariable(value = "evaluationCriteriaId") Long evaluationCriteriaId, @PathVariable(value = "isDelete") Boolean isDelete) {
        return evaluationCriteriaServiceImp.deleteEvaluationCriteriaById(evaluationCriteriaId, isDelete);
    }
}
