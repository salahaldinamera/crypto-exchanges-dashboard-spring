package com.crypto.dashboardweb.service.impl;

import com.crypto.dashboardweb.dao.ExchangeAccountRepository;
import com.crypto.dashboardweb.model.ExchangeAccount;
import com.crypto.dashboardweb.service.ExchangeAccountService;
import com.crypto.dashboardweb.service.exceptions.ExchangeAccountException;
import com.crypto.dashboardweb.service.exceptions.enums.ExchangeAccountExceptionEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExchangeAccountServiceImpl implements ExchangeAccountService {

    @Autowired
    private ExchangeAccountRepository exchangeAccountRepository;

    @Override
    public ExchangeAccount create(ExchangeAccount exchangeAccount) {
        return exchangeAccountRepository.save(exchangeAccount);
    }

    @Override
    public List<ExchangeAccount> getExchangeAccounts() {
        return exchangeAccountRepository.findAll();
    }

    @Override
    public ExchangeAccount getById(Long id) throws ExchangeAccountException {
        Optional<ExchangeAccount> exchangeAccountOptional = exchangeAccountRepository.findById(id);

        if (exchangeAccountOptional.isEmpty())
            throw new ExchangeAccountException(ExchangeAccountExceptionEnum.EXCHANGE_ACCOUNT_NOT_FOUND);

        return exchangeAccountOptional.get();
    }

    @Override
    public ExchangeAccount update(Long id, ExchangeAccount newExchangeAccount) throws ExchangeAccountException {
        ExchangeAccount exchangeAccount = getById(id);
        newExchangeAccount.setId(id);
        return exchangeAccountRepository.save(newExchangeAccount);
    }

    @Override
    public void delete(Long id) {
        exchangeAccountRepository.deleteById(id);
    }
}
