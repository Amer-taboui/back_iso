package com.crm.operis_app.repository.GRH;

import com.crm.operis_app.model.GRH.requiredSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequiredSkillRepository extends JpaRepository<requiredSkill,Long> {
}
