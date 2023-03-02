package com.crm.operis_app.services.NonConformite;

import com.crm.operis_app.exception.ResourceNotFoundException;
import com.crm.operis_app.model.NonConformite.ListeNonConformite;
import com.crm.operis_app.model.reclamation.ListeReclamation;
import com.crm.operis_app.repository.NonConformite.ListeNonConformiteRepository;
import com.crm.operis_app.repository.reclamation.ListeReclamationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ListeNonConformiteService {

    @Autowired
    ListeNonConformiteRepository listeNonConformiteRepository;

    public List<ListeNonConformite> getListeNonConformite() {
        return listeNonConformiteRepository.findByActiveIsTrueOrderByIdAsc();
    }

    public List<ListeNonConformite> getArchivedListeNonConformite() {
        return listeNonConformiteRepository.findByActiveIsFalseOrderByIdAsc();
    }
    public Optional<ListeNonConformite> getListeNonConformiteById(Long reclamationId) {
        if (!listeNonConformiteRepository.existsById(reclamationId)) {
            throw new ResourceNotFoundException("listeNonConformite with id " + reclamationId + " not found");
        }
        return listeNonConformiteRepository.findById(reclamationId);
    }

    public ListeNonConformite createListeNonConformite(ListeNonConformite listeReclamation) {
        return listeNonConformiteRepository.save(listeReclamation);
    }

    public ListeNonConformite updateListeNonConformiteById(Long reclamationId, ListeNonConformite ReclamationRequest) {
        if (!listeNonConformiteRepository.existsById(reclamationId)) {
            throw new ResourceNotFoundException("listeNonConformite with id " + reclamationId + " not found");
        }
        Optional<ListeNonConformite> reclamation = listeNonConformiteRepository.findById(reclamationId);
        if (!reclamation.isPresent()) {
            throw new ResourceNotFoundException("listeNonConformite with id " + reclamationId + " not found");
        }
        ListeNonConformite listeReclamation = reclamation.get();


        listeReclamation.setCommentaire(ReclamationRequest.getCommentaire());
        listeReclamation.setDateCreation(ReclamationRequest.getDateCreation());

        listeReclamation.setTauxTraitement(ReclamationRequest.getTauxTraitement());
        listeReclamation.setAnalyseResultat(ReclamationRequest.getAnalyseResultat());
        listeReclamation.setDesignationNc(ReclamationRequest.getDesignationNc());
        listeReclamation.setEtatAnalyse(ReclamationRequest.getEtatAnalyse());
        listeReclamation.setResponsableAnalyse(ReclamationRequest.getResponsableAnalyse());
        listeReclamation.setResponsableDecouverte(ReclamationRequest.getResponsableDecouverte());


        return listeNonConformiteRepository.save(listeReclamation);
    }


    public ResponseEntity<Object> deleteListeNonConformiteById(Long reclamationId) {
        if (!listeNonConformiteRepository.existsById(reclamationId)) {
            throw new ResourceNotFoundException("listeNonConformite with id " + reclamationId + " not found");
        }
        listeNonConformiteRepository.deleteById(reclamationId);
        return ResponseEntity.ok().build();
    }

}


