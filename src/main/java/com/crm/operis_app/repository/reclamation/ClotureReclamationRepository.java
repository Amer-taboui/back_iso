package com.crm.operis_app.repository.reclamation;

import com.crm.operis_app.model.reclamation.ClotureReclamation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClotureReclamationRepository extends JpaRepository<ClotureReclamation,Long> {
}
