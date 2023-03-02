package com.crm.operis_app.services.NonConformite.Utils;

import com.crm.operis_app.exception.ResourceNotFoundException;
import com.crm.operis_app.model.NonConformite.Utils.CategorieNonConformite;
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
}
