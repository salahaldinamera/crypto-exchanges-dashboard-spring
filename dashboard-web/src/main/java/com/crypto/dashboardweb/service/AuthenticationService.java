package com.crypto.dashboardweb.service;


import com.crypto.dashboardweb.model.dto.JwtRequestDto;
import com.crypto.dashboardweb.model.dto.JwtResponseDto;
import com.crypto.dashboardweb.service.exceptions.AccessTokenException;
import com.crypto.dashboardweb.service.exceptions.AuthenticationException;
import com.crypto.dashboardweb.service.exceptions.UserException;

public interface AuthenticationService {

    /**
     * Authenticate user login
     * @param requestDto
     * @return
     */
    JwtResponseDto authenticate(JwtRequestDto requestDto) throws AuthenticationException, UserException;

    /**
     * Check if the token is expired
     * @param token
     * @return
     */
    boolean isTokenExpired(String token) throws AccessTokenException;

    /**
     * Check if the user is authenticated
     * @param jwtRequest
     * @return
     */
    boolean checkUserAuthentication(JwtRequestDto jwtRequest) throws AuthenticationException;
}
