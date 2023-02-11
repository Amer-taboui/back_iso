package com.crm.operis_app.repository.authUser;

import com.crm.operis_app.model.authUser.ImageUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ImageUserRepository extends JpaRepository<ImageUser,Long> {
}
