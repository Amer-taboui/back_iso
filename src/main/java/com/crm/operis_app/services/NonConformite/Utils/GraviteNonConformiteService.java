package com.crm.operis_app.services.NonConformite.Utils;

import com.crm.operis_app.exception.ResourceNotFoundException;
import com.crm.operis_app.model.NonConformite.Utils.GraviteNonConformite;
import com.crm.operis_app.repository.NonConformite.Utils.GraviteNonConformiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GraviteNonConformiteService {

    @Autowired
    GraviteNonConformiteRepository graviteNonConformiteRepository;

    public List<GraviteNonConformite> getGraviteNonConformite() {
        return graviteNonConformiteRepository.findByActiveIsTrueOrderByGraviteIdAsc();
    }

    public List<GraviteNonConformite> getArchivedGraviteNonConformite() {
        return graviteNonConformiteRepository.findByActiveIsFalseOrderByGraviteIdAsc();
    }

    public Optional<GraviteNonConformite> getGraviteNonConformiteById(Long categorieId) {
        if (!graviteNonConformiteRepository.existsById(categorieId)) {
            throw new ResourceNotFoundException("GraviteNonConformite with id " + categorieId + " not found");
        }
        return graviteNonConformiteRepository.findById(categorieId);
    }

    public GraviteNonConformite createGraviteNonConformite( GraviteNonConformite categorie) {
        return graviteNonConformiteRepository.save(categorie);
    }

    public GraviteNonConformite updateGraviteNonConformiteById(Long categorieId, GraviteNonConformite categorieRequest) {
        if (!graviteNonConformiteRepository.existsById(categorieId)) {
            throw new ResourceNotFoundException("GraviteNonConformite with id " + categorieId + " not found");
        }
        Optional<GraviteNonConformite> categorieNonConformite = graviteNonConformiteRepository.findById(categorieId);
        if (!categorieNonConformite.isPresent()) {
            throw new ResourceNotFoundException("GraviteNonConformite with id " + categorieId + " not found");
        }
        GraviteNonConformite categorieNc = categorieNonConformite.get();
        categorieNc.setGraviteNc(categorieRequest.getGraviteNc());
        categorieNc.setDescriptionGravite(categorieRequest.getDescriptionGravite());
        return graviteNonConformiteRepository.save(categorieNc);
    }

    public ResponseEntity<Object> deleteGraviteNonConformiteById(long categorieId, Boolean isDelete) {
        if (!graviteNonConformiteRepository.existsById(categorieId)) {
            throw new ResourceNotFoundException("GraviteNonConformite with id " + categorieId + " not found");
        }
        Optional<GraviteNonConformite> categorieNonConformite = graviteNonConformiteRepository.findById(categorieId);
        GraviteNonConformite categorieNc=categorieNonConformite.get();
        categorieNc.setActive(isDelete);
        graviteNonConformiteRepository.save(categorieNc);
//        siteRepository.deleteById(siteId);
        return ResponseEntity.ok().build();
    }
}
