package com.crm.operis_app.repository.NonConformite;

import com.crm.operis_app.model.NonConformite.ListeNonConformite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListeNonConformiteRepository extends JpaRepository<ListeNonConformite,Long> {
    List<ListeNonConformite> findByActiveIsTrueOrderByIdAsc();
    List<ListeNonConformite> findByActiveIsFalseOrderByIdAsc();
}
