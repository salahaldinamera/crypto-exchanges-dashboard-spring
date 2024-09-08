package com.crypto.dashboardweb.controller.utils;

import com.crypto.dashboardweb.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequestInterceptor;
import org.springframework.web.servlet.handler.WebRequestHandlerInterceptorAdapter;
import com.crypto.dashboardweb.controller.configs.WebConfig;

/**
 * Requests Interceptor
 * Validates bearer for each request
 * @see WebConfig
 */
@Component
public class RequestsInterceptor extends WebRequestHandlerInterceptorAdapter {

    @Autowired
    private AuthenticationService authenticationService;

    public RequestsInterceptor(WebRequestInterceptor requestInterceptor) {
        super(requestInterceptor);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        if (token != null && token.contains("Bearer ")) {
            token = token.split("Bearer ")[1];
            boolean isExpired = authenticationService.isTokenExpired(token);

            if (isExpired) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return false;
            }
        }
        return super.preHandle(request, response, handler);
    }
}
