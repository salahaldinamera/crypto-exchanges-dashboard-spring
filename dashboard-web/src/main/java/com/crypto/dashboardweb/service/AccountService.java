package com.crypto.dashboardweb.service;

import com.crypto.dashboardweb.service.exceptions.ExchangeAccountException;

import java.math.BigDecimal;

public interface AccountService {

    public BigDecimal getTotalBalance() throws ExchangeAccountException;
}
