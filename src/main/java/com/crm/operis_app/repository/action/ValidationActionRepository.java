package com.crm.operis_app.repository.action;

import com.crm.operis_app.model.action.actionCorrection.ValidationAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ValidationActionRepository extends JpaRepository<ValidationAction,Long> {
}
