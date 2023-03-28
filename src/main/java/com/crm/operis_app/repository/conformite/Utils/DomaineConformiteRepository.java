package com.crm.operis_app.repository.conformite.Utils;

import com.crm.operis_app.model.NonConformite.Utils.CategorieNonConformite;
import com.crm.operis_app.model.conformite.Utils.DomaineConformite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DomaineConformiteRepository extends JpaRepository<DomaineConformite,Long> {
    List<DomaineConformite> findByActiveIsTrueOrderByIdAsc();
    List<DomaineConformite> findByActiveIsFalseOrderByIdAsc();

}
