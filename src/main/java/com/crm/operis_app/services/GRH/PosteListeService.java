package com.crm.operis_app.services.GRH;

import com.crm.operis_app.model.GRH.Post;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface PosteListeService {
    List<Post> getPosteListe();
    List<Post> getArchivedPosteListe();
    Optional<Post> getPosteListeById(Long posteListeId);
    Post createPosteListe(Post post);
    Post updatePosteListeById(Long posteListeId, Post postRequest);
    ResponseEntity<Object> deletePosteListeById(Long actionCostId);
}
