package com.crypto.dashboardweb.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Profile {
    private String username;
    private String role;
    private String token;

    public Profile() {
    }

    public Profile(String username, String role, String token) {
        this.username = username;
        this.role = role;
        this.token = token;
    }
}
