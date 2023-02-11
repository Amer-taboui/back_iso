package com.crm.operis_app.repository.authUser;

import com.crm.operis_app.model.authUser.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
 import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin("*")
@Repository

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {
    List<Privilege> findByModule(String module);


    @Query(value = " SELECT * FROM  roles , privilege , roles_privileges  WHERE roles.role_id = roles_privileges.role_id \n" +
            "AND privilege.privilege_id = roles_privileges.privilege_id AND roles.role_id = :id AND privilege.module = :moduleName", nativeQuery = true)
    List<Privilege> findByRoleAndModule(@Param("id") Long roleId, @Param("moduleName") String moduleName);

    @Query(value = "SELECT module FROM privilege\n" +
            " where module= 'AUDITEURS'\n" +
            " OR module= 'PERSONNELS'\n" +
            " OR module= 'CLIENTS'\n" +
            " OR module= 'PRODUITS'\n" +
            " OR module= 'SITES'\n" +
            " OR module= 'PROCÉDÉ DE FABRICATION'\n" +
            " OR module= 'PROCESSUS'\n" +
            " OR module= 'POSTE DE TRAVAIL'\n" +
            " OR module= 'PLANIFICATION DYNAMIQUE'\n" +
            " OR module= 'CRITÈRE LPA'\n" +
            " OR module= 'ZONES'\n" +
            " OR module= 'CHECKLIST PRODUIT'\n" +
            " OR module= 'MÉTHODE AMDEC'\n" +
            " OR module= 'COTATIONS'\n" +
            " OR module= 'ÉTAPES DE FLUX'" +
            " OR module= 'CRITÈRE RECLAMATION'" +
            " OR module= 'PERFERMANCE SYSTÈME'" +
            " OR module= 'TAUX DE RESPECT 8D'" +
            " OR module= 'NIVEAU DE RISQUE'" +
            " OR module= 'CHECKLIST 5T'" +
            "group by module ", nativeQuery = true)
    List<String> findParametreModuleList();

    @Query(value = "SELECT  module FROM privilege\n" +
            " where module= 'IATF'\n" +
            " OR module= 'AMDEC'\n" +
            " OR module= 'AUDIT'\n" +
            " OR module= 'RULES'\n" +
            " OR module= 'CSR'\n" +
            " OR module= 'ACTION'\n" +
            " OR module= 'LLC'\n" +
            " OR module= 'PROJET' \n" +
            " OR module= 'CONCEPTION ET DÉVELOPPEMENT' \n" +
            " OR module= 'PLANIFICATION DYNAMIQUE'\n" +

            " OR module= 'PLAN SURVEILLANCE'" +
            "group by module  ", nativeQuery = true)
    List<String> findModuleList();


}