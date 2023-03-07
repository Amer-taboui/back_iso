package com.crm.operis_app.repository.NonConformite;

import com.crm.operis_app.model.NonConformite.ValidationNonConformite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ValidationNonConformiteRepository extends JpaRepository<ValidationNonConformite,Long> {
}
