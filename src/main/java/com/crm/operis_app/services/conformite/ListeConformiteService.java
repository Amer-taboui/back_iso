package com.crm.operis_app.services.conformite;

import com.crm.operis_app.exception.ResourceNotFoundException;
import com.crm.operis_app.model.conformite.ListeConformite;
import com.crm.operis_app.repository.conformite.ListeConformiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ListeConformiteService {
    @Autowired
    ListeConformiteRepository listeConformiteRepository;

    public List<ListeConformite> getListeConformite() {
        return listeConformiteRepository.findByActiveIsTrueOrderByIdAsc();
    }

    public List<ListeConformite> getArchivedListeConformite() {
        return listeConformiteRepository.findByActiveIsFalseOrderByIdAsc();
    }
    public Optional<ListeConformite> getListeConformiteById(Long conformiteId) {
        if (!listeConformiteRepository.existsById(conformiteId)) {
            throw new ResourceNotFoundException("listeConformite with id " + conformiteId + " not found");
        }
        return listeConformiteRepository.findById(conformiteId);
    }

    public ListeConformite createListeConformite(ListeConformite listeReclamation) {
        return listeConformiteRepository.save(listeReclamation);
    }

    public ListeConformite updateListeConformiteById(Long conformiteId, ListeConformite ReclamationRequest) {
        if (!listeConformiteRepository.existsById(conformiteId)) {
            throw new ResourceNotFoundException("listeConformite with id " + conformiteId + " not found");
        }
        Optional<ListeConformite> reclamation = listeConformiteRepository.findById(conformiteId);
        if (!reclamation.isPresent()) {
            throw new ResourceNotFoundException("listeConformite with id " + conformiteId + " not found");
        }
        ListeConformite listeReclamation = reclamation.get();


        listeReclamation.setCommentaire(ReclamationRequest.getCommentaire());
        listeReclamation.setDateApplication(ReclamationRequest.getDateApplication());

        listeReclamation.setTauxTraitement(ReclamationRequest.getTauxTraitement());
        listeReclamation.setConformite(ReclamationRequest.getConformite());
        listeReclamation.setAnalyseConformite(ReclamationRequest.getAnalyseConformite());
        listeReclamation.setApplicabilite(ReclamationRequest.getApplicabilite());
        listeReclamation.setDomaine(ReclamationRequest.getDomaine());
        listeReclamation.setNatureExigence(ReclamationRequest.getNatureExigence());
        listeReclamation.setSousDomaine(ReclamationRequest.getSousDomaine());
        listeReclamation.setSyntheseExigence(ReclamationRequest.getSyntheseExigence());
        listeReclamation.setSysteme(ReclamationRequest.getSysteme());

        return listeConformiteRepository.save(listeReclamation);
    }


    public ResponseEntity<Object> deleteListeConformiteById(Long conformiteId) {
        if (!listeConformiteRepository.existsById(conformiteId)) {
            throw new ResourceNotFoundException("listeConformite with id " + conformiteId + " not found");
        }
        listeConformiteRepository.deleteById(conformiteId);
        return ResponseEntity.ok().build();
    }
}
