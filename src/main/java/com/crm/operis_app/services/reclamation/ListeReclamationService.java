package com.crm.operis_app.services.reclamation;

import com.crm.operis_app.exception.ResourceNotFoundException;
import com.crm.operis_app.model.reclamation.ListeReclamation;
import com.crm.operis_app.repository.reclamation.ListeReclamationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ListeReclamationService {
    @Autowired
    ListeReclamationRepository listeReclamationRepository;

    public List<ListeReclamation> getReclamation() {
        return listeReclamationRepository.findByActiveIsTrueOrderByIdAsc();
    }

    public List<ListeReclamation> getArchivedReclamation() {
        return listeReclamationRepository.findByActiveIsFalseOrderByIdAsc();
    }
    public Optional<ListeReclamation> getReclamationById(Long reclamationId) {
        if (!listeReclamationRepository.existsById(reclamationId)) {
            throw new ResourceNotFoundException("Reclamation with id " + reclamationId + " not found");
        }
        return listeReclamationRepository.findById(reclamationId);
    }

    public ListeReclamation createReclamation(ListeReclamation listeReclamation) {
        return listeReclamationRepository.save(listeReclamation);
    }

    public ListeReclamation updateReclamationById(Long reclamationId, ListeReclamation ReclamationRequest) {
        if (!listeReclamationRepository.existsById(reclamationId)) {
            throw new ResourceNotFoundException("ListeReclamation with id " + reclamationId + " not found");
        }
        Optional<ListeReclamation> reclamation = listeReclamationRepository.findById(reclamationId);
        if (!reclamation.isPresent()) {
            throw new ResourceNotFoundException("ListeReclamation with id " + reclamationId + " not found");
        }
        ListeReclamation listeReclamation = reclamation.get();


        listeReclamation.setTypeReclamation(ReclamationRequest.getTypeReclamation());
        listeReclamation.setTelephone(ReclamationRequest.getTelephone());

        listeReclamation.setReclamant(ReclamationRequest.getReclamant());
        listeReclamation.setTauxTraitement(ReclamationRequest.getTauxTraitement());
        listeReclamation.setPriorite(ReclamationRequest.getPriorite());
        listeReclamation.setEmail(ReclamationRequest.getEmail());
        listeReclamation.setDescription(ReclamationRequest.getDescription());
        listeReclamation.setDateCreation(ReclamationRequest.getDateCreation());
        listeReclamation.setContact(ReclamationRequest.getContact());
        listeReclamation.setCommentaireTraitement(ReclamationRequest.getCommentaireTraitement());
        listeReclamation.setCommentaire(ReclamationRequest.getCommentaire());
        listeReclamation.setAdresse(ReclamationRequest.getAdresse());


        return listeReclamationRepository.save(listeReclamation);
    }


    public ResponseEntity<Object> deleteReclamationById(Long reclamationId) {
        if (!listeReclamationRepository.existsById(reclamationId)) {
            throw new ResourceNotFoundException("reclamationId with id " + reclamationId + " not found");
        }
        listeReclamationRepository.deleteById(reclamationId);
        return ResponseEntity.ok().build();
    }

}
