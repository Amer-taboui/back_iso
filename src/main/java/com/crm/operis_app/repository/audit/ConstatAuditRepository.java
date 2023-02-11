package com.crm.operis_app.repository.audit;

import com.crm.operis_app.model.audit.ConstatAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ConstatAuditRepository extends JpaRepository<ConstatAudit,Long>  {
}
