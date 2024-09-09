package com.crypto.dashboardweb.service;

import com.crypto.dashboardweb.model.ExchangeAccount;
import com.crypto.dashboardweb.service.exceptions.ExchangeAccountException;

import java.util.List;

/**
 * ExchangeAccountService
 *
 * @author Salah-Aldin Amera
 *
 */
public interface ExchangeAccountService {

    /**
     * Create exchangeAccount service
     * @param exchangeAccount
     * @return
     */
    ExchangeAccount create(ExchangeAccount exchangeAccount);

    /**
     * Get exchangeAccounts service
     * @return
     */
    List<ExchangeAccount> getExchangeAccounts();

    /**
     * Get exchangeAccount by id service
     * @param id
     * @return
     */
    ExchangeAccount getById(Long id) throws ExchangeAccountException;

    /**
     * Update exchangeAccount  by id service
     * @param exchangeAccount
     * @return
     */
    ExchangeAccount update(Long id, ExchangeAccount exchangeAccount) throws ExchangeAccountException;

    /**
     * Delete exchangeAccount by id service
     * @param id
     */
    void delete(Long id);

}
