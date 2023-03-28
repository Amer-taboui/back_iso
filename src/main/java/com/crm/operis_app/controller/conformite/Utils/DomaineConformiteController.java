package com.crm.operis_app.controller.conformite.Utils;

import com.crm.operis_app.model.conformite.Utils.DomaineConformite;
import com.crm.operis_app.model.conformite.Utils.NatureExigence;
import com.crm.operis_app.services.conformite.Utils.DomaineConformiteService;
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
public class DomaineConformiteController {
    @Autowired
    DomaineConformiteService domaineConformiteService;

    @RequestMapping(value = "/domaineConformite", method = RequestMethod.GET)
    public List<DomaineConformite> getDomaineConformite() {
        return domaineConformiteService.getDomaineConformite();
    }

    @RequestMapping(value = "/archivedDomaineConformite", method = RequestMethod.GET)
    public List<DomaineConformite> getArchivedDomaineConformite() {
        return domaineConformiteService.getArchivedDomaineConformite();
    }


    @RequestMapping(value = "/createDomaineConformite", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public DomaineConformite createDomaineConformite( @RequestBody DomaineConformite site) {
        return domaineConformiteService.createDomaineConformite(site);
    }

    @RequestMapping(value = "/domaineConformite/{domaineConformiteId}", method = RequestMethod.GET)
    public Optional<DomaineConformite> getDomaineConformiteById(@PathVariable(value = "domaineConformiteId") Long categorieId) {
        return domaineConformiteService.getDomaineConformiteById(categorieId);
    }

    @RequestMapping(value = "/domaineConformite/{natureExigenceId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public DomaineConformite updateDomaineConformiteById(@PathVariable(value = "domaineConformiteId") Long siteId, @RequestBody DomaineConformite site) {
        return domaineConformiteService.updateDomaineConformiteById(siteId, site);
    }

    @RequestMapping(value = "/DomaineConformite/{domaineConformiteId}/{isDelete}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteDomaineConformiteById(@PathVariable(value = "domaineConformiteId") Long siteId , @PathVariable(value = "isDelete") Boolean isDelete) {
        return domaineConformiteService.deleteDomaineConformiteById(siteId,isDelete);
    }

//-------------------------------------addCategorieToNonConformite------------------//

    @PostMapping(value = "/conformite/{conformiteId}/domaineConformite/{domaineConformiteId}")
    public void addDomaineConformiteToConformite(@PathVariable(value = "conformiteId") Long actionId, @PathVariable(value = "domaineConformiteId") Long siteId) {
        domaineConformiteService.addDomaineConformiteToConformite(actionId,siteId);
    }

    @PostMapping(value = "/conformites/{conformiteId}/domaineConformite/{domaineConformiteId}")
    public void removeDomaineConformiteFromConformite(@PathVariable(value = "conformiteId") Long actionId, @PathVariable(value = "domaineConformiteId") Long siteId) {
        domaineConformiteService.removeDomaineConformiteFromConformite(actionId,siteId);
    }




}

