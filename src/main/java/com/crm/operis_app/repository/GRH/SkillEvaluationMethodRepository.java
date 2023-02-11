package com.crm.operis_app.repository.GRH;

import com.crm.operis_app.model.GRH.Post;
import com.crm.operis_app.model.GRH.PostPersonnelCount;
import com.crm.operis_app.model.GRH.SkillEvaluationMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Repository
public interface SkillEvaluationMethodRepository extends JpaRepository<SkillEvaluationMethod,Long> {
//   List<SkillEvaluationMethod> findBySkillId(Long skillId);
@Query(value = " SELECT  skill_evaluation_method.skill_id  FROM skill_evaluation_method " +
        "    where skill_evaluation_method.personal_id = :personalId", nativeQuery = true)
List<BigInteger> findSkillIdListBySkillEvaluation(@Param("personalId") Long personalId);

    @Query(value = " SELECT  skill_evaluation_method.skill_id  FROM skill_evaluation_method " +
            "    where skill_evaluation_method.post_id = :postId", nativeQuery = true)
    List<BigInteger> findSkillIdBySkillEvaluation(@Param("postId") Long postId);


    @Query(value = "SELECT SUM(note) / (SELECT SUM(note) FROM skill_evaluation_method WHERE post_id = :postId) * 100 FROM skill_evaluation_method WHERE personal_id = :personalId AND post_id = :postId GROUP BY personal_id, nom_personnel", nativeQuery = true)
    double sumNoteByPersonalIdAndNomPersonnelAndPostId(@Param("personalId") Long personalId, @Param("postId") Long postId);


}
