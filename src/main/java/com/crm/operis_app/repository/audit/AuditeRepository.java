package com.crm.operis_app.repository.audit;

import com.crm.operis_app.model.audit.Audite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditeRepository extends JpaRepository<Audite,Long> {
}
