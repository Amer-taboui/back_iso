package com.crm.operis_app.repository.conformite.Utils;

import com.crm.operis_app.model.conformite.Utils.NatureExigence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface NatureExigenceRepository extends JpaRepository<NatureExigence,Long> {
    List<NatureExigence> findByActiveIsTrueOrderByIdAsc();
    List<NatureExigence> findByActiveIsFalseOrderByIdAsc();
}
