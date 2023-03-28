package com.crm.operis_app.services.conformite.Utils;

import com.crm.operis_app.exception.ResourceNotFoundException;
import com.crm.operis_app.model.conformite.ListeConformite;
import com.crm.operis_app.model.conformite.Utils.NatureExigence;
import com.crm.operis_app.repository.conformite.ListeConformiteRepository;
import com.crm.operis_app.repository.conformite.Utils.NatureExigenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NatureExigenceService {

    @Autowired
    NatureExigenceRepository natureExigenceRepository;
    @Autowired
    ListeConformiteRepository listeConformiteRepository;

    public List<NatureExigence> getNatureExigence() {
        return natureExigenceRepository.findByActiveIsTrueOrderByIdAsc();
    }

    public List<NatureExigence> getArchivedNatureExigence() {
        return natureExigenceRepository.findByActiveIsFalseOrderByIdAsc();
    }

    public Optional<NatureExigence> getNatureExigenceById(Long categorieId) {
        if (!natureExigenceRepository.existsById(categorieId)) {
            throw new ResourceNotFoundException("categorie with id " + categorieId + " not found");
        }
        return natureExigenceRepository.findById(categorieId);
    }

    public NatureExigence createNatureExigence(NatureExigence categorie) {
        return natureExigenceRepository.save(categorie);
    }

    public NatureExigence updateNatureExigenceById(Long categorieId, NatureExigence categorieRequest) {
        if (!natureExigenceRepository.existsById(categorieId)) {
            throw new ResourceNotFoundException("categorie with id " + categorieId + " not found");
        }
        Optional<NatureExigence> categorieNonConformite = natureExigenceRepository.findById(categorieId);
        if (!categorieNonConformite.isPresent()) {
            throw new ResourceNotFoundException("categorie with id " + categorieId + " not found");
        }
        NatureExigence categorieNc = categorieNonConformite.get();
        categorieNc.setNatureExigence(categorieRequest.getNatureExigence());
        categorieNc.setDescription(categorieRequest.getDescription());
        return natureExigenceRepository.save(categorieNc);
    }

    public ResponseEntity<Object> deleteNatureExigenceById(long categorieId, Boolean isDelete) {
        if (!natureExigenceRepository.existsById(categorieId)) {
            throw new ResourceNotFoundException("NatureExigence with id " + categorieId + " not found");
        }
        Optional<NatureExigence> categorieNonConformite = natureExigenceRepository.findById(categorieId);
        NatureExigence categorieNc = categorieNonConformite.get();
        categorieNc.setActive(isDelete);
        natureExigenceRepository.save(categorieNc);
//        siteRepository.deleteById(siteId);
        return ResponseEntity.ok().build();
    }

    //-------------------------------------addNatureExigenceToConformite------------------//
    public void addNatureExigenceToConformite(Long actionId, Long siteId) {
        Optional<NatureExigence> siteById = natureExigenceRepository.findById(siteId);
        if (!siteById.isPresent()) {
            throw new ResourceNotFoundException("site with id " + siteId + " does not exist");
        }
        NatureExigence site = siteById.get();

        Optional<ListeConformite> actionById = listeConformiteRepository.findById(actionId);
        if (!actionById.isPresent()) {
            throw new ResourceNotFoundException("action with id " + actionId + " does not exist");
        }
        ListeConformite action = actionById.get();

        action.getNatureExigences().add(site);
        listeConformiteRepository.save(action);
    }

    public void removeNatureExigenceFromConformite(Long actionId, Long siteId) {
        Optional<NatureExigence> siteById = natureExigenceRepository.findById(siteId);
        if (!siteById.isPresent()) {
            throw new ResourceNotFoundException("site with id " + siteId + " does not exist");
        }
        NatureExigence site = siteById.get();

        Optional<ListeConformite> actionById = listeConformiteRepository.findById(actionId);
        if (!actionById.isPresent()) {
            throw new ResourceNotFoundException("action with id " + actionId + " does not exist");
        }
        ListeConformite action = actionById.get();
        action.getNatureExigences().remove(site);
        listeConformiteRepository.save(action);
    }

}
