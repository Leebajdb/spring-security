package com.myApp.app.controller;

import com.myApp.app.dtos.AuthRequestDTO;
import com.myApp.app.dtos.AuthResponseDTO;
import com.myApp.app.service.AuthService;
import com.myApp.app.utils.AppResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static com.myApp.app.utils.ValidationProcessor.processBindingResult;

/**
 * The controller with APIs for Authentication.
 */
@RestController
@RequestMapping(value = "/api")
public class AuthenticationApis {

    private final AuthService authService;

    /**
     * The Constructor Injector for AuthenticationApis.
     *
     * @param authServiceParam authServiceParam
     */
    @Autowired
    public AuthenticationApis(final AuthService authServiceParam) {
        this.authService = authServiceParam;
    }

    /**
     * To authenticate.
     *
     * @param authRequest   authRequest
     * @param bindingResult bindingResult
     * @return AuthResponseDTO
     */
    @PostMapping(value = "/authenticate")
    public AppResponse<AuthResponseDTO> authenticate(
            @Valid @RequestBody final AuthRequestDTO authRequest,
            final BindingResult bindingResult) {
        List<String> errors = processBindingResult(bindingResult);
        if (errors.size() > 0) {
            return AppResponse.<AuthResponseDTO>builder()
                    .message(errors.toString())
                    .success(false)
                    .build();
        }
        AuthResponseDTO response = authService.loginCheck(authRequest);
        if (response != null) {
            return AppResponse.<AuthResponseDTO>builder()
                    .data(response)
                    .message("Successfully authenticated user.")
                    .success(true)
                    .build();
        }
        return AppResponse.<AuthResponseDTO>builder()
                .success(false)
                .message("Authentication error, please check provided email or password!")
                .build();

    }


}
