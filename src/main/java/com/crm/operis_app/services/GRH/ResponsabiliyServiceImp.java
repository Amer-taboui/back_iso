package com.crm.operis_app.services.GRH;

import com.crm.operis_app.exception.ResourceNotFoundException;
import com.crm.operis_app.model.GRH.Responsability;
import com.crm.operis_app.model.GRH.Post;
import com.crm.operis_app.repository.GRH.ResponsabiliyRepository;
import com.crm.operis_app.repository.GRH.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service

public class ResponsabiliyServiceImp implements ResponsabiliyService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    ResponsabiliyRepository responsabiliyRepository;

    @Override
    public List<Responsability> getAllResponsabiliy( ) {
        return responsabiliyRepository.findAll();
    }
    @Override
    public Responsability createResponsabiliy(Long posteId, Responsability responsability) {
        Set<Responsability> responsability1 = new HashSet<>();
        Post post1 = new Post();
        Optional<Post> byId = postRepository.findById(posteId);
        if (!byId.isPresent()) {
            throw new ResourceNotFoundException("posteListe with id " + posteId + " does not exist");
        }
        Post post = byId.get();
        responsability.setPost(post);
        Responsability ficheFonctions = this.responsabiliyRepository.save(responsability);
        responsability1.add(ficheFonctions);
        post1.setResponsability(responsability1);
        return ficheFonctions;
    }
    @Override
    public Optional<Responsability> getResponsabiliyById(Long ficheFonctionId) {
        if (!responsabiliyRepository.existsById(ficheFonctionId)) {
            throw new ResourceNotFoundException("exigence with id " + ficheFonctionId + " not found");
        }
        return responsabiliyRepository.findById(ficheFonctionId);
    }
    @Override
    public Responsability updateResponsabiliyById(Long ficheFonctionId, Responsability responsabilityRequest) {
        if (!responsabiliyRepository.existsById(ficheFonctionId)) {
            throw new ResourceNotFoundException("FicheFonction with id " + ficheFonctionId + " not found");
        }
        Optional<Responsability> ficheFonction = responsabiliyRepository.findById(ficheFonctionId);
        if (!ficheFonction.isPresent()) {
            throw new ResourceNotFoundException("FicheFonction with id " + ficheFonctionId + " not found");
        }
        Responsability responsability1 = ficheFonction.get();
        responsability1.setResponsabiliyName(responsabilityRequest.getResponsabiliyName());
        responsability1.setComment(responsabilityRequest.getComment());
        return responsabiliyRepository.save(responsability1);
    }
    @Override
    public ResponseEntity<Object> deleteResponsabiliyById(Long ficheFonctionId) {
        if (!responsabiliyRepository.existsById(ficheFonctionId)) {
            throw new ResourceNotFoundException("ficheFonction with id " + ficheFonctionId + " not found");
        }
        responsabiliyRepository.deleteById(ficheFonctionId);
        return ResponseEntity.ok().build();
    }
}
