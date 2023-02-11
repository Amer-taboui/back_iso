package com.crm.operis_app.services.GRH;

import com.crm.operis_app.exception.ResourceNotFoundException;
import com.crm.operis_app.model.GRH.Formation;
import com.crm.operis_app.model.GRH.Personal;
import com.crm.operis_app.model.GRH.Post;
import com.crm.operis_app.model.authUser.User;

import com.crm.operis_app.repository.GRH.FormationRepository;
import com.crm.operis_app.repository.GRH.PostRepository;
import com.crm.operis_app.repository.Utils.PersonnelRepository;
import com.crm.operis_app.repository.authUser.UserRepository;
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
    Long formationId;
    Long personnelId;

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

    @Override

    public List<Map<String,Object>> getPersonals() {
        return personnelRepository.getPersonals();
    }

}
