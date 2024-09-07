package com.crypto.dashboardweb.model.dto;

public class JwtRequestDto {
    private String username;
    private String password;
    private boolean staySignedIn;

    public JwtRequestDto() {
    }

    public JwtRequestDto(boolean staySignedIn, String password, String username) {
        this.staySignedIn = staySignedIn;
        this.password = password;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isStaySignedIn() {
        return staySignedIn;
    }

    public void setStaySignedIn(boolean staySignedIn) {
        this.staySignedIn = staySignedIn;
    }
}
