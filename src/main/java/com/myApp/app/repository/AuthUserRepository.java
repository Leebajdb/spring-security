package com.myApp.app.repository;

import com.myApp.app.entity.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The AuthUser Repository.
 */
public interface AuthUserRepository extends JpaRepository<AuthUser, Long> {

    /**
     * To find By Email And IsDeleted False.
     * @param email email
     * @return AuthUser
     */
    AuthUser findByEmailAndIsDeletedFalse(String email);

}
