package com.crm.operis_app.services.action;

import com.crm.operis_app.model.action.actionCorrection.ListeActionCorrection;
import com.crm.operis_app.model.action.actionCorrection.PlanAction;
import com.crm.operis_app.repository.action.ListeActionCorrectionRepository;
import com.crm.operis_app.repository.action.PlanActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service

public class PlanActionService {


    @Autowired
    ListeActionCorrectionRepository listeActionCorrectionRepository;

    @Autowired
    PlanActionRepository planActionRepository;



    public List<PlanAction> getPlanAction() {
        return planActionRepository.findAll();
    }


    public Optional<PlanAction> getPlanActionById(Long planActionId) {
        if (!planActionRepository.existsById(planActionId)) {
            throw new ResourceNotFoundException("planAction with id " + planActionId + " not found");
        }
        return planActionRepository.findById(planActionId);
    }

    public PlanAction createPlanAction(Long liste_actionId, PlanAction planAction) {
        Set<PlanAction> planActions = new HashSet<>();

        Optional<ListeActionCorrection> byId = listeActionCorrectionRepository.findById(liste_actionId);
        if (!byId.isPresent()) {
            throw new ResourceNotFoundException("liste_action with id " + liste_actionId + " does not exist");
        }
        ListeActionCorrection liste_action = byId.get();
        planAction.setListeActionCorrection(liste_action);

        PlanAction validationAction1 = this.planActionRepository.save(planAction);
        planActions.add(validationAction1);
        return validationAction1;

    }

    public PlanAction updatePlanActionById(Long action1Id, PlanAction planAction) {
        if (! planActionRepository.existsById(action1Id)) {
            throw new ResourceNotFoundException("planAction  with id " + action1Id + " not found");
        }
        Optional<PlanAction> Liste =  planActionRepository.findById(action1Id);

        if (!Liste.isPresent()) {
            throw new ResourceNotFoundException("planAction with id " + action1Id + " not found");
        }
        PlanAction liste1 = Liste.get();
        liste1.setResponsableSuivi(planAction.getResponsableSuivi());
        liste1.setTauxEfficacite(planAction.getTauxEfficacite());
        liste1.setTauxRealisation(planAction.getTauxRealisation());
        liste1.setCommentaireSuivi(planAction.getCommentaireSuivi());
        return  planActionRepository.save(liste1);
    }

    public ResponseEntity<Object> deletePlanActionById(long action1Id) {
        if (! planActionRepository.existsById(action1Id)) {
            throw new ResourceNotFoundException("planAction with id " + action1Id + " not found");
        }
        planActionRepository.deleteById(action1Id);
        return ResponseEntity.ok().build();
    }


}


