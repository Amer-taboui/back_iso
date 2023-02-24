package com.crm.operis_app.repository.reclamation;

import com.crm.operis_app.model.reclamation.AnalyseReclamation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalyseReclamationRepository extends JpaRepository<AnalyseReclamation,Long> {
}
