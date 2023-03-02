package com.crm.operis_app.repository.NonConformite.Utils;

import com.crm.operis_app.model.NonConformite.Utils.CategorieNonConformite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface CategorieNonConformiteRepository extends JpaRepository<CategorieNonConformite, Long> {

    List<CategorieNonConformite> findByActiveIsTrueOrderByCategorieIdAsc();
    List<CategorieNonConformite> findByActiveIsFalseOrderByCategorieIdAsc();


}
