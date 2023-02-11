package com.crm.operis_app.repository.action;



import com.crm.operis_app.model.action.utils.ActionCost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@RepositoryRestResource

public interface ActionCostRepository extends JpaRepository<ActionCost, Long> {



}

