package com.crm.operis_app.repository.Utils;

import com.crm.operis_app.model.Utils.Settings;
import org.springframework.data.jpa.repository.JpaRepository;
 import org.springframework.stereotype.Repository;

@Repository

public interface SettingsRepository extends JpaRepository<Settings, Long> {


}
