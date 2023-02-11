package com.crm.operis_app.repository.action;

import com.crm.operis_app.model.action.actionCorrection.ListeActionCorrection;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface ListeActionCorrectionRepository extends JpaRepository<ListeActionCorrection, Long> {

    List<ListeActionCorrection> findByActiveIsTrue();

    List<ListeActionCorrection> findByActiveIsFalse();


}


