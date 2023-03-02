package com.crm.operis_app.services.reclamation;

import com.crm.operis_app.exception.ResourceNotFoundException;
import com.crm.operis_app.model.action.utils.ActionCost;
import com.crm.operis_app.model.reclamation.ListeReclamation;
import com.crm.operis_app.model.reclamation.ReclamationCost;
import com.crm.operis_app.repository.reclamation.ListeReclamationRepository;
import com.crm.operis_app.repository.reclamation.ReclamationCostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReclamationService {

    @Autowired
    ReclamationCostRepository reclamationCostRepository;
    @Autowired
    ListeReclamationRepository listeReclamation;

    public Optional<ReclamationCost> getActionCostById(Long reclamationCostId) {
        if (!reclamationCostRepository.existsById(reclamationCostId)) {
            throw new ResourceNotFoundException("ReclamationCost with id " + reclamationCostId + " not found");
        }
        return reclamationCostRepository.findById(reclamationCostId);
    }


    public ReclamationCost createActionCorrectionCost(Long listeActionId, ReclamationCost reclamationCost) {
        Optional<ListeReclamation> byId = listeReclamation.findById(listeActionId);
        if (!byId.isPresent()) {
            throw new ResourceNotFoundException("Liste Action with id " + listeActionId + " does not exist");
        }
        ListeReclamation listeAction = byId.get();
        listeAction.setReclamationCost(reclamationCost);
        return reclamationCostRepository.save(reclamationCost);
    }




    public ReclamationCost updateActionCostById(Long actionCostId, ReclamationCost actionCostRequest) {
        if (!reclamationCostRepository.existsById(actionCostId)) {
            throw new ResourceNotFoundException("ReclamationCost with id " + actionCostId + " not found");
        }
        Optional<ReclamationCost> actionCost = reclamationCostRepository.findById(actionCostId);

        if (!actionCost.isPresent()) {
            throw new ResourceNotFoundException("ReclamationCost with id " + actionCostId + " not found");
        }

        ReclamationCost actionCost1 = actionCost.get();
        actionCost1.setWorkforce(actionCostRequest.getWorkforce());
        actionCost1.setWorkforceCost(actionCostRequest.getWorkforceCost());
        actionCost1.setPiece(actionCostRequest.getPiece());
        actionCost1.setPieceCost(actionCostRequest.getPieceCost());
        actionCost1.setTransport(actionCostRequest.getTransport());
        actionCost1.setTransportCost(actionCostRequest.getTransportCost());
        actionCost1.setVarious(actionCostRequest.getVarious());
        actionCost1.setVariousCost(actionCostRequest.getVariousCost());
        actionCost1.setCostTotal(actionCostRequest.getWorkforceCost()+actionCostRequest.getPieceCost()+actionCostRequest.getTransportCost()+actionCostRequest.getVariousCost());
        return reclamationCostRepository.save(actionCost1);
    }

    public ResponseEntity<Object> deleteActionCostById(Long reclamationCostId) {
        if (!reclamationCostRepository.existsById(reclamationCostId)) {
            throw new ResourceNotFoundException("ReclamationCost with id " + reclamationCostId + " not found");
        }
        reclamationCostRepository.deleteById(reclamationCostId);
        return ResponseEntity.ok().build();
    }

}
