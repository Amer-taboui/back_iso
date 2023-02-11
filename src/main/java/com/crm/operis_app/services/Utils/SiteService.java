package com.crm.operis_app.services.Utils;
import com.crm.operis_app.exception.ResourceNotFoundException;
import com.crm.operis_app.model.Utils.Site;
import com.crm.operis_app.model.action.actionCorrection.ListeActionCorrection;
import com.crm.operis_app.repository.Utils.SiteRepository;
import com.crm.operis_app.repository.action.ListeActionCorrectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class SiteService {

    @Autowired
    SiteRepository siteRepository;



    @Autowired
    ListeActionCorrectionRepository liste_actionRepository;




    public List<Site> getSites() {
        return siteRepository.findByActiveIsTrueOrderBySiteIdAsc();
    }

    public List<Site> getArchivedSites() {
        return siteRepository.findByActiveIsFalseOrderBySiteIdAsc();
    }

    public Optional<Site> getSiteById(Long siteId) {
        if (!siteRepository.existsById(siteId)) {
            throw new ResourceNotFoundException("SITE with id " + siteId + " not found");
        }
        return siteRepository.findById(siteId);
    }

    public Site createSite( Site site) {
        return siteRepository.save(site);
    }

    public Site updateSiteById(Long siteId, Site SiteRequest) {
        if (!siteRepository.existsById(siteId)) {
            throw new ResourceNotFoundException("SITE with id " + siteId + " not found");
        }
        Optional<Site> site = siteRepository.findById(siteId);
        if (!site.isPresent()) {
            throw new ResourceNotFoundException("SITE with id " + siteId + " not found");
        }
        Site site1 = site.get();
        site1.setSiteName(SiteRequest.getSiteName());
        site1.setType(SiteRequest.getType());
        return siteRepository.save(site1);
    }

    public ResponseEntity<Object> deleteSiteById(long siteId,Boolean isDelete) {
        if (!siteRepository.existsById(siteId)) {
            throw new ResourceNotFoundException("Book with id " + siteId + " not found");
        }
        Optional<Site> site = siteRepository.findById(siteId);
        Site site1=site.get();
        site1.setActive(isDelete);
        siteRepository.save(site1);
//        siteRepository.deleteById(siteId);
        return ResponseEntity.ok().build();
    }




   /* public void addSiteToAction(Long actionId, Long siteId) {
        Optional<Site> siteById = siteRepository.findById(siteId);
        if (!siteById.isPresent()) {
            throw new ResourceNotFoundException("site with id " + siteId + " does not exist");
        }
        Site site = siteById.get();

        Optional<ListeActionCorrection> actionById = liste_actionRepository.findById(actionId);
        if (!actionById.isPresent()) {
            throw new ResourceNotFoundException("action with id " + actionId + " does not exist");
        }
        ListeActionCorrection action = actionById.get();

        action.getSites().add(site);
        liste_actionRepository.save(action);
    }*/

   /* public void removeSiteFromAction(Long actionId, Long siteId) {
        Optional<Site> siteById = siteRepository.findById(siteId);
        if (!siteById.isPresent()) {
            throw new ResourceNotFoundException("site with id " + siteId + " does not exist");
        }
        Site site = siteById.get();

        Optional<ListeActionCorrection> actionById = liste_actionRepository.findById(actionId);
        if (!actionById.isPresent()) {
            throw new ResourceNotFoundException("action with id " + actionId + " does not exist");
        }
        ListeActionCorrection action = actionById.get();
        action.getSites().remove(site);
        liste_actionRepository.save(action);
    }
*/




}