package com.crm.operis_app.services.NonConformite.Utils;

import com.crm.operis_app.exception.ResourceNotFoundException;
import com.crm.operis_app.model.NonConformite.ListeNonConformite;
import com.crm.operis_app.model.NonConformite.Utils.CategorieNonConformite;
import com.crm.operis_app.model.NonConformite.Utils.GraviteNonConformite;
import com.crm.operis_app.model.NonConformite.Utils.OrigineNonConformite;
import com.crm.operis_app.repository.NonConformite.ListeNonConformiteRepository;
import com.crm.operis_app.repository.NonConformite.Utils.OrigineNonConformiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrigineNonConformiteService {

    @Autowired
    OrigineNonConformiteRepository origineNonConformiteRepository;

    @Autowired
    ListeNonConformiteRepository listeNonConformiteRepository;

    public List<OrigineNonConformite> getOrigineNonConformite() {
        return origineNonConformiteRepository.findByActiveIsTrueOrderByOrigineIdAsc();
    }

    public List<OrigineNonConformite> getArchivedOrigineNonConformite() {
        return origineNonConformiteRepository.findByActiveIsFalseOrderByOrigineIdAsc();
    }

    public Optional<OrigineNonConformite> getOrigineNonConformiteById(Long origineId) {
        if (!origineNonConformiteRepository.existsById(origineId)) {
            throw new ResourceNotFoundException("origine with id " + origineId + " not found");
        }
        return origineNonConformiteRepository.findById(origineId);
    }

    public OrigineNonConformite createOrigineNonConformite( OrigineNonConformite categorie) {
        return origineNonConformiteRepository.save(categorie);
    }

    public OrigineNonConformite updateOrigineNonConformiteById(Long origineId, OrigineNonConformite categorieRequest) {
        if (!origineNonConformiteRepository.existsById(origineId)) {
            throw new ResourceNotFoundException("origine with id " + origineId + " not found");
        }
        Optional<OrigineNonConformite> categorieNonConformite = origineNonConformiteRepository.findById(origineId);
        if (!categorieNonConformite.isPresent()) {
            throw new ResourceNotFoundException("origine with id " + origineId + " not found");
        }
        OrigineNonConformite categorieNc = categorieNonConformite.get();
        categorieNc.setOrigineNc(categorieRequest.getOrigineNc());
        categorieNc.setDescriptionOrigine(categorieRequest.getDescriptionOrigine());
        return origineNonConformiteRepository.save(categorieNc);
    }

    public ResponseEntity<Object> deleteOrigineNonConformiteById(long origineId, Boolean isDelete) {
        if (!origineNonConformiteRepository.existsById(origineId)) {
            throw new ResourceNotFoundException("origine with id " + origineId + " not found");
        }
        Optional<OrigineNonConformite> categorieNonConformite = origineNonConformiteRepository.findById(origineId);
        OrigineNonConformite categorieNc=categorieNonConformite.get();
        categorieNc.setActive(isDelete);
        origineNonConformiteRepository.save(categorieNc);
//        siteRepository.deleteById(siteId);
        return ResponseEntity.ok().build();
    }

    //-------------------------------------addCategorieToNonConformite------------------//
    public void addOrigineToNonConformite(Long nonConformiteId, Long origineId) {
        Optional<OrigineNonConformite> origineById = origineNonConformiteRepository.findById(origineId);
        if (!origineById.isPresent()) {
            throw new ResourceNotFoundException("origine with id " + origineId + " does not exist");
        }
        OrigineNonConformite origineNonConformite = origineById.get();

        Optional<ListeNonConformite> nonConformiteById = listeNonConformiteRepository.findById(nonConformiteId);
        if (!nonConformiteById.isPresent()) {
            throw new ResourceNotFoundException("Nc with id " + nonConformiteId + " does not exist");
        }
        ListeNonConformite listeNonConformite = nonConformiteById.get();

        listeNonConformite.getOrigineNonConformite().add(origineNonConformite);
        listeNonConformiteRepository.save(listeNonConformite);
    }

    public void removeOrigineFromNonConformite(Long nonConformiteId, Long origineId) {
        Optional<OrigineNonConformite> origineById = origineNonConformiteRepository.findById(origineId);
        if (!origineById.isPresent()) {
            throw new ResourceNotFoundException("origine with id " + origineId + " does not exist");
        }
        OrigineNonConformite origineNonConformite = origineById.get();

        Optional<ListeNonConformite> nonConformiteById = listeNonConformiteRepository.findById(nonConformiteId);
        if (!nonConformiteById.isPresent()) {
            throw new ResourceNotFoundException("Nc with id " + nonConformiteId + " does not exist");
        }
        ListeNonConformite listeNonConformite = nonConformiteById.get();
        listeNonConformite.getOrigineNonConformite().remove(origineNonConformite);
        listeNonConformiteRepository.save(listeNonConformite);
    }

}
