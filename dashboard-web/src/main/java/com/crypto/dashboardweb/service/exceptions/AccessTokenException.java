package com.crypto.dashboardweb.service.exceptions;

import com.crypto.dashboardweb.service.exceptions.enums.AccessTokenExceptionEnum;

public class AccessTokenException extends DashboardException {
    private AccessTokenExceptionEnum accessTokenExceptionEnum;

    public AccessTokenException(AccessTokenExceptionEnum accessTokenExceptionEnum) {
        this.accessTokenExceptionEnum = accessTokenExceptionEnum;
        setExceptionMessage(accessTokenExceptionEnum.name());
    }

    public AccessTokenExceptionEnum getAccessTokenExceptionEnum() {
        return accessTokenExceptionEnum;
    }
}
