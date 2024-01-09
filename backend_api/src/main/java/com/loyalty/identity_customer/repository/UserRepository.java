package com.loyalty.identity_customer.repository;

import com.loyalty.identity_customer.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

    @Query("SELECT u FROM UserModel u WHERE u.username = :username AND u.password = :password AND u.rolename = 'PARTNER'")
    UserModel getUserPartnerByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    @Query("SELECT u FROM UserModel u WHERE u.username = :username AND u.password = :password AND u.rolename = 'ADMIN'")
    UserModel getUserAdminByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}
