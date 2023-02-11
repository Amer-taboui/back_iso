package com.crm.operis_app.repository.Utils;

import com.crm.operis_app.model.GRH.Personal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository

public interface PersonnelRepository extends JpaRepository<Personal, Long> {
    List<Personal> findByActiveIsTrueOrderByIdAsc();

    List<Personal> findByActiveIsFalseOrderByIdAsc();



    @Query(value = "SELECT  s.skill_name, count(s.skill_id) " +
            "FROM personnel p " +
            "JOIN skill_evaluation_method sem on p.personnel_id = sem.personal_id " +
            "JOIN skill s on sem.skill_id = s.skill_id " +
            "GROUP BY  s.skill_name",
            nativeQuery = true)
    List<Map<String,Object>> getPersonals();


}
