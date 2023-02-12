package com.crm.operis_app.controller.Utils;

import com.crm.operis_app.model.GRH.Personal;
import com.crm.operis_app.services.GRH.PersonnelService;
import com.crm.operis_app.services.GRH.PersonnelServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class PersonnelController {
    @Autowired
    PersonnelService personnelServiceImp;

    @RequestMapping(value = "/personnels", method = RequestMethod.GET)
    public List<Personal> getAllPersonnel() {
        return personnelServiceImp.getListPersonnels();
    }


    @RequestMapping(value = "/archivedPrsonnels", method = RequestMethod.GET)
    public List<Personal> getArchivedListPersonnels() {
        return personnelServiceImp.getArchivedListPersonnels();
    }


    @RequestMapping(value = "/createPersonnel", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Personal createPersonnel(@RequestBody Personal personnel) {
        return personnelServiceImp.createPersonnel(personnel);
    }

    //---------------------------user---------------------------------//
    @PostMapping(value = "/user/{userId}/personnel/{personnelId}")
    public void addPersonnelToUser(@PathVariable(value = "userId") Long userId, @PathVariable(value = "personnelId") Long personnelId) {
        personnelServiceImp.addPersonnelToUser(userId, personnelId);
    }

    //----------------------------------------------------------//
    @RequestMapping(value = "/personnel/{personnelId}", method = RequestMethod.GET)
    public Optional<Personal> getPersonnelById(@PathVariable(value = "personnelId") Long personnelId) {
        return personnelServiceImp.getPersonnelById(personnelId);
    }

    @RequestMapping(value = "/personnel/{personnelId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Personal updatePersonnel(@PathVariable(value = "personnelId") Long personnelId, @RequestBody Personal personnel) {
        return personnelServiceImp.updatePersonnelById(personnelId, personnel);
    }


    @RequestMapping(value = "/personnel/{personnelId}/{isDelete}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deletePersonnelById(@PathVariable(value = "personnelId") Long personnelId, @PathVariable(value = "isDelete") Boolean isDelete) {
        return personnelServiceImp.deletePersonnelById(personnelId, isDelete);
    }


//-------------------------------------participantToPersonnel--------------------------------------//

    @PostMapping(value = "/formation/{formationId}/participant/{personnelId}")
public void addParticipantToFormation(@PathVariable(value = "formationId") Long formationId, @PathVariable(value = "personnelId") Long personnelId)  {
    personnelServiceImp.addParticipantToFormation(formationId,personnelId);
}


//-------------------------------------Personal_Post--------------------------------------//
/*
    @PostMapping(value = "add/post/{postId}/personal/{personalId}")
    public void addPersonalPost(@PathVariable(value = "personalId") Long personalId, @PathVariable(value = "postId") Long postId) {
        personnelServiceImp.addPersonalPost(personalId, postId);
    }
*/

    @PostMapping("add/post/{postId}/personals/{personalId}")
    public void addPersonalToPost(@PathVariable Long postId, @PathVariable Long personalId) {
        personnelServiceImp.addPersonalPost(postId, personalId);
    }
    @PostMapping(value = "remove/personal/{personalId}/post/{postId}")
    public void removePersonalPost(@PathVariable(value = "personalId") Long personalId, @PathVariable(value = "postId") Long postId) {
        personnelServiceImp.removePersonalPost(personalId, postId);
    }
//--------------------------------personal-formation----------------------//
@PostMapping("add/formation/{formationId}/personals/{personalId}")
public void addPersonalToFormation(@PathVariable Long formationId, @PathVariable Long personalId) {
    personnelServiceImp.addPersonalFormation(formationId, personalId);
}
    @PostMapping(value = "remove/personal/{personalId}/formation/{formationId}")
    public void removePersonalFormation(@PathVariable(value = "personalId") Long personalId, @PathVariable(value = "formationId") Long formationId) {
        personnelServiceImp.removePersonalFormation(personalId, formationId);
    }
    //--------------------------------personal-audit----------------------//
    @PostMapping("add/audit/{auditId}/personals/{personalId}")
    public void addPersonalToAudit(@PathVariable Long auditId, @PathVariable Long personalId) {
        personnelServiceImp.addPersonalAudit(auditId, personalId);
    }
    @PostMapping(value = "remove/personal/{personalId}/audit/{auditId}")
    public void removePersonalAudit(@PathVariable(value = "personalId") Long personalId, @PathVariable(value = "auditId") Long auditId) {
        personnelServiceImp.removePersonalAudit(personalId, auditId);
    }


    @GetMapping("/skill")
    public List<Map<String,Object>> getPersonalsWithSkills() {
        return personnelServiceImp.getPersonals();
    }





}
