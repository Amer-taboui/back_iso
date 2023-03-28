package com.crm.operis_app.services.conformite.Utils;

import com.crm.operis_app.exception.ResourceNotFoundException;
import com.crm.operis_app.model.conformite.ListeConformite;
import com.crm.operis_app.model.conformite.Utils.DomaineConformite;
import com.crm.operis_app.repository.conformite.ListeConformiteRepository;
import com.crm.operis_app.repository.conformite.Utils.DomaineConformiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DomaineConformiteService {
    @Autowired
    DomaineConformiteRepository domaineConformiteRepository;
    @Autowired
    ListeConformiteRepository listeConformiteRepository;

    public List<DomaineConformite> getDomaineConformite() {
        return domaineConformiteRepository.findByActiveIsTrueOrderByIdAsc();
    }

    public List<DomaineConformite> getArchivedDomaineConformite() {
        return domaineConformiteRepository.findByActiveIsFalseOrderByIdAsc();
    }

    public Optional<DomaineConformite> getDomaineConformiteById(Long categorieId) {
        if (!domaineConformiteRepository.existsById(categorieId)) {
            throw new ResourceNotFoundException("DomaineConformite with id " + categorieId + " not found");
        }
        return domaineConformiteRepository.findById(categorieId);
    }

    public DomaineConformite createDomaineConformite(DomaineConformite categorie) {
        return domaineConformiteRepository.save(categorie);
    }

    public DomaineConformite updateDomaineConformiteById(Long categorieId, DomaineConformite categorieRequest) {
        if (!domaineConformiteRepository.existsById(categorieId)) {
            throw new ResourceNotFoundException("DomaineConformite with id " + categorieId + " not found");
        }
        Optional<DomaineConformite> categorieNonConformite = domaineConformiteRepository.findById(categorieId);
        if (!categorieNonConformite.isPresent()) {
            throw new ResourceNotFoundException("DomaineConformite with id " + categorieId + " not found");
        }
        DomaineConformite categorieNc = categorieNonConformite.get();
        categorieNc.setDomaine(categorieRequest.getDomaine());
        categorieNc.setSousDomaineConformite(categorieRequest.getSousDomaineConformite());
        return domaineConformiteRepository.save(categorieNc);
    }

    public ResponseEntity<Object> deleteDomaineConformiteById(long categorieId, Boolean isDelete) {
        if (!domaineConformiteRepository.existsById(categorieId)) {
            throw new ResourceNotFoundException("DomaineConformite with id " + categorieId + " not found");
        }
        Optional<DomaineConformite> categorieNonConformite = domaineConformiteRepository.findById(categorieId);
        DomaineConformite categorieNc = categorieNonConformite.get();
        categorieNc.setActive(isDelete);
        domaineConformiteRepository.save(categorieNc);
//        siteRepository.deleteById(siteId);
        return ResponseEntity.ok().build();
    }

    //-------------------------------------addDomaineConformiteToConformite------------------//
    public void addDomaineConformiteToConformite(Long actionId, Long siteId) {
        Optional<DomaineConformite> siteById = domaineConformiteRepository.findById(siteId);
        if (!siteById.isPresent()) {
            throw new ResourceNotFoundException("DomaineConformite with id " + siteId + " does not exist");
        }
        DomaineConformite site = siteById.get();

        Optional<ListeConformite> actionById = listeConformiteRepository.findById(actionId);
        if (!actionById.isPresent()) {
            throw new ResourceNotFoundException("DomaineConformite with id " + actionId + " does not exist");
        }
        ListeConformite action = actionById.get();

        action.getDomaineConformite().add(site);
        listeConformiteRepository.save(action);
    }

    public void removeDomaineConformiteFromConformite(Long actionId, Long siteId) {
        Optional<DomaineConformite> siteById = domaineConformiteRepository.findById(siteId);
        if (!siteById.isPresent()) {
            throw new ResourceNotFoundException("DomaineConformite with id " + siteId + " does not exist");
        }
        DomaineConformite site = siteById.get();

        Optional<ListeConformite> actionById = listeConformiteRepository.findById(actionId);
        if (!actionById.isPresent()) {
            throw new ResourceNotFoundException("DomaineConformite with id " + actionId + " does not exist");
        }
        ListeConformite action = actionById.get();
        action.getDomaineConformite().remove(site);
        listeConformiteRepository.save(action);
    }

}

