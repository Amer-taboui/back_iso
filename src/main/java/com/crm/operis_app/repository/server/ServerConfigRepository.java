package com.crm.operis_app.repository.server;

import com.crm.operis_app.model.server.ServerConfig;
import org.springframework.data.jpa.repository.JpaRepository;
 import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;


@CrossOrigin("*")
@Repository
public interface ServerConfigRepository extends JpaRepository<ServerConfig, Long> {
}
