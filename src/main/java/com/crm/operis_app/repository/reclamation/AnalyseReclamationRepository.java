package com.crm.operis_app.repository.reclamation;

import com.crm.operis_app.model.reclamation.AnalyseReclamation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface AnalyseReclamationRepository extends JpaRepository<AnalyseReclamation,Long> {

    @Query(value = " SELECT  analyseReclamation.personnel_id  FROM analyseReclamation " +
            "    where analyseReclamation.list_reclamation_id = :reclamationId", nativeQuery = true)
    List<BigInteger> findPersonnelIdListByAnalyseReclamation(@Param("reclamationId") Long reclamationId);
}
