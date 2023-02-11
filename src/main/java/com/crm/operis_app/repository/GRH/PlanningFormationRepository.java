package com.crm.operis_app.repository.GRH;

import com.crm.operis_app.model.GRH.PlanningFormation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@RepositoryRestResource
public interface PlanningFormationRepository extends JpaRepository<PlanningFormation, Long> {
     @Query(value = "SELECT * FROM planning_gannt_apqp where project_id =  :id " +
             "ORDER BY position ASC,pid ;"  ,nativeQuery = true)
     List<PlanningFormation> findPlanningByFormationId(Long id);

     @Query(value = "SELECT * FROM planning_gannt_apqp where p_parent = :id " +
             "ORDER BY position ASC,pid ;"  ,nativeQuery = true)
     List<PlanningFormation> findPlanningByParentId(int id);

     @Transactional
     @Modifying
     void deleteByPParent(int pParent);


}