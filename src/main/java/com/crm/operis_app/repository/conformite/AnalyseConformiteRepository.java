package com.crm.operis_app.repository.conformite;

import com.crm.operis_app.model.conformite.AnalyseConformite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface AnalyseConformiteRepository extends JpaRepository<AnalyseConformite,Long> {
    @Query(value = " SELECT  analyseConformite.personnel_id  FROM analyseConformite " +
            "    where analyseConformite.liste_conformite_id = :conformiteId", nativeQuery = true)
    List<BigInteger> findPersonnelIdListByAnalyseConformite(@Param("conformiteId") Long conformiteId);
}
