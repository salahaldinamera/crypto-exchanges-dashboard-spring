package com.crypto.dashboardweb.controller;

import com.crypto.dashboardweb.model.ExchangeAccount;
import com.crypto.dashboardweb.service.ExchangeAccountService;
import com.crypto.dashboardweb.service.exceptions.ExchangeAccountException;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Exchange Account Controller
 * Contains Exhange Account API-Endpoints
 */
@Tag(name = "Exchange Account", description = "Exchange Account APIs")
@RestController
@RequestMapping("/api/v1/exchanges-accounts/")
public class ExchangeAccountController {

    @Autowired
    private ExchangeAccountService exchangeAccountService;


    /**
     * Create Exchange Account API-Endpoint
     * @param createExchangeAccount
     * @return
     */
    @PostMapping
    public ResponseEntity<ExchangeAccount> create(@RequestBody ExchangeAccount createExchangeAccount) {
        ExchangeAccount exchangeAccount = exchangeAccountService.create(createExchangeAccount);
        return new ResponseEntity<>(exchangeAccount, HttpStatus.CREATED);
    }

    /**
     * Get Exchange Accounts API-Endpoint
     * @return
     */
    @GetMapping
    public ResponseEntity<List<ExchangeAccount>> getExchangeAccounts() {
        List<ExchangeAccount> exchangeAccounts = exchangeAccountService.getExchangeAccounts();
        return new ResponseEntity<>(exchangeAccounts, HttpStatus.OK);
    }

    /**
     * Get Exchange Account API-Endpoint
     * @param id
     * @return
     * @throws ExchangeAccountException
     */
    @GetMapping("{id}")
    public ResponseEntity<ExchangeAccount> getExchangeAccount(@PathVariable Long id) throws ExchangeAccountException {
        ExchangeAccount exchangeAccount = exchangeAccountService.getById(id);
        return new ResponseEntity<>(exchangeAccount, HttpStatus.OK);
    }

    /**
     * Get Exchange Account API-Endpoint
     * @param id
     * @param updateExchangeAccount
     * @return
     * @throws ExchangeAccountException
     */
    @PutMapping("{id}")
    public ResponseEntity<ExchangeAccount> updateExchangeAccount(@PathVariable Long id, @RequestBody ExchangeAccount updateExchangeAccount) throws ExchangeAccountException {
        ExchangeAccount exchangeAccount = exchangeAccountService.update(id, updateExchangeAccount);
        return new ResponseEntity<>(exchangeAccount, HttpStatus.OK);
    }

    /**
     * Delete Exchange Account API-Endpoint
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Long> deleteExchangeAccount(@PathVariable Long id) {
        exchangeAccountService.delete(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
