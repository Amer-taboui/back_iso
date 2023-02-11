package com.crm.operis_app.controller.Utils;
import com.crm.operis_app.model.Utils.Site;
import com.crm.operis_app.services.Utils.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins="*", allowedHeaders="*")
@RestController
@RequestMapping("/api")
public class SiteController {


    @Autowired
    SiteService siteService;

    @RequestMapping(value = "/sites", method = RequestMethod.GET)
    public List<Site> getSites() {
        return siteService.getSites();
    }

    @RequestMapping(value = "/archivedSites", method = RequestMethod.GET)
    public List<Site> getArchivedSites() {
        return siteService.getArchivedSites();
    }


    @RequestMapping(value = "/createSite", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Site createSite( @RequestBody Site site) {
        return siteService.createSite(site);
    }

    @RequestMapping(value = "/site/{siteId}", method = RequestMethod.GET)
    public Optional<Site> getSiteById(@PathVariable(value = "siteId") Long siteId) {
        return siteService.getSiteById(siteId);
    }

    @RequestMapping(value = "/site/{siteId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Site updateSite(@PathVariable(value = "siteId") Long siteId, @RequestBody Site site) {
        return siteService.updateSiteById(siteId, site);
    }

    @RequestMapping(value = "/site/{siteId}/{isDelete}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteSiteById(@PathVariable(value = "siteId") Long siteId , @PathVariable(value = "isDelete") Boolean isDelete) {
        return siteService.deleteSiteById(siteId,isDelete);
    }

  /*  @PostMapping(value = "/action/{actionId}/site/{siteId}")
    public void addSiteToAction(@PathVariable(value = "actionId") Long actionId, @PathVariable(value = "siteId") Long siteId) {
        siteService.addSiteToAction(actionId,siteId);
    }
    @PostMapping(value = "/actions/{actionId}/site/{siteId}")
    public void removeSiteFromAction(@PathVariable(value = "actionId") Long actionId, @PathVariable(value = "siteId") Long siteId) {
        siteService.removeSiteFromAction(actionId,siteId);
    }

*/

}
