package com.crypto.dashboardweb.service.exceptions;

import com.crypto.dashboardweb.service.exceptions.enums.AuthenticationExceptionEnum;

public class AuthenticationException extends DashboardException {
    private AuthenticationExceptionEnum authenticationExceptionEnum;

    public AuthenticationException(AuthenticationExceptionEnum authenticationExceptionEnum) {
        this.authenticationExceptionEnum = authenticationExceptionEnum;
        setExceptionMessage(authenticationExceptionEnum.name());
    }

    public AuthenticationExceptionEnum getAuthenticationExceptionEnum() {
        return authenticationExceptionEnum;
    }
}
