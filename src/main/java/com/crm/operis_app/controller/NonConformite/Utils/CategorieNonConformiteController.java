package com.crm.operis_app.controller.NonConformite.Utils;

import com.crm.operis_app.model.NonConformite.Utils.CategorieNonConformite;
import com.crm.operis_app.services.NonConformite.Utils.CategorieNonConformiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins="*", allowedHeaders="*")
@RestController
@RequestMapping("/api")
public class CategorieNonConformiteController {
    @Autowired
    CategorieNonConformiteService categorieNonConformiteService;

    @RequestMapping(value = "/Categorie", method = RequestMethod.GET)
    public List<CategorieNonConformite> getSites() {
        return categorieNonConformiteService.getCategorieNonConformite();
    }

    @RequestMapping(value = "/archivedCategorie", method = RequestMethod.GET)
    public List<CategorieNonConformite> getArchivedSites() {
        return categorieNonConformiteService.getArchivedCategorieNonConformite();
    }


    @RequestMapping(value = "/createCategorie", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public CategorieNonConformite createSite( @RequestBody CategorieNonConformite site) {
        return categorieNonConformiteService.createCategorieNonConformite(site);
    }

    @RequestMapping(value = "/Categorie/{categorieId}", method = RequestMethod.GET)
    public Optional<CategorieNonConformite> getSiteById(@PathVariable(value = "categorieId") Long categorieId) {
        return categorieNonConformiteService.getCategorieNonConformiteById(categorieId);
    }

    @RequestMapping(value = "/categorie/{categorieId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public CategorieNonConformite updateSite(@PathVariable(value = "categorieId") Long siteId, @RequestBody CategorieNonConformite site) {
        return categorieNonConformiteService.updateCategorieNonConformiteById(siteId, site);
    }

    @RequestMapping(value = "/Categorie/{categorieId}/{isDelete}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteSiteById(@PathVariable(value = "categorieId") Long siteId , @PathVariable(value = "isDelete") Boolean isDelete) {
        return categorieNonConformiteService.deleteCategorieNonConformiteById(siteId,isDelete);
    }

//-------------------------------------addCategorieToNonConformite------------------//

    @PostMapping(value = "/nonConformite/{nonConformiteId}/categorie/{categorieId}")
    public void addCategorieToNonConformite(@PathVariable(value = "nonConformiteId") Long actionId, @PathVariable(value = "categorieId") Long siteId) {
        categorieNonConformiteService.addCategorieToNonConformite(actionId,siteId);
    }

    @PostMapping(value = "/nonConformites/{nonConformiteId}/categorie/{categorieId}")
    public void removeCategorieFromNonConformite(@PathVariable(value = "nonConformiteId") Long actionId, @PathVariable(value = "categorieId") Long siteId) {
        categorieNonConformiteService.removeCategorieFromNonConformite(actionId,siteId);
    }




}
