package com.crm.operis_app.repository.GRH;

import com.crm.operis_app.model.GRH.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillRepository extends JpaRepository<Skill,Long> {
    List<Skill> findByActiveIsTrueOrderByIdAsc();

    List<Skill> findByActiveIsFalseOrderByIdAsc();


}
