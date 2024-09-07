package com.crypto.dashboardweb.service;



import com.crypto.dashboardweb.model.AccessToken;
import com.crypto.dashboardweb.service.exceptions.AccessTokenException;

import java.util.List;

public interface AccessTokenService {

    /**
     * Create accessToken service
     * @param accessToken
     * @return
     */
    AccessToken createAccessToken(AccessToken accessToken) throws AccessTokenException;

    /**
     * Get accessTokens service
     * @return
     */
    List<AccessToken> getAccessTokens();

    /**
     * Get accessToken by id service
     * @param id
     * @return
     */
    AccessToken getById(Long id) throws AccessTokenException;

    /**
     * Get accessToken by token service
     * @param token
     * @return
     */
    AccessToken getByToken(String token) throws AccessTokenException;

    /**
     * Update accessToken by id service
     * @param id
     * @return
     */
    AccessToken update(Long id, AccessToken accessToken) throws AccessTokenException;

    /**
     * Delete accessToken service
     * @param id
     * @return
     */
    void delete(Long id);
}
