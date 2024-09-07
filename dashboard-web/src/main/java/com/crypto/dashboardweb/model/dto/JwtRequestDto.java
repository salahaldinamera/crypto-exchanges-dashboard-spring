package com.crypto.dashboardweb.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
}
