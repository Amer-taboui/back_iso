package com.crm.operis_app.repository.action;

import com.crm.operis_app.model.action.actionCorrection.PlanAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PlanActionRepository extends JpaRepository<PlanAction, Long> {
}
