package com.myApp.app.dtos;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * The Auth Request DTO.
 */
@Data
public class AuthRequestDTO {

    @NotNull(message = "Email Address cannot be null")
    @NotEmpty(message = "Email Address cannot be empty")
    @NotBlank(message = "Email Address cannot be blank")
    @Email
    private String emailAddress;

    @NotNull(message = "Password cannot be null")
    @NotEmpty(message = "Password Address cannot be empty")
    @NotBlank(message = "Password Address cannot be blank")
    private String password;

    @NotNull
    private boolean rememberMe;
}
