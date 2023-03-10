package com.crm.operis_app.repository.conformite;

import com.crm.operis_app.model.conformite.ListeConformite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListeConformiteRepository extends JpaRepository<ListeConformite,Long> {
    List<ListeConformite> findByActiveIsTrueOrderByIdAsc();
    List<ListeConformite> findByActiveIsFalseOrderByIdAsc();
}
