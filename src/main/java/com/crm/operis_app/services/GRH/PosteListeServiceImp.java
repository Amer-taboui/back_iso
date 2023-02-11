package com.crm.operis_app.services.GRH;

import com.crm.operis_app.exception.ResourceNotFoundException;
import com.crm.operis_app.model.GRH.Personal;
import com.crm.operis_app.model.GRH.Post;
import com.crm.operis_app.repository.GRH.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class PosteListeServiceImp implements PosteListeService{

    @Autowired
    PostRepository postRepository;
@Override
    public List<Post> getPosteListe() {
        return postRepository.findByActiveIsTrueOrderByIdAsc();
    }
@Override
    public List<Post> getArchivedPosteListe() {
        return postRepository.findByActiveIsFalseOrderByIdAsc();
    }
@Override
    public Optional<Post> getPosteListeById(Long posteListeId) {
        if (!postRepository.existsById(posteListeId)) {
            throw new ResourceNotFoundException("Personnel with id " + posteListeId + " not found");
        }
        return postRepository.findById(posteListeId);
    }
@Override
    public Post createPosteListe(Post post) {
        return postRepository.save(post);
    }

@Override
    public Post updatePosteListeById(Long posteListeId, Post postRequest) {
        if (!postRepository.existsById(posteListeId)) {
            throw new ResourceNotFoundException("posteListe with id " + posteListeId + " not found");
        }
        Optional<Post> posteListe = postRepository.findById(posteListeId);
        if (!posteListe.isPresent()) {
            throw new ResourceNotFoundException("posteListeId with id " + posteListeId + " not found");
        }
        Post post1 = posteListe.get();
    post1.setPosteName(postRequest.getPosteName());
    post1.setMission(postRequest.getMission());
    post1.setDesciption(postRequest.getDesciption());

    post1.setExpertise(postRequest.getExpertise());
    post1.setProfessionalFamily(postRequest.getProfessionalFamily());
    post1.setProfession(postRequest.getProfession());
        post1.setCreationDate(postRequest.getCreationDate());
        post1.setUpdatedDate(postRequest.getUpdatedDate());
    post1.setComment(postRequest.getComment());

    return postRepository.save(post1);
    }

/*
    public ResponseEntity<Object> deletePosteListeById(Long posteListeId, Boolean isDelete) {
        Optional<Post> PosteListe = postRepository.findById(posteListeId);
        if (!PosteListe.isPresent()) {
            throw new ResourceNotFoundException("PosteListe with id " + posteListeId + " not found");
        }
        Post post1 = PosteListe.get();
        post1.setActive(isDelete);
        postRepository.save(post1);
        return ResponseEntity.ok().build();
    }
*/
public ResponseEntity<Object> deletePosteListeById(Long actionCostId) {
    if (!postRepository.existsById(actionCostId)) {
        throw new ResourceNotFoundException("ActionCost with id " + actionCostId + " not found");
    }
    postRepository.deleteById(actionCostId);
    return ResponseEntity.ok().build();
}


}
