package com.myApp.app.service;

import com.myApp.app.dtos.AuthRequestDTO;
import com.myApp.app.dtos.AuthResponseDTO;

/**
 * The AUth Service Interface.
 */
public interface AuthService {

    /**
     * To Login.
     *
     * @param authRequest authRequest
     * @return AuthResponseDTO
     */
    AuthResponseDTO loginCheck(final AuthRequestDTO authRequest);
}
