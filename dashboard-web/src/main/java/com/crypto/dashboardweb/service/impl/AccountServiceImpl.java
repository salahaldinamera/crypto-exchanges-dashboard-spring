package com.crypto.dashboardweb.service.impl;

import com.crypto.dashboardweb.model.ExchangeAccount;
import com.crypto.dashboardweb.model.ExchangeAccountApi;
import com.crypto.dashboardweb.service.AccountService;
import com.crypto.dashboardweb.service.ExchangeAccountService;
import com.crypto.dashboardweb.service.exceptions.ExchangeAccountException;
import com.crypto.xchangeservice.service.OkxService;
import com.crypto.xchangeservice.service.impl.OkxServiceImpl;
import org.knowm.xchange.currency.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountServiceImpl implements AccountService {


    @Autowired
    private ExchangeAccountService exchangeAccountService;

    @Override
    public BigDecimal getTotalBalance() throws ExchangeAccountException {
        ExchangeAccount exchangeAccount = exchangeAccountService.getById(2l);
        ExchangeAccountApi exchangeAccountApi = exchangeAccount.getRealApi();
        OkxService okxService = new OkxServiceImpl(
                exchangeAccountApi.getApiKey(),
                exchangeAccountApi.getApiSecret(),
                exchangeAccountApi.getName()
        );

        return okxService.getTotalBalance();
    }
}
