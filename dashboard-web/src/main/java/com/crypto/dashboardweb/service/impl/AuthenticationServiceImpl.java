package com.crypto.dashboardweb.service.impl;

import com.crypto.dashboardweb.model.AccessToken;
import com.crypto.dashboardweb.model.User;
import com.crypto.dashboardweb.model.dto.JwtRequestDto;
import com.crypto.dashboardweb.model.dto.JwtResponseDto;
import com.crypto.dashboardweb.service.AccessTokenService;
import com.crypto.dashboardweb.service.AuthenticationService;
import com.crypto.dashboardweb.service.UserService;
import com.crypto.dashboardweb.service.exceptions.AccessTokenException;
import com.crypto.dashboardweb.service.exceptions.AuthenticationException;
import com.crypto.dashboardweb.service.exceptions.UserException;
import com.crypto.dashboardweb.service.exceptions.enums.AuthenticationExceptionEnum;
import com.crypto.dashboardweb.service.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Slf4j
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private AccessTokenService accessTokenService;

    @Override
    public JwtResponseDto authenticate(JwtRequestDto jwtRequestDto) throws AuthenticationException, UserException {
        UserDetails userDetails;

        try {
            userDetails = userService.loadUserByUsername(jwtRequestDto.getUsername());
        } catch (Exception e) {
            throw new AuthenticationException(AuthenticationExceptionEnum.EMAIL_NOT_FOUND);
        }

        if (!bCryptPasswordEncoder.matches(jwtRequestDto.getPassword(), userDetails.getPassword())) {
            throw new AuthenticationException(AuthenticationExceptionEnum.WRONG_PASSWORD);
        }

        return generateToken(jwtRequestDto.getUsername(), userDetails, jwtRequestDto.isStaySignedIn());
    }

    @Override
    public boolean isTokenExpired(String token) throws AccessTokenException {
        AccessToken accessToken = accessTokenService.getByToken(token);
        if (accessToken == null) return false;
        return accessToken.isExpired();
    }

    @Override
    public boolean checkUserAuthentication(JwtRequestDto jwtRequest) throws AuthenticationException {
        UserDetails userDetails;
        try {
            userDetails = userService.loadUserByUsername(jwtRequest.getUsername());
        } catch (UsernameNotFoundException e) {
            throw new AuthenticationException(AuthenticationExceptionEnum.EMAIL_NOT_FOUND);
        }
        if (userDetails == null) {
            throw new AuthenticationException(AuthenticationExceptionEnum.EMAIL_NOT_FOUND);
        }
        if (!bCryptPasswordEncoder.matches(jwtRequest.getPassword(), userDetails.getPassword())) {
            throw new AuthenticationException(AuthenticationExceptionEnum.WRONG_PASSWORD);
        }

        return true;
    }



    private JwtResponseDto generateToken(String username, UserDetails userDetails, boolean staySignedIn) throws UserException {
        User user = userService.getUserByUsername(username);

        if(user.getAccessToken() == null)
            user.setAccessToken(new AccessToken());

        Calendar cal = Calendar.getInstance();
        String token = jwtTokenUtil.generateToken(userDetails, user, staySignedIn);

        user.getAccessToken().setCreationDate(cal.getTime());
        user.getAccessToken().setExpired(false);
        user.getAccessToken().setToken(token);
        user.getAccessToken().setUser(user);
        cal.add(Calendar.HOUR_OF_DAY, 12);
        user.getAccessToken().setExpirationDate(cal.getTime());
        userService.updateInternal(user);

        return new JwtResponseDto(token, user.getEmail(), user.getRole(), user.getId(), user.isDefaultPasswordChanged());
    }
}
