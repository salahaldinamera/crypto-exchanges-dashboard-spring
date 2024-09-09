package com.crypto.dashboardweb.service.exceptions;

import com.crypto.dashboardweb.service.exceptions.enums.ExchangeAccountExceptionEnum;
import lombok.Getter;

@Getter
public class ExchangeAccountException extends DashboardException {
    private ExchangeAccountExceptionEnum exchangeAccountExceptionEnum;

    public ExchangeAccountException(ExchangeAccountExceptionEnum exchangeAccountExceptionEnum) {
        this.exchangeAccountExceptionEnum = exchangeAccountExceptionEnum;
    }
}
