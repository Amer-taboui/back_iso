package com.crm.operis_app.services.GRH;

import com.crm.operis_app.exception.ResourceNotFoundException;
import com.crm.operis_app.model.GRH.Formation;
import com.crm.operis_app.model.GRH.PlanningFormation;
import com.crm.operis_app.repository.GRH.FormationRepository;
import com.crm.operis_app.repository.GRH.PlanningFormationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PlanningFormationServiceImp {
    @Autowired
    PlanningFormationRepository planningFormationRepository;

    @Autowired
    FormationRepository formationRepository;


    public PlanningFormation createPlanningFormation(Long formationId, PlanningFormation planningGannt) {
        Optional<Formation> formationById = formationRepository.findById(formationId);
        if (!formationById.isPresent()) {
            throw new ResourceNotFoundException("formation with id " + formationId + " does not exist");
        }


        Formation formation = formationById.get();
        planningGannt.setFormation(formation);

        return planningFormationRepository.save(planningGannt);
    }


    public List<PlanningFormation> getPlanningGanntsFormation(Long formationId) {
        List<PlanningFormation>  planningGanntApqps=planningFormationRepository.findPlanningByFormationId(formationId);
        return planningFormationRepository.findPlanningByFormationId(formationId);
    }

    public List<PlanningFormation> getChildTasksByParent(int parentId) {
        return planningFormationRepository.findPlanningByParentId(parentId);
    }

    public PlanningFormation updatePlanningGanntFormationById(Long planningGanntId, PlanningFormation planningGanntRequest) {
        if (!planningFormationRepository.existsById(planningGanntId)) {
            throw new ResourceNotFoundException("planningGannt with id " + planningGanntId + " not found");
        }
        Optional<PlanningFormation> planningGannt = planningFormationRepository.findById(planningGanntId);

        if (!planningGannt.isPresent()) {
            throw new ResourceNotFoundException("planningGannt with id " + planningGanntId + " not found");
        }
        PlanningFormation planningGannt1 = planningGannt.get();
        planningGannt1.setpName(planningGanntRequest.getpName());
        planningGannt1.setpStart(planningGanntRequest.getpStart());
        planningGannt1.setpEnd(planningGanntRequest.getpEnd());
        planningGannt1.setpComp(planningGanntRequest.getpComp());
        planningGannt1.setpClass(planningGanntRequest.getpClass());
        planningGannt1.setpNotes(planningGanntRequest.getpNotes());
        planningGannt1.setRealEndDate(planningGanntRequest.getRealEndDate());
        return planningFormationRepository.save(planningGannt1);
    }



    public ResponseEntity<Object> deletePlanningGanntFormation(Long planningGanntId) {
        Optional<PlanningFormation> planningGannt = planningFormationRepository.findById(planningGanntId);

        if (!planningGannt.isPresent()) {
            throw new ResourceNotFoundException("planningGannt with id " + planningGanntId + " not found");
        }

        if (planningGannt.get().getTaskIsParent()){
            int parentId=  planningGanntId.intValue();
            planningFormationRepository.deleteByPParent(parentId);
        }
        planningFormationRepository.deleteById(planningGanntId);
        return ResponseEntity.ok().build();
    }
}
