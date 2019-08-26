package com.myApp.app.service.impl;

import com.myApp.app.entity.AuthUser;
import com.myApp.app.repository.AuthUserRepository;
import com.myApp.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The User Service Implementation.
 */
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final AuthUserRepository authUserRepository;

    /**
     * The constructor injector for UserServiceImpl.
     *
     * @param authUserRepo authUserRepo
     */
    @Autowired
    public UserServiceImpl(final AuthUserRepository authUserRepo) {
        this.authUserRepository = authUserRepo;
    }


    /**
     * To load User By Username.
     *
     * @param username username
     * @return UserDetails
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(final String username)
            throws UsernameNotFoundException {
        AuthUser user = findUserByEmailAddress(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        Set<String> role = new HashSet<>();
        role.add(user.getRole());
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(), role.stream().map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList()));
    }

    /**
     * To find user by email address.
     *
     * @param email email
     * @return AuthUser
     */
    @Override
    public AuthUser findUserByEmailAddress(final String email) {
        return authUserRepository.findByEmailAndIsDeletedFalse(email);
    }

    /**
     * To save user.
     *
     * @param user user
     * @return AuthUser
     */
    @Override
    public AuthUser saveAuthUser(final AuthUser user) {
        return authUserRepository.save(user);
    }
}
