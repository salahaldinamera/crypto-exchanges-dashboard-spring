package com.crypto.dashboardweb.dao;

import com.crypto.dashboardweb.model.AccessToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccessTokenRepository extends JpaRepository<AccessToken, Long> {
    /**
     * Find access token by token
     * @param token
     * @return
     */
    Optional<AccessToken> findByToken(String token);
}
