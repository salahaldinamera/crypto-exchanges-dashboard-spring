package com.crypto.dashboardweb.controller.configs;

import com.crypto.dashboardweb.service.exceptions.DashboardException;
import com.crypto.dashboardweb.service.exceptions.enums.AccessTokenExceptionEnum;
import com.crypto.dashboardweb.service.exceptions.enums.AuthenticationExceptionEnum;
import com.crypto.dashboardweb.service.exceptions.enums.UserExceptionEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * GlobalExceptionHandler
 * Contains exception handling for the entire application
 * For customizing httpStatus for each ExceptionEnum add it to httpStatusMap
 *
 * @author Salah-Aldin Amera
 *
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Map<String, HttpStatus> httpStatusMap = new HashMap<>(){{
        put(AccessTokenExceptionEnum.ACCESS_TOKEN_NOT_FOUND.name(), HttpStatus.FORBIDDEN);
        put(AuthenticationExceptionEnum.EMAIL_NOT_FOUND.name(), HttpStatus.FORBIDDEN);
        put(AuthenticationExceptionEnum.WRONG_PASSWORD.name(), HttpStatus.FORBIDDEN);
        put(UserExceptionEnum.USER_EXIST.name(), HttpStatus.CONFLICT);
    }};

    /**
     * Provides handling for exceptions throughout this service
     * @param ex
     * @return
     */
    @ExceptionHandler({Exception.class})
    public final ResponseEntity<String> handleException(Exception ex) {
        HttpHeaders headers = new HttpHeaders();

        if(ex instanceof DashboardException dashboardException) {
            log.error(((DashboardException) ex).getExceptionMessage());
            return new ResponseEntity<>(dashboardException.getExceptionMessage(), headers, getHttpStatusCode(dashboardException));
        }

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return  new ResponseEntity<>(ex.getMessage(), headers, status);
    }

    private HttpStatus getHttpStatusCode(DashboardException dashboardException) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        if (httpStatusMap.containsKey(dashboardException.getExceptionMessage())) httpStatus = httpStatusMap.get(dashboardException.getExceptionMessage());
        return httpStatus;
    }

}
