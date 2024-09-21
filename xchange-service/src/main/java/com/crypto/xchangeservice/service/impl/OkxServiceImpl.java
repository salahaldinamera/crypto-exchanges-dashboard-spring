package com.crypto.xchangeservice.service.impl;

import com.crypto.xchangeservice.service.OkxService;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.account.AccountInfo;
import org.knowm.xchange.dto.account.Balance;
import org.knowm.xchange.dto.account.Wallet;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.instrument.Instrument;
import org.knowm.xchange.okex.OkexExchange;
import org.knowm.xchange.service.account.AccountService;
import org.knowm.xchange.service.marketdata.MarketDataService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collection;

@Service
public class OkxServiceImpl implements OkxService {

    private final Exchange okxExchange;

    public OkxServiceImpl(String apiKey, String secretKey, String passphrase) {
        // Create exchange instance
        this.okxExchange = ExchangeFactory.INSTANCE.createExchange(OkexExchange.class.getName());

        // Apply credentials
        okxExchange.getExchangeSpecification().setApiKey(apiKey);
        okxExchange.getExchangeSpecification().setSecretKey(secretKey);
        okxExchange.getExchangeSpecification().setExchangeSpecificParametersItem("passphrase", passphrase);

        // Initialize exchange
        okxExchange.applySpecification(okxExchange.getExchangeSpecification());
    }

    @Override
    public BigDecimal getTotalBalance() {
        AccountService accountService = okxExchange.getAccountService();
        try {
            AccountInfo accountInfo = accountService.getAccountInfo();
            return calculateTotalBalance(accountInfo);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private BigDecimal calculateTotalBalance(AccountInfo accountInfo) {
        BigDecimal totalBalance = BigDecimal.ZERO;

        Collection<Wallet> wallets = accountInfo.getWallets().values();

        for (Wallet wallet : wallets) {
            if (wallet.getName().equals("futures")) continue;
            for (Balance value : wallet.getBalances().values()) {
                BigDecimal amount = value.getTotal()==null ? new BigDecimal(0) : value.getTotal();
                String currency = value.getCurrency().getCurrencyCode();
                CurrencyPair currencyPair = new CurrencyPair(currency, "USDT");
                BigDecimal price = currency.equals("USDT") ? new BigDecimal(1) : this.getCoinPrice(currencyPair);
                BigDecimal balance = amount.multiply(price);
                totalBalance = totalBalance.add(balance);
            }
        }

        return totalBalance;
    }

    private BigDecimal getCoinPrice(CurrencyPair currencyPair) {
        MarketDataService marketDataService = okxExchange.getMarketDataService();
        try {
            Ticker ticker = marketDataService.getTicker((Instrument) currencyPair);
            if (ticker != null) {
                return ticker.getLast();
            } else {
                throw new RuntimeException("Ticker not found for currency pair: " + currencyPair);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error fetching price for currency pair: " + currencyPair, e);
        }
    }
}
