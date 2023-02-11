package com.crm.operis_app.repository.authUser;

import com.crm.operis_app.model.authUser.AccessLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("*")
@Repository
public interface AccessLogRepository extends JpaRepository<AccessLog, Integer> {

    public Page<AccessLog> findAllByOrderByIdDesc(Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT * FROM ACCES_LOG WHERE access_token=?1")
    AccessLog isUserLoggedIn(String accessToken);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE ACCES_LOG SET logout_at=NOW() WHERE access_token=?1")
    void removeAuthToken(String accessToken);

    @Query(nativeQuery = true, value = "SELECT user_id FROM ACCES_LOG WHERE access_token=?1")
    Integer getUserId(String accessToken);
}
