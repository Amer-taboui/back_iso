package com.crm.operis_app.controller.NonConformite.Utils;

import com.crm.operis_app.model.NonConformite.Utils.CategorieNonConformite;
import com.crm.operis_app.model.NonConformite.Utils.GraviteNonConformite;
import com.crm.operis_app.model.NonConformite.Utils.OrigineNonConformite;
import com.crm.operis_app.services.NonConformite.Utils.GraviteNonConformiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins="*", allowedHeaders="*")
@RestController
@RequestMapping("/api")
public class GraviteNonConformiteController {

    @Autowired
    GraviteNonConformiteService graviteNonConformiteService;

    @RequestMapping(value = "/Gravite", method = RequestMethod.GET)
    public List<GraviteNonConformite> getGravite() {
        return graviteNonConformiteService.getGraviteNonConformite();
    }

    @RequestMapping(value = "/archivedGravite", method = RequestMethod.GET)
    public List<GraviteNonConformite> getArchivedGravite() {
        return graviteNonConformiteService.getArchivedGraviteNonConformite();
    }


    @RequestMapping(value = "/createGravite", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public GraviteNonConformite createGravite( @RequestBody GraviteNonConformite site) {
        return graviteNonConformiteService.createGraviteNonConformite(site);
    }

    @RequestMapping(value = "/Gravite/{graviteId}", method = RequestMethod.GET)
    public Optional<GraviteNonConformite> getGraviteById(@PathVariable(value = "graviteId") Long graviteId) {
        return graviteNonConformiteService.getGraviteNonConformiteById(graviteId);
    }




    @RequestMapping(value = "/gravite/{graviteId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public GraviteNonConformite updateGravite(@PathVariable(value = "graviteId") Long graviteId, @RequestBody GraviteNonConformite site) {
        return graviteNonConformiteService.updateGraviteNonConformiteById(graviteId, site);
    }

    @RequestMapping(value = "/Gravite/{graviteId}/{isDelete}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteGraviteById(@PathVariable(value = "graviteId") Long siteId , @PathVariable(value = "isDelete") Boolean isDelete) {
        return graviteNonConformiteService.deleteGraviteNonConformiteById(siteId,isDelete);
    }
}
