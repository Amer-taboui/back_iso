package com.crm.operis_app.repository.GRH;

import com.crm.operis_app.model.GRH.Formation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FormationRepository extends JpaRepository<Formation, Long> {
    List<Formation> findByActiveIsTrueOrderByIdAsc();
    List<Formation> findByActiveIsFalseOrderByIdAsc();
}
