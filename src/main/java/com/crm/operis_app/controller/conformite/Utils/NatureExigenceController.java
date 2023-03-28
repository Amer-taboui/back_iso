package com.crm.operis_app.controller.conformite.Utils;

import com.crm.operis_app.model.NonConformite.Utils.CategorieNonConformite;
import com.crm.operis_app.model.conformite.Utils.NatureExigence;
import com.crm.operis_app.services.NonConformite.Utils.CategorieNonConformiteService;
import com.crm.operis_app.services.conformite.Utils.NatureExigenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins="*", allowedHeaders="*")
@RestController
@RequestMapping("/api")
public class NatureExigenceController {
    @Autowired
    NatureExigenceService natureExigenceService;

    @RequestMapping(value = "/natureExigence", method = RequestMethod.GET)
    public List<NatureExigence> getSites() {
        return natureExigenceService.getNatureExigence();
    }

    @RequestMapping(value = "/archivedNatureExigence", method = RequestMethod.GET)
    public List<NatureExigence> getArchivedNatureExigence() {
        return natureExigenceService.getArchivedNatureExigence();
    }


    @RequestMapping(value = "/createNatureExigence", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public NatureExigence createNatureExigence( @RequestBody NatureExigence site) {
        return natureExigenceService.createNatureExigence(site);
    }

    @RequestMapping(value = "/natureExigence/{natureExigenceId}", method = RequestMethod.GET)
    public Optional<NatureExigence> getNatureExigenceById(@PathVariable(value = "natureExigenceId") Long categorieId) {
        return natureExigenceService.getNatureExigenceById(categorieId);
    }

    @RequestMapping(value = "/natureExigence/{natureExigenceId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public NatureExigence updateNatureExigenceById(@PathVariable(value = "natureExigenceId") Long siteId, @RequestBody NatureExigence site) {
        return natureExigenceService.updateNatureExigenceById(siteId, site);
    }

    @RequestMapping(value = "/NatureExigence/{natureExigenceId}/{isDelete}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteNatureExigenceById(@PathVariable(value = "natureExigenceId") Long siteId , @PathVariable(value = "isDelete") Boolean isDelete) {
        return natureExigenceService.deleteNatureExigenceById(siteId,isDelete);
    }

//-------------------------------------addCategorieToNonConformite------------------//

    @PostMapping(value = "/conformite/{conformiteId}/natureExigence/{natureExigenceId}")
    public void addNatureExigenceToConformite(@PathVariable(value = "conformiteId") Long actionId, @PathVariable(value = "natureExigenceId") Long siteId) {
        natureExigenceService.addNatureExigenceToConformite(actionId,siteId);
    }

    @PostMapping(value = "/conformites/{conformiteId}/natureExigence/{natureExigenceId}")
    public void removeNatureExigenceFromConformite(@PathVariable(value = "conformiteId") Long actionId, @PathVariable(value = "natureExigenceId") Long siteId) {
        natureExigenceService.removeNatureExigenceFromConformite(actionId,siteId);
    }




}
