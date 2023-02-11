package com.crm.operis_app.services.GRH;
import com.crm.operis_app.exception.ResourceNotFoundException;
import com.crm.operis_app.model.GRH.Personal;
import com.crm.operis_app.model.GRH.Skill;
import com.crm.operis_app.model.GRH.Post;
import com.crm.operis_app.model.GRH.SkillEvaluationMethod;
import com.crm.operis_app.repository.GRH.SkillEvaluationMethodRepository;
import com.crm.operis_app.repository.GRH.SkillRepository;
import com.crm.operis_app.repository.GRH.PostRepository;
import com.crm.operis_app.repository.Utils.PersonnelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service

public class SkillServiceImp implements SkillService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    SkillRepository skillRepository;

    @Autowired
    PersonnelRepository personnelRepository;

@Autowired
    SkillEvaluationMethodRepository skillEvaluationMethodRepository;

  /*  public List<Skill> getCompetences() {
        return skillRepository.findByActiveIsTrueOrderByIdAsc();
    }

    public List<Skill> getArchivedCompetences() {
        return skillRepository.findByActiveIsFalse();
    }
*/

    public List<Skill> getCompetences() {
        return skillRepository.findByActiveIsTrueOrderByIdAsc();
    }

    public List<Skill> getArchivedCompetences() {
        return skillRepository.findByActiveIsFalseOrderByIdAsc();
    }


  /*  @Override
    public List<Skill> getAllCompetencesExigees( ) {
        return skillRepository.findAll();
    }*/

    @Override
    public Skill createCompetences(Skill skill) {
        return skillRepository.save(skill);
    }

    @Override
    public Skill createCompetencesExigees(Long posteId, Skill skill) {
        Set<Skill> skill1 = new HashSet<>();
        Post post1 = new Post();
        Optional<Post> byId = postRepository.findById(posteId);
        if (!byId.isPresent()) {
            throw new ResourceNotFoundException("posteListe with id " + posteId + " does not exist");
        }
        Post post = byId.get();
        //skill.set(post);
        Skill competencesExigeess = this.skillRepository.save(skill);
        skill1.add(competencesExigeess);
        //post1.setCompetencesExigees(skill1);
        return competencesExigeess;
    }

    @Override
    public Optional<Skill> getCompetencesExigeesById(Long competencesExigeesId) {
        if (!skillRepository.existsById(competencesExigeesId)) {
            throw new ResourceNotFoundException("exigence with id " + competencesExigeesId + " not found");
        }
        return skillRepository.findById(competencesExigeesId);
    }
    @Override
    public Skill updateCompetencesExigees(Long competencesExigeesId, Skill skillRequest) {
        if (!skillRepository.existsById(competencesExigeesId)) {
            throw new ResourceNotFoundException("competencesExigees with id " + competencesExigeesId + " not found");
        }
        Optional<Skill> competencesExigees = skillRepository.findById(competencesExigeesId);
        if (!competencesExigees.isPresent()) {
            throw new ResourceNotFoundException("CompetencesExigees with id " + competencesExigeesId + " not found");
        }
        Skill skill1 = competencesExigees.get();
        skill1.setSkillName(skillRequest.getSkillName());
        skill1.setCategory(skillRequest.getCategory());
        skill1.setCreationDate(skillRequest.getCreationDate());
        skill1.setDescription(skillRequest.getDescription());
        skill1.setComment(skillRequest.getComment());
        return skillRepository.save(skill1);
    }
    @Override
    public ResponseEntity<Object> deleteCompetencesExigeesById(Long competencesExigeesId) {
        if (!skillRepository.existsById(competencesExigeesId)) {
            throw new ResourceNotFoundException("competencesExigees with id " + competencesExigeesId + " not found");
        }
        skillRepository.deleteById(competencesExigeesId);
        return ResponseEntity.ok().build();
    }

/*public ResponseEntity<Object> deleteCompetencesById(Long competencesId) {
    if (!skillRepository.existsById(competencesId)) {
        throw new ResourceNotFoundException("Competences with id " + competencesId + " not found");
    }
    skillRepository.deleteById(competencesId);
    return ResponseEntity.ok().build();
}*/

    public ResponseEntity<Object> deleteCompetencesById(Long equipeId ,boolean isDelete) {
        if (!skillRepository.existsById(equipeId)) {
            throw new ResourceNotFoundException("Competences with id " + equipeId + " not found");
        }

        Optional<Skill> equipe = skillRepository.findById(equipeId);
        Skill equipe1 = equipe.get();
        equipe1.setActive(isDelete);
        skillRepository.save(equipe1);

        return ResponseEntity.ok().build();

    }



    //-------------------------------Personnal_AvailableSkills-------------------------//

    @Override
    public void addPersonnalAvailableSkills(Long personalId, Long skillId) {
        Optional<Skill> skillById = skillRepository.findById(skillId);
        if (!skillById.isPresent()) {
            throw new ResourceNotFoundException("skill with id " + skillId + " does not exist");
        }
        Skill skill = skillById.get();
        Optional<Personal> personalById = personnelRepository.findById(personalId);
        if (!personalById.isPresent()) {
            throw new ResourceNotFoundException("personal  with id " + personalId + " does not exist");
        }
        Personal personal = personalById.get();
        personal.getSkill().add(skill);
        personnelRepository.save(personal);
    }

    @Override
    public void removePersonnalAvailableSkills(Long personalId, Long skillId) {
        Optional<Skill> skillById = skillRepository.findById(skillId);
        if (!skillById.isPresent()) {
            throw new ResourceNotFoundException("skill with id " + skillId + " does not exist");
        }
        Skill skill = skillById.get();
        Optional<Personal> personalById = personnelRepository.findById(personalId);
        if (!personalById.isPresent()) {
            throw new ResourceNotFoundException("personal  with id " + personalId + " does not exist");
        }
        Personal personal = personalById.get();
        personal.getSkill().remove(skill);
        personnelRepository.save(personal);
    }

    //-----------------------addSkillToPersonnal-------------------------------//
    public void addSkillToPersonnal(Long personnalId, Long skillId) {
        Optional<Skill> personnelById = skillRepository.findById(skillId);
        if (!personnelById.isPresent()) {
            throw new ResourceNotFoundException("skill with id " + skillId + " does not exist");
        }
        Skill personnel = personnelById.get();
        Optional<SkillEvaluationMethod> teamById = skillEvaluationMethodRepository.findById(personnalId);
        if (!teamById.isPresent()) {
            throw new ResourceNotFoundException("personnalId with id " + personnalId + " does not exist");
        }
        SkillEvaluationMethod team = teamById.get();
        team.setSkill(personnel);
        skillEvaluationMethodRepository.save(team);
    }
    //-----------------------addSkillToPost-------------------------------//
    public void addSkillToPost(Long postId, Long skillId) {
        Optional<Skill> postById = skillRepository.findById(skillId);
        if (!postById.isPresent()) {
            throw new ResourceNotFoundException("skill with id " + skillId + " does not exist");
        }
        Skill skill = postById.get();
        Optional<SkillEvaluationMethod> teamById = skillEvaluationMethodRepository.findById(postId);
        if (!teamById.isPresent()) {
            throw new ResourceNotFoundException("post with id " + postId + " does not exist");
        }
        SkillEvaluationMethod team = teamById.get();
        team.setSkill(skill);
        skillEvaluationMethodRepository.save(team);
    }

}

