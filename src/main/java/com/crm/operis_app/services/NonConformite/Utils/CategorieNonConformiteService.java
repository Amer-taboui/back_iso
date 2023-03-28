package com.crm.operis_app.services.NonConformite.Utils;

import com.crm.operis_app.exception.ResourceNotFoundException;
import com.crm.operis_app.model.NonConformite.ListeNonConformite;
import com.crm.operis_app.model.NonConformite.Utils.CategorieNonConformite;
import com.crm.operis_app.repository.NonConformite.ListeNonConformiteRepository;
import com.crm.operis_app.repository.NonConformite.Utils.CategorieNonConformiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategorieNonConformiteService {
    @Autowired
    CategorieNonConformiteRepository categorieNonConformiteRepository;
    @Autowired
    ListeNonConformiteRepository listeNonConformiteRepository;

    public List<CategorieNonConformite> getCategorieNonConformite() {
        return categorieNonConformiteRepository.findByActiveIsTrueOrderByCategorieIdAsc();
    }

    public List<CategorieNonConformite> getArchivedCategorieNonConformite() {
        return categorieNonConformiteRepository.findByActiveIsFalseOrderByCategorieIdAsc();
    }

    public Optional<CategorieNonConformite> getCategorieNonConformiteById(Long categorieId) {
        if (!categorieNonConformiteRepository.existsById(categorieId)) {
            throw new ResourceNotFoundException("categorie with id " + categorieId + " not found");
        }
        return categorieNonConformiteRepository.findById(categorieId);
    }

    public CategorieNonConformite createCategorieNonConformite( CategorieNonConformite categorie) {
        return categorieNonConformiteRepository.save(categorie);
    }

    public CategorieNonConformite updateCategorieNonConformiteById(Long categorieId, CategorieNonConformite categorieRequest) {
        if (!categorieNonConformiteRepository.existsById(categorieId)) {
            throw new ResourceNotFoundException("categorie with id " + categorieId + " not found");
        }
        Optional<CategorieNonConformite> categorieNonConformite = categorieNonConformiteRepository.findById(categorieId);
        if (!categorieNonConformite.isPresent()) {
            throw new ResourceNotFoundException("categorie with id " + categorieId + " not found");
        }
        CategorieNonConformite categorieNc = categorieNonConformite.get();
        categorieNc.setCategorieNc(categorieRequest.getCategorieNc());
        categorieNc.setDescriptionNc(categorieRequest.getDescriptionNc());
        return categorieNonConformiteRepository.save(categorieNc);
    }

    public ResponseEntity<Object> deleteCategorieNonConformiteById(long categorieId, Boolean isDelete) {
        if (!categorieNonConformiteRepository.existsById(categorieId)) {
            throw new ResourceNotFoundException("categorieNonConformite with id " + categorieId + " not found");
        }
        Optional<CategorieNonConformite> categorieNonConformite = categorieNonConformiteRepository.findById(categorieId);
        CategorieNonConformite categorieNc=categorieNonConformite.get();
        categorieNc.setActive(isDelete);
        categorieNonConformiteRepository.save(categorieNc);
//        siteRepository.deleteById(siteId);
        return ResponseEntity.ok().build();
    }

    //-------------------------------------addCategorieToNonConformite------------------//
    public void addCategorieToNonConformite(Long actionId, Long siteId) {
        Optional<CategorieNonConformite> siteById = categorieNonConformiteRepository.findById(siteId);
        if (!siteById.isPresent()) {
            throw new ResourceNotFoundException("site with id " + siteId + " does not exist");
        }
        CategorieNonConformite site = siteById.get();

        Optional<ListeNonConformite> actionById = listeNonConformiteRepository.findById(actionId);
        if (!actionById.isPresent()) {
            throw new ResourceNotFoundException("action with id " + actionId + " does not exist");
        }
        ListeNonConformite action = actionById.get();

        action.getCategorieNonConformite().add(site);
        listeNonConformiteRepository.save(action);
    }

    public void removeCategorieFromNonConformite(Long actionId, Long siteId) {
        Optional<CategorieNonConformite> siteById = categorieNonConformiteRepository.findById(siteId);
        if (!siteById.isPresent()) {
            throw new ResourceNotFoundException("site with id " + siteId + " does not exist");
        }
        CategorieNonConformite site = siteById.get();

        Optional<ListeNonConformite> actionById = listeNonConformiteRepository.findById(actionId);
        if (!actionById.isPresent()) {
            throw new ResourceNotFoundException("action with id " + actionId + " does not exist");
        }
        ListeNonConformite action = actionById.get();
        action.getCategorieNonConformite().remove(site);
        listeNonConformiteRepository.save(action);
    }

}
