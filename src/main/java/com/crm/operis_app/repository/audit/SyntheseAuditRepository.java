package com.crm.operis_app.repository.audit;

import com.crm.operis_app.model.audit.SyntheseAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource

public interface SyntheseAuditRepository extends JpaRepository <SyntheseAudit,Long>{
}
