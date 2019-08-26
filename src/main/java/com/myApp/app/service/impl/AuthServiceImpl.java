package com.myApp.app.service.impl;

import com.myApp.app.dtos.AuthRequestDTO;
import com.myApp.app.dtos.AuthResponseDTO;
import com.myApp.app.entity.AuthUser;
import com.myApp.app.security.JwtTokenUtil;
import com.myApp.app.service.AuthService;
import com.myApp.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * The Auth service Implementation.
 */
@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenUtil jwtTokenUtil;

    private final UserService userService;

    /**
     * The constructor Injector for AuthServiceImpl.
     *
     * @param authenticationManager authenticationManager
     * @param jwtTokenUtils         jwtTokenUtils
     * @param userServiceParam      userServiceParam
     */
    @Autowired
    public AuthServiceImpl(final AuthenticationManager authenticationManager,
                           final JwtTokenUtil jwtTokenUtils,
                           final UserService userServiceParam) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtils;
        this.userService = userServiceParam;
    }

    /**
     * To Login.
     *
     * @param authRequest authRequest
     * @return AuthResponseDTO
     */
    @Override
    public AuthResponseDTO loginCheck(final AuthRequestDTO authRequest) {
        final Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        authRequest.getEmailAddress(), authRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final AuthUser authUser = userService
                .findUserByEmailAddress(authRequest.getEmailAddress());
        if (authUser != null && !authUser.getIsDeleted()) {
            final String token = jwtTokenUtil.generateToken(authUser,
                    authRequest.isRememberMe());
            authUser.setLastLoggedIn(LocalDateTime.now());
            userService.saveAuthUser(authUser);
            return buildAuthResponseDTO(authUser, token);
        }
        return null;
    }

    /**
     * To build AuthResponse DTO.
     *
     * @param user  user
     * @param token token
     * @return AuthResponseDTO
     */
    private AuthResponseDTO buildAuthResponseDTO(final AuthUser user,
                                                 final String token) {
        AuthResponseDTO authResponse = new AuthResponseDTO();
        authResponse.setUserId(user.getId());
        authResponse.setEmailId(user.getEmail());
        authResponse.setFirstName(user.getFirstName());
        authResponse.setLastName(user.getLastName());
        authResponse.setLastLoggedIn(user.getLastLoggedIn());
        authResponse.setRole(user.getRole());
        authResponse.setToken(token);
        return authResponse;
    }
}
