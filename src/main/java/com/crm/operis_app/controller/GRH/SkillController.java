package com.crm.operis_app.controller.GRH;

import com.crm.operis_app.model.GRH.Formation;
import com.crm.operis_app.model.GRH.Skill;
import com.crm.operis_app.services.GRH.SkillServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class SkillController {

    @Autowired
    SkillServiceImp skillServiceImp;

    @RequestMapping(value = "/allCompetencesExigees", method = RequestMethod.GET)
    public List<Skill> getAllCompetencesExigees() {
        return skillServiceImp.getCompetences();
    }


    @RequestMapping(value = "/competences/{competencesId}", method = RequestMethod.GET)
    public Optional<Skill> getCompetencesById(@PathVariable(value = "competencesId") Long competencesId) {
        return skillServiceImp.getCompetencesExigeesById(competencesId);
    }

    @RequestMapping(value = "/createCompetence", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Skill createCompetence(@RequestBody Skill skill) {
        return skillServiceImp.createCompetences(skill);
    }

    @RequestMapping(value = "/createCompetencesExigees/posteListe/{posteId}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Skill createCompetencesExigeesToPoste(@PathVariable(value = "posteId") Long posteId, @RequestBody Skill skill) {
        return skillServiceImp.createCompetencesExigees(posteId, skill);
    }


    @RequestMapping(value = "edit/competences/{competencesId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Skill updateCompetencesById(@PathVariable(value = "competencesId") Long competencesId, @RequestBody Skill skill) {
        return skillServiceImp.updateCompetencesExigees(competencesId, skill);
    }

    @RequestMapping(value = "/competencesExigees/{competencesExigeesId}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteCompetencesExigeesById(@PathVariable(value = "competencesExigeesId") Long competencesExigeesId) {
        return skillServiceImp.deleteCompetencesExigeesById(competencesExigeesId);
    }

   @RequestMapping(value = "/competences/{competencesId}/{isDelete}", method = RequestMethod.DELETE)
   public ResponseEntity<Object> deleteCompetencesById(@PathVariable(value = "competencesId") Long competencesId , @PathVariable(value = "isDelete") Boolean isDelete) {
       return skillServiceImp.deleteCompetencesById(competencesId,isDelete);
   }
  /*  @DeleteMapping(value = "/competences/{id}")
    public ResponseEntity<Object> deleteFormationById(@PathVariable(value = "id") Long id) {
        return skillServiceImp.deleteCompetencesById(id);
    }*/
//-------------------------------------PersonnalAvailableSkills--------------------------------------//

    @PostMapping(value = "add/personal/{personalId}/skill/{Skill_ID}")
    public void addPersonnalAvailableSkills(@PathVariable(value = "personalId") Long personalId, @PathVariable(value = "Skill_ID") Long skillId) {
        skillServiceImp.addPersonnalAvailableSkills(personalId, skillId);
    }

    @PostMapping(value = "remove/personal/{personalId}/skill/{skillId}")
    public void removePersonnalAvailableSkills(@PathVariable(value = "personalId") Long personalId, @PathVariable(value = "skillId") Long skillId) {
        skillServiceImp.removePersonnalAvailableSkills(personalId, skillId);
    }
//------------------------addSkillToPersonnal----------------------------//

@PostMapping(value = "/skillEvaluationPersonal/{personnalId}/addSkill/{skillId}")
public void  addSkillToPersonnal(@PathVariable(value = "personnalId") Long personnalId, @PathVariable(value = "skillId") Long skillId) {
    skillServiceImp.addSkillToPersonnal(personnalId,skillId);
}
//------------------------addSkillToPost----------------------------//

    @PostMapping(value = "/skillEvaluationPost/{postId}/addSkill/{skillId}")
    public void  addSkillToPost(@PathVariable(value = "postId") Long personnalId, @PathVariable(value = "skillId") Long skillId) {
        skillServiceImp.addSkillToPost(personnalId,skillId);
    }
}
