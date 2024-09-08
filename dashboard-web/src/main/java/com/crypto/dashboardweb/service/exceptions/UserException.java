package com.crypto.dashboardweb.service.exceptions;

import com.crypto.dashboardweb.service.exceptions.enums.UserExceptionEnum;

public class UserException extends DashboardException {
    private UserExceptionEnum userExceptionEnum;

    public UserException(UserExceptionEnum userExceptionEnum) {
        this.userExceptionEnum = userExceptionEnum;
        setExceptionMessage(userExceptionEnum.name());
    }

    public UserExceptionEnum getUserExceptionEnum() {
        return userExceptionEnum;
    }
}
