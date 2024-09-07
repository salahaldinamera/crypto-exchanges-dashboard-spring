package com.crypto.dashboardweb.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class AccessToken {
    @Id
    @GeneratedValue
    private Long id;

    private String token;
    private boolean expired;
    private Date creationDate;
    private Date expirationDate;

    @OneToOne
    private User user;

    public void update(AccessToken accessToken) {
        this.setExpired(accessToken.isExpired());
        if(accessToken.getToken() != null) this.setToken(accessToken.getToken());
        if(accessToken.getCreationDate() != null) this.setCreationDate(accessToken.getCreationDate());
        if(accessToken.getExpirationDate() != null) this.setExpirationDate(accessToken.getExpirationDate());
        if(accessToken.getUser() != null) this.setUser(accessToken.getUser());
    }
}