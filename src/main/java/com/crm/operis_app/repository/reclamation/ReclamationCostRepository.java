package com.crm.operis_app.repository.reclamation;

import com.crm.operis_app.model.reclamation.ReclamationCost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ReclamationCostRepository extends JpaRepository <ReclamationCost, Long> {
}
