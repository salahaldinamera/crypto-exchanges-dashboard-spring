package com.crypto.dashboardweb.controller;

import com.crypto.dashboardweb.service.AccountService;
import com.crypto.dashboardweb.service.exceptions.ExchangeAccountException;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * Account Controller
 * Exchanges accounts related API-Endpoint
 */
@Tag(name = "Authentication", description = "Authentication management")
@RestController
@RequestMapping("/api/v1/account/")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/balance")
    public BigDecimal balance() throws ExchangeAccountException {
        return accountService.getTotalBalance();
    }
}
