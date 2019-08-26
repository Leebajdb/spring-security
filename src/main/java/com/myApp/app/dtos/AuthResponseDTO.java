package com.myApp.app.dtos;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * The Auth Response DTO.
 */
@Data
public class AuthResponseDTO {

    private Long userId;

    private String firstName;

    private String lastName;

    private String emailId;

    private String role;

    private LocalDateTime lastLoggedIn;

    private String token;
}
