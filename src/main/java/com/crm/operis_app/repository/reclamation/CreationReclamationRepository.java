package com.crm.operis_app.repository.reclamation;

import com.crm.operis_app.model.reclamation.CreationReclamation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreationReclamationRepository extends JpaRepository<CreationReclamation,Long> {
}
