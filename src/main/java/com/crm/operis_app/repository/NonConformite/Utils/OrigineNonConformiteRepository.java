package com.crm.operis_app.repository.NonConformite.Utils;

import com.crm.operis_app.model.NonConformite.Utils.OrigineNonConformite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface OrigineNonConformiteRepository extends JpaRepository<OrigineNonConformite, Long> {

    List<OrigineNonConformite> findByActiveIsTrueOrderByOrigineIdAsc();
    List<OrigineNonConformite> findByActiveIsFalseOrderByOrigineIdAsc();


}
