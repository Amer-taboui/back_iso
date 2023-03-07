package com.crm.operis_app.controller.NonConformite.Utils;

import com.crm.operis_app.model.NonConformite.Utils.OrigineNonConformite;
import com.crm.operis_app.services.NonConformite.Utils.OrigineNonConformiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins="*", allowedHeaders="*")
@RestController
@RequestMapping("/api")
public class OrigineNonConformiteController {

    @Autowired
    OrigineNonConformiteService origineNonConformiteService;

    @RequestMapping(value = "/Origine", method = RequestMethod.GET)
    public List<OrigineNonConformite> getOrigineNonConformite() {
        return origineNonConformiteService.getOrigineNonConformite();
    }

    @RequestMapping(value = "/archivedOrigine", method = RequestMethod.GET)
    public List<OrigineNonConformite> getArchivedOrigineNonConformite() {
        return origineNonConformiteService.getArchivedOrigineNonConformite();
    }


    @RequestMapping(value = "/createOrigine", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public OrigineNonConformite createOrigineNonConformite( @RequestBody OrigineNonConformite site) {
        return origineNonConformiteService.createOrigineNonConformite(site);
    }

    @RequestMapping(value = "/Origine/{origineId}", method = RequestMethod.GET)
    public Optional<OrigineNonConformite> getOrigineNonConformiteById(@PathVariable(value = "origineId") Long categorieId) {
        return origineNonConformiteService.getOrigineNonConformiteById(categorieId);
    }

    @RequestMapping(value = "/origine/{origineId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public OrigineNonConformite updateOrigineNonConformite(@PathVariable(value = "origineId") Long origineId, @RequestBody OrigineNonConformite site) {
        return origineNonConformiteService.updateOrigineNonConformiteById(origineId, site);
    }

    @RequestMapping(value = "/Origine/{origineId}/{isDelete}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteOrigineNonConformiteById(@PathVariable(value = "origineId") Long origineId , @PathVariable(value = "isDelete") Boolean isDelete) {
        return origineNonConformiteService.deleteOrigineNonConformiteById(origineId,isDelete);
    }

    //-------------------------------------addOrigineToNonConformite------------------//

    @PostMapping(value = "/nonConformite/{nonConformiteId}/origine/{origineId}")
    public void addOrigineToNonConformite(@PathVariable(value = "nonConformiteId") Long actionId, @PathVariable(value = "origineId") Long siteId) {
        origineNonConformiteService.addOrigineToNonConformite(actionId,siteId);
    }

    @PostMapping(value = "/nonConformites/{nonConformiteId}/origine/{origineId}")
    public void removeOrigineFromNonConformite(@PathVariable(value = "nonConformiteId") Long actionId, @PathVariable(value = "origineId") Long siteId) {
        origineNonConformiteService.removeOrigineFromNonConformite(actionId,siteId);
    }

}
