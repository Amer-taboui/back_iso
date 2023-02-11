package com.crm.operis_app.controller.GRH;

import com.crm.operis_app.model.GRH.Post;
import com.crm.operis_app.services.GRH.PosteListeService;
import com.crm.operis_app.services.GRH.PosteListeServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class PosteListeController {
    @Autowired
    PosteListeService posteListeServiceImp;

    @RequestMapping(value = "/PosteListe", method = RequestMethod.GET)
    public List<Post> getAllPosteListe() {
        return posteListeServiceImp.getPosteListe();
    }


    @RequestMapping(value = "/archivedPosteListe", method = RequestMethod.GET)
    public List<Post> getArchivedPosteListe() {
        return posteListeServiceImp.getArchivedPosteListe();
    }


    @RequestMapping(value = "/createPosteListe", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Post createPosteListe(@RequestBody Post post) {
        return posteListeServiceImp.createPosteListe(post);
    }

    @RequestMapping(value = "posteListe/{posteListeId}", method = RequestMethod.GET)
    public Optional<Post> getPosteListeById(@PathVariable(value = "posteListeId") Long posteListeId) {
        return posteListeServiceImp.getPosteListeById(posteListeId);
    }

    @RequestMapping(value = "/posteListe/{posteListeId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Post updatePosteListe(@PathVariable(value = "posteListeId") Long posteListeId, @RequestBody Post post) {
        return posteListeServiceImp.updatePosteListeById(posteListeId, post);
    }
/*
    @RequestMapping(value = "/posteListe/{posteListeId}/{isDelete}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deletePosteListeById(@PathVariable(value = "posteListeId") Long posteListeId, @PathVariable(value = "isDelete") Boolean isDelete) {
        return posteListeServiceImp.deletePosteListeById(posteListeId, isDelete);
    }
*/
    @DeleteMapping(value = "/posteListe/{id}")
    public ResponseEntity<Object> deleteActionCostById(@PathVariable(value = "id") Long id) {
        return posteListeServiceImp.deletePosteListeById(id);
    }
}
