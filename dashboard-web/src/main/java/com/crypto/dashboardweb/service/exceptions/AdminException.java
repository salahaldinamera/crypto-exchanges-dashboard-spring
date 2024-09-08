package com.crypto.dashboardweb.service.exceptions;

public class AdminException extends Exception {
    public String exceptionMessage;

    public AdminException() {}

    public AdminException(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }
}
