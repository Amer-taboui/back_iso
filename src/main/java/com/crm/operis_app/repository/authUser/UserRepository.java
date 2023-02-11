package com.crm.operis_app.repository.authUser;

import com.crm.operis_app.model.authUser.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
 import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

    List<User> findByActiveIsTrue();

    List<User> findByActiveIsFalse();


    @Query(value = " SELECT email FROM users  where personnel_id = :id ", nativeQuery = true)
    String getEmailByPersonnelId(Long id);

    @Query(value = " SELECT current_ip_adress FROM users  where username = :username ", nativeQuery = true)
    String getUserIpAdress(@Param("username") String username);


    @Query(value = " SELECT nom_personnel FROM personnel  where personnel_id= :id ", nativeQuery = true)
    String getNameByPersonnelId(@Param("id") Long id);

    @Query(value = "SELECT personnel_id FROM users ", nativeQuery = true)
    List<BigInteger> findUserPersonnelId();


}