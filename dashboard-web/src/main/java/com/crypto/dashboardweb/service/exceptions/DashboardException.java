package com.crypto.dashboardweb.service.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DashboardException extends Exception {
    public String exceptionMessage;

    public DashboardException() {}

    public DashboardException(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }
}
