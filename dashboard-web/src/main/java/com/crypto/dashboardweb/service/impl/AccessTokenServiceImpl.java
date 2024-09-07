package com.crypto.dashboardweb.service.impl;

import com.crypto.dashboardweb.dao.AccessTokenRepository;
import com.crypto.dashboardweb.model.AccessToken;
import com.crypto.dashboardweb.service.AccessTokenService;
import com.crypto.dashboardweb.service.exceptions.AccessTokenException;
import com.crypto.dashboardweb.service.exceptions.enums.AccessTokenExceptionEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class AccessTokenServiceImpl implements AccessTokenService {

    @Autowired
    private AccessTokenRepository accessTokenRepository;


    @Override
    public AccessToken createAccessToken(AccessToken accessToken) throws AccessTokenException {
        if(accessToken == null)
            throw new AccessTokenException(AccessTokenExceptionEnum.ACCESS_TOKEN_IS_NULL);

        return accessTokenRepository.save(accessToken);
    }

    @Override
    public List<AccessToken> getAccessTokens() {
        return accessTokenRepository.findAll();
    }

    @Override
    public AccessToken getById(Long id) throws AccessTokenException {
        Optional<AccessToken> accessTokenOptional = accessTokenRepository.findById(id);

        if(accessTokenOptional.isEmpty())
            throw new AccessTokenException(AccessTokenExceptionEnum.ACCESS_TOKEN_NOT_FOUND);

        return accessTokenOptional.get();
    }

    @Override
    public AccessToken getByToken(String token) throws AccessTokenException {
        Optional<AccessToken> accessToken = accessTokenRepository.findByToken(token);

        if(accessToken.isEmpty())
            throw new AccessTokenException(AccessTokenExceptionEnum.ACCESS_TOKEN_NOT_FOUND);

        return accessToken.get();
    }

    @Override
    public AccessToken update(Long id, AccessToken newAccessToken) throws AccessTokenException {
        if(newAccessToken == null)
            throw new AccessTokenException(AccessTokenExceptionEnum.ACCESS_TOKEN_IS_NULL);

        AccessToken accessToken = getById(id);

        accessToken.update(newAccessToken);

        return accessTokenRepository.save(accessToken);
    }

    @Override
    public void delete(Long id) {
        accessTokenRepository.deleteById(id);
    }
}
