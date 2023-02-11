package com.crm.operis_app.repository.audit;

import com.crm.operis_app.model.audit.ListeAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource

public interface ListeAuditRepository extends JpaRepository<ListeAudit, Long> {

    List<ListeAudit> findByActiveIsTrue();

    List<ListeAudit> findByActiveIsFalse();


}