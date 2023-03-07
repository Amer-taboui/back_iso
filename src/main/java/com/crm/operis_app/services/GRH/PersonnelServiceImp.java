package com.crm.operis_app.services.GRH;

import com.crm.operis_app.exception.ResourceNotFoundException;
import com.crm.operis_app.model.GRH.Formation;
import com.crm.operis_app.model.GRH.Personal;
import com.crm.operis_app.model.GRH.Post;
import com.crm.operis_app.model.NonConformite.ClotureNonConformite;
import com.crm.operis_app.model.NonConformite.ListeNonConformite;
import com.crm.operis_app.model.NonConformite.ValidationNonConformite;
import com.crm.operis_app.model.action.actionCorrection.EtatAction;
import com.crm.operis_app.model.action.actionCorrection.PlanAction;
import com.crm.operis_app.model.action.actionCorrection.ValidationAction;
import com.crm.operis_app.model.audit.ListeAudit;
import com.crm.operis_app.model.authUser.User;

import com.crm.operis_app.model.reclamation.AnalyseReclamation;
import com.crm.operis_app.model.reclamation.ClotureReclamation;
import com.crm.operis_app.model.reclamation.CreationReclamation;
import com.crm.operis_app.repository.GRH.FormationRepository;
import com.crm.operis_app.repository.GRH.PostRepository;
import com.crm.operis_app.repository.NonConformite.ClotureNonConformiteRepository;
import com.crm.operis_app.repository.NonConformite.ListeNonConformiteRepository;
import com.crm.operis_app.repository.NonConformite.ValidationNonConformiteRepository;
import com.crm.operis_app.repository.Utils.PersonnelRepository;
import com.crm.operis_app.repository.action.EtatActionRepository;
import com.crm.operis_app.repository.action.PlanActionRepository;
import com.crm.operis_app.repository.action.ValidationActionRepository;
import com.crm.operis_app.repository.audit.ListeAuditRepository;
import com.crm.operis_app.repository.authUser.UserRepository;
import com.crm.operis_app.repository.reclamation.AnalyseReclamationRepository;
import com.crm.operis_app.repository.reclamation.ClotureReclamationRepository;
import com.crm.operis_app.repository.reclamation.CreationReclamationRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service

public class PersonnelServiceImp implements PersonnelService {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    @Autowired
    PersonnelRepository personnelRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PostRepository postRepository;
    @Autowired
    FormationRepository formationRepository;
    @Autowired
    ListeAuditRepository listeAuditRepository;
    @Autowired
    PlanActionRepository planActionRepository;
    @Autowired
    ValidationActionRepository validationActionRepository;
    @Autowired
    EtatActionRepository etatActionRepository;
    @Autowired
    AnalyseReclamationRepository analyseReclamationRepository;
    @Autowired
    ClotureReclamationRepository  clotureReclamationRepository;
    @Autowired
    CreationReclamationRepository creationReclamationRepository;
    @Autowired
    ClotureNonConformiteRepository  clotureNonConformiteRepository;
    @Autowired
    ValidationNonConformiteRepository validationNonConformiteRepository;
    @Autowired
    ListeNonConformiteRepository listeNonConformiteRepository;
    Long formationId;
    Long personnelId;
    Long auditId;
    @Override
    public List<Personal> getListPersonnels() {
        return personnelRepository.findByActiveIsTrueOrderByIdAsc();
    }

    @Override
    public List<Personal> getArchivedListPersonnels() {
        return personnelRepository.findByActiveIsFalseOrderByIdAsc();
    }
    @Override
    public Optional<Personal> getPersonnelById(Long personnelId) {
        if (!personnelRepository.existsById(personnelId)) {
            throw new ResourceNotFoundException("Personnel with id " + personnelId + " not found");
        }
        return personnelRepository.findById(personnelId);
    }
    @Override
    public Personal createPersonnel(Personal personnel) {
        return personnelRepository.save(personnel);
    }
    @Override
    public Personal updatePersonnelById(Long personnelId, Personal PersonnelRequest) {
        if (!personnelRepository.existsById(personnelId)) {
            throw new ResourceNotFoundException("Personnel with id " + personnelId + " not found");
        }
        Optional<Personal> personnel = personnelRepository.findById(personnelId);
        if (!personnel.isPresent()) {
            throw new ResourceNotFoundException("Personnel with id " + personnelId + " not found");
        }
        Personal personnel1 = personnel.get();
        personnel1.setNomPersonnel(PersonnelRequest.getNomPersonnel());
        personnel1.setPrenomPersonnel(PersonnelRequest.getPrenomPersonnel());
        personnel1.setMatricule(PersonnelRequest.getMatricule());
        personnel1.setBirthDate(PersonnelRequest.getBirthDate());
        personnel1.setAdress(PersonnelRequest.getAdress());
        personnel1.setPhone(PersonnelRequest.getPhone());
        personnel1.setCity(PersonnelRequest.getCity());
        personnel1.setComment(PersonnelRequest.getComment());
        personnel1.setCin(PersonnelRequest.getCin());
        personnel1.setTypeContrat(PersonnelRequest.getTypeContrat());
        personnel1.setDateAff(PersonnelRequest.getDateAff());
        personnel1.setDateFinAff(PersonnelRequest.getDateFinAff());
        personnel1.setChampInterim(PersonnelRequest.getChampInterim());
        personnel1.setAdressEmail(PersonnelRequest.getAdressEmail());
        personnel1.setCountry(PersonnelRequest.getCountry());
        return personnelRepository.save(personnel1);
    }
    @Override
    public void addPersonnelToUser(Long userId, Long personnelId) {
        Optional<Personal> personnelById = personnelRepository.findById(personnelId);
        if (!personnelById.isPresent()) {
            throw new ResourceNotFoundException("Personnel with id " + personnelId + " does not exist");
        }
        Personal personnel = personnelById.get();

        Optional<User> userById = userRepository.findById(userId);
        if (!userById.isPresent()) {
            throw new ResourceNotFoundException("user with id " + userId + " does not exist");
        }
        User user = userById.get();
        user.setPersonnel(personnel);
        userRepository.save(user);
    }

    @Override
    public ResponseEntity<Object> deletePersonnelById(Long personnelId, Boolean isDelete) {
        Optional<Personal> personnel = personnelRepository.findById(personnelId);
        if (!personnel.isPresent()) {
            throw new ResourceNotFoundException("Personnel with id " + personnelId + " not found");
        }
        Personal personnel1 = personnel.get();
        personnel1.setActive(isDelete);

        personnelRepository.save(personnel1);
//        personnelRepository.deleteById(personnelId);
        return ResponseEntity.ok().build();
    }


@Override
public void addParticipantToFormation(Long formationId, Long personnelId) {
    Optional<Personal> personnelById = personnelRepository.findById(personnelId);
    if (!personnelById.isPresent()) {
        throw new ResourceNotFoundException("Personnel with id " + personnelId + " does not exist");
    }
    Personal personnel = personnelById.get();
    Optional<Formation> formationById = formationRepository.findById(formationId);
    if (!formationById.isPresent()) {
        throw new ResourceNotFoundException("actionSecuritation with id " + formationId + " does not exist");
    }
    Formation formation = formationById.get();
    //formation.setParticipantFormation(personnel);
    formationRepository.save(formation);
    this.formationId = formationId;
    this.personnelId = personnelId;
}

@Override
    public void addPersonalPost(Long postId, Long personalId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post not found"));
        Personal personal = personnelRepository.findById(personalId).orElseThrow(() -> new ResourceNotFoundException("Personal not found"));
        post.getPersonnels().add(personal);
        postRepository.save(post);
    }

    @Override
    public void removePersonalPost(Long personalId, Long postId) {
        Optional<Personal> personalById = personnelRepository.findById(personalId);
        if (!personalById.isPresent()) {
            throw new ResourceNotFoundException("personal with id " + personalId + " does not exist");
        }
        Personal personal = personalById.get();

        Optional<Post> postById = postRepository.findById(postId);
        if (!postById.isPresent()) {
            throw new ResourceNotFoundException("Post  with id " + postId + " does not exist");
        }
        Post post = postById.get();
        post.getPersonnels().remove(personal);
        postRepository.save(post);
    }

    //----------------------addPersonnalToFormation------------------//
    @Override
    public void addPersonalFormation(Long formationId, Long personalId) {
        Formation formation = formationRepository.findById(formationId).orElseThrow(() -> new ResourceNotFoundException("formation not found"));
        Personal personal = personnelRepository.findById(personalId).orElseThrow(() -> new ResourceNotFoundException("Personal not found"));
        formation.getPersonnels().add(personal);
        formationRepository.save(formation);
    }

    @Override
    public void removePersonalFormation(Long personalId, Long formationId) {
        Optional<Personal> personalById = personnelRepository.findById(personalId);
        if (!personalById.isPresent()) {
            throw new ResourceNotFoundException("personal with id " + personalId + " does not exist");
        }
        Personal personal = personalById.get();

        Optional<Formation> formationById = formationRepository.findById(formationId);
        if (!formationById.isPresent()) {
            throw new ResourceNotFoundException("formation  with id " + formationId + " does not exist");
        }
        Formation formation = formationById.get();
        formation.getPersonnels().remove(personal);
        formationRepository.save(formation);
    }
    //----------------------addPersonnalToAudit------------------//
    @Override
    public void addPersonalAudit(Long auditId, Long personalId) {
        ListeAudit formation = listeAuditRepository.findById(auditId).orElseThrow(() -> new ResourceNotFoundException("formation not found"));
        Personal personal = personnelRepository.findById(personalId).orElseThrow(() -> new ResourceNotFoundException("Personal not found"));
        formation.getPersonnels().add(personal);
        listeAuditRepository.save(formation);
    }

    @Override
    public void removePersonalAudit(Long personalId, Long auditId) {
        Optional<Personal> personalById = personnelRepository.findById(personalId);
        if (!personalById.isPresent()) {
            throw new ResourceNotFoundException("personal with id " + personalId + " does not exist");
        }
        Personal personal = personalById.get();

        Optional<ListeAudit> formationById = listeAuditRepository.findById(auditId);
        if (!formationById.isPresent()) {
            throw new ResourceNotFoundException("formation  with id " + auditId + " does not exist");
        }
        ListeAudit formation = formationById.get();
        formation.getPersonnels().remove(personal);
        listeAuditRepository.save(formation);
    }
    //----------------------------------planAction-------------------------------------------//


   @Override
public void addPersonalPlanAction(Long planId, Long personnelId) {
    Optional<Personal> personnelById = personnelRepository.findById(personnelId);
    if (!personnelById.isPresent()) {
        throw new ResourceNotFoundException("Personnel with id " + personnelId + " does not exist");
    }
       Personal personnel = personnelById.get();
    Optional<PlanAction> planById = planActionRepository.findById(planId);
    if (!planById.isPresent()) {
        throw new ResourceNotFoundException("plan with id " + planId + " does not exist");
    }
       PlanAction planAction = planById.get();
       planAction.setPersonal(personnel);
       planActionRepository.save(planAction);
}

    @Override
    public void addPersonalValidationAction(Long validationId, Long personnelId) {
        Optional<Personal> personnelById = personnelRepository.findById(personnelId);
        if (!personnelById.isPresent()) {
            throw new ResourceNotFoundException("Personnel with id " + personnelId + " does not exist");
        }
        Personal personnel = personnelById.get();
        Optional<ValidationAction> planById = validationActionRepository.findById(validationId);
        if (!planById.isPresent()) {
            throw new ResourceNotFoundException("validationId with id " + validationId + " does not exist");
        }
        ValidationAction validationAction = planById.get();
        validationAction.setPersonal(personnel);
        validationActionRepository.save(validationAction);
    }

    @Override
    public void addPersonalEtatAction(Long etatId, Long personnelId) {
        Optional<Personal> personnelById = personnelRepository.findById(personnelId);
        if (!personnelById.isPresent()) {
            throw new ResourceNotFoundException("Personnel with id " + personnelId + " does not exist");
        }
        Personal personnel = personnelById.get();
        Optional<EtatAction> etatById = etatActionRepository.findById(etatId);
        if (!etatById.isPresent()) {
            throw new ResourceNotFoundException("etat with id " + etatId + " does not exist");
        }
        EtatAction etatAction = etatById.get();
        etatAction.setPersonal(personnel);
        etatActionRepository.save(etatAction);
    }
    @Override
    public void addPersonalAnalyseReclamation(Long analyseId, Long personnelId) {
        Optional<Personal> personnelById = personnelRepository.findById(personnelId);
        if (!personnelById.isPresent()) {
            throw new ResourceNotFoundException("Personnel with id " + personnelId + " does not exist");
        }
        Personal personnel = personnelById.get();
        Optional<AnalyseReclamation> etatById = analyseReclamationRepository.findById(analyseId);
        if (!etatById.isPresent()) {
            throw new ResourceNotFoundException("etat with id " + analyseId + " does not exist");
        }
        AnalyseReclamation etatAction = etatById.get();
        etatAction.setPersonal(personnel);
        analyseReclamationRepository.save(etatAction);
    }
    @Override
    public void addPersonalClotureReclamation(Long clotureId, Long personnelId) {
        Optional<Personal> personnelById = personnelRepository.findById(personnelId);
        if (!personnelById.isPresent()) {
            throw new ResourceNotFoundException("Personnel with id " + personnelId + " does not exist");
        }
        Personal personnel = personnelById.get();
        Optional<ClotureReclamation> etatById = clotureReclamationRepository.findById(clotureId);
        if (!etatById.isPresent()) {
            throw new ResourceNotFoundException("cloture with id " + clotureId + " does not exist");
        }
        ClotureReclamation etatAction = etatById.get();
        etatAction.setPersonal(personnel);
        clotureReclamationRepository.save(etatAction);
    }
    @Override
    public void addPersonalCreationReclamation(Long crationId, Long personnelId) {
        Optional<Personal> personnelById = personnelRepository.findById(personnelId);
        if (!personnelById.isPresent()) {
            throw new ResourceNotFoundException("Personnel with id " + personnelId + " does not exist");
        }
        Personal personnel = personnelById.get();
        Optional<CreationReclamation> etatById = creationReclamationRepository.findById(crationId);
        if (!etatById.isPresent()) {
            throw new ResourceNotFoundException("cration with id " + crationId + " does not exist");
        }
        CreationReclamation etatAction = etatById.get();
        etatAction.setPersonal(personnel);
        creationReclamationRepository.save(etatAction);
    }
    @Override
    public void addPersonalValidationNonConformite(Long validationId, Long personnelId) {
        Optional<Personal> personnelById = personnelRepository.findById(personnelId);
        if (!personnelById.isPresent()) {
            throw new ResourceNotFoundException("Personnel with id " + personnelId + " does not exist");
        }
        Personal personnel = personnelById.get();
        Optional<ValidationNonConformite> planById = validationNonConformiteRepository.findById(validationId);
        if (!planById.isPresent()) {
            throw new ResourceNotFoundException("plan with id " + validationId + " does not exist");
        }
        ValidationNonConformite planAction = planById.get();
        planAction.setPersonal(personnel);
        validationNonConformiteRepository.save(planAction);
    }
    @Override
    public void addPersonalClotureNonConformite(Long clotureId, Long personnelId) {
        Optional<Personal> personnelById = personnelRepository.findById(personnelId);
        if (!personnelById.isPresent()) {
            throw new ResourceNotFoundException("Personnel with id " + personnelId + " does not exist");
        }
        Personal personnel = personnelById.get();
        Optional<ClotureNonConformite> planById = clotureNonConformiteRepository.findById(clotureId);
        if (!planById.isPresent()) {
            throw new ResourceNotFoundException("cloture with id " + clotureId + " does not exist");
        }
        ClotureNonConformite planAction = planById.get();
        planAction.setPersonal(personnel);
        clotureNonConformiteRepository.save(planAction);
    }

    @Override
    public void addResponsableDecouverteListeNonConformite(Long listeNonConformiteId, Long personnelId) {
        Optional<Personal> personnelById = personnelRepository.findById(personnelId);
        if (!personnelById.isPresent()) {
            throw new ResourceNotFoundException("Personnel with id " + personnelId + " does not exist");
        }
        Personal personnel = personnelById.get();
        Optional<ListeNonConformite> nonConformiteById = listeNonConformiteRepository.findById(listeNonConformiteId);
        if (!nonConformiteById.isPresent()) {
            throw new ResourceNotFoundException("listeNonConformite with id " + listeNonConformiteId + " does not exist");
        }
        ListeNonConformite listeNonConformite = nonConformiteById.get();
        listeNonConformite.setResponsableDecouverte(personnel);
        listeNonConformiteRepository.save(listeNonConformite);
    }




    @Override

    public List<Map<String,Object>> getPersonals() {
        return personnelRepository.getPersonals();
    }

}
