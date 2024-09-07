package com.crypto.dashboardweb.model.dto;

import com.crypto.dashboardweb.model.enums.UserRole;

public class JwtResponseDto {
    private final String jwtToken;
    private String username;
    private UserRole userRole;
    private Long userId;
    private boolean defaultPasswordChanged;

    public JwtResponseDto(String jwtToken, String username, UserRole userRole, Long userId, boolean defaultPasswordChanged) {
        this.jwtToken = jwtToken;
        this.username = username;
        this.userRole = userRole;
        this.userId = userId;
        this.defaultPasswordChanged = defaultPasswordChanged;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public String getUsername() {
        return username;
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
