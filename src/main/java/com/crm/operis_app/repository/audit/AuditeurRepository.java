package com.crm.operis_app.repository.audit;

import com.crm.operis_app.model.audit.Audite;
import com.crm.operis_app.model.audit.Auditeur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditeurRepository extends JpaRepository<Auditeur,Long> {
}
