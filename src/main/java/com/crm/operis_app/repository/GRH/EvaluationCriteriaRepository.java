package com.crm.operis_app.repository.GRH;

import com.crm.operis_app.model.GRH.EvaluationCriteria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvaluationCriteriaRepository extends JpaRepository<EvaluationCriteria,Long> {
}
