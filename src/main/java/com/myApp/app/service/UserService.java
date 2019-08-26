package com.myApp.app.service;

import com.myApp.app.entity.AuthUser;

/**
 * The UserService Interface.
 */
public interface UserService {

    /**
     * To find user by email address.
     *
     * @param email email
     * @return AuthUser
     */
    AuthUser findUserByEmailAddress(String email);

    /**
     * To save the user.
     * @param user user
     * @return AuthUser
     */
    AuthUser saveAuthUser(AuthUser user);
}
