package com.crm.operis_app.services.action;

import com.crm.operis_app.exception.ResourceNotFoundException;
import com.crm.operis_app.model.action.actionCorrection.ListeActionCorrection;
import com.crm.operis_app.model.action.utils.ActionCost;
import com.crm.operis_app.repository.action.ActionCostRepository;
import com.crm.operis_app.repository.action.ListeActionCorrectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ActionCostService {


    @Autowired
    ActionCostRepository actionCostRepository;

    @Autowired
    ListeActionCorrectionRepository listeActionRepository;


    public Optional<ActionCost> getActionCostById(Long actionCostId) {
        if (!actionCostRepository.existsById(actionCostId)) {
            throw new ResourceNotFoundException("ActionCost with id " + actionCostId + " not found");
        }
        return actionCostRepository.findById(actionCostId);
    }


    public ActionCost createActionCorrectionCost(Long listeActionId, ActionCost actionCost) {
        Optional<ListeActionCorrection> byId = listeActionRepository.findById(listeActionId);
        if (!byId.isPresent()) {
            throw new ResourceNotFoundException("Liste Action with id " + listeActionId + " does not exist");
        }
        ListeActionCorrection listeAction = byId.get();
        listeAction.setActionCorrectionCost(actionCost);
        return actionCostRepository.save(actionCost);
    }




    public ActionCost updateActionCostById(Long actionCostId, ActionCost actionCostRequest) {
        if (!actionCostRepository.existsById(actionCostId)) {
            throw new ResourceNotFoundException("equipe with id " + actionCostId + " not found");
        }
        Optional<ActionCost> actionCost = actionCostRepository.findById(actionCostId);

        if (!actionCost.isPresent()) {
            throw new ResourceNotFoundException("equipe with id " + actionCostId + " not found");
        }

        ActionCost actionCost1 = actionCost.get();
        actionCost1.setWorkforce(actionCostRequest.getWorkforce());
        actionCost1.setWorkforceCost(actionCostRequest.getWorkforceCost());
        actionCost1.setPiece(actionCostRequest.getPiece());
        actionCost1.setPieceCost(actionCostRequest.getPieceCost());
        actionCost1.setTransport(actionCostRequest.getTransport());
        actionCost1.setTransportCost(actionCostRequest.getTransportCost());
        actionCost1.setVarious(actionCostRequest.getVarious());
        actionCost1.setVariousCost(actionCostRequest.getVariousCost());
        actionCost1.setCostTotal(actionCostRequest.getWorkforceCost()+actionCostRequest.getPieceCost()+actionCostRequest.getTransportCost()+actionCostRequest.getVariousCost());
        return actionCostRepository.save(actionCost1);
    }

    public ResponseEntity<Object> deleteActionCostById(Long actionCostId) {
        if (!actionCostRepository.existsById(actionCostId)) {
            throw new ResourceNotFoundException("ActionCost with id " + actionCostId + " not found");
        }
        actionCostRepository.deleteById(actionCostId);
        return ResponseEntity.ok().build();
    }







}
