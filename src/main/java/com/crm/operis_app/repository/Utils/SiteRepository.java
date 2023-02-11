package com.crm.operis_app.repository.Utils;
import com.crm.operis_app.model.Utils.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface SiteRepository extends JpaRepository<Site, Long> {

    List<Site> findByActiveIsTrueOrderBySiteIdAsc();
    List<Site> findByActiveIsFalseOrderBySiteIdAsc();


}
