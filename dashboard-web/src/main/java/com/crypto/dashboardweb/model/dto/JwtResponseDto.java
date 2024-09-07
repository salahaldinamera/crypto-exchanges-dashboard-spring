package com.crypto.dashboardweb.model.dto;

import com.crypto.dashboardweb.model.enums.UserRole;

public class JwtResponseDto {
    private final String jwtToken;
    private String email;
    private UserRole userRole;
    private Long userId;
    private boolean defaultPasswordChanged;

    public JwtResponseDto(String jwtToken, String email, UserRole userRole, Long userId, boolean defaultPasswordChanged) {
        this.jwtToken = jwtToken;
        this.email = email;
        this.userRole = userRole;
        this.userId = userId;
        this.defaultPasswordChanged = defaultPasswordChanged;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public String getEmail() {
        return email;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public Long getUserId() {
        return userId;
    }

    public boolean isDefaultPasswordChanged() {
        return defaultPasswordChanged;
    }
}
