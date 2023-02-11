package com.crm.operis_app.repository.action;

import com.crm.operis_app.model.action.actionCorrection.EtatAction;
import com.crm.operis_app.model.action.utils.ActionCost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface EtatActionRepository extends JpaRepository<EtatAction, Long> {
}
