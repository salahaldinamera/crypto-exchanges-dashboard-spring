package com.crypto.dashboardweb.controller;


import com.crypto.dashboardweb.model.dto.JwtRequestDto;
import com.crypto.dashboardweb.model.dto.JwtResponseDto;
import com.crypto.dashboardweb.service.AuthenticationService;
import com.crypto.dashboardweb.service.exceptions.AuthenticationException;
import com.crypto.dashboardweb.service.exceptions.UserException;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Authentication Controller
 * Contains authentication related API-Endpoint
 * login API
 */
@Tag(name = "Authentication", description = "Authentication management")
@RestController
@RequestMapping("/api/v1/authenticate/")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    /**
     * Create authentication token for the user if authorized
     * @param jwtRequestDto
     * @return
     * @throws AuthenticationException
     * @throws UserException
     */
    @PostMapping
    public ResponseEntity<JwtResponseDto> createAuthenticationToken(@RequestBody JwtRequestDto jwtRequestDto) throws AuthenticationException, UserException {
        JwtResponseDto response = authenticationService.authenticate(jwtRequestDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
