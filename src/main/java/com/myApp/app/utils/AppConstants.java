package com.myApp.app.utils;

/**
 * The Interface with constants.
 */
public interface AppConstants {
    long TOKEN_VALIDITY_SECONDS = 6 * 60 * 60;
    long REMEMBER_ME_TOKEN_VALIDITY_SECONDS = 7 * 24 * 60 * 60;
    String SECRET_KEY = "K5KqA5G5sku";
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING = "Authorization";
}
