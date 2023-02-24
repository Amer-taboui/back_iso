package com.crm.operis_app.repository.reclamation;

import com.crm.operis_app.model.GRH.Formation;
import com.crm.operis_app.model.reclamation.ListeReclamation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListeReclamationRepository extends JpaRepository<ListeReclamation,Long> {
    List<ListeReclamation> findByActiveIsTrueOrderByIdAsc();
    List<ListeReclamation> findByActiveIsFalseOrderByIdAsc();
}
