package com.crypto.dashboardweb.dao;

import com.crypto.dashboardweb.model.AccessToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccessTokenRepository extends JpaRepository<AccessToken, Long> {
    /**
     * Find access token by token
     * @param token
     * @return
     */
    Optional<AccessToken> findByToken(String token);
}
