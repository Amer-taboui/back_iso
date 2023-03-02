package com.crm.operis_app.repository.NonConformite.Utils;

import com.crm.operis_app.model.NonConformite.Utils.GraviteNonConformite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface GraviteNonConformiteRepository extends JpaRepository<GraviteNonConformite, Long> {

    List<GraviteNonConformite> findByActiveIsTrueOrderByGraviteIdAsc();
    List<GraviteNonConformite> findByActiveIsFalseOrderByGraviteIdAsc();


}

