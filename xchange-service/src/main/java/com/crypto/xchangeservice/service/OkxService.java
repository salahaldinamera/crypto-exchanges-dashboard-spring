package com.crypto.xchangeservice.service;

import org.knowm.xchange.dto.account.AccountInfo;

import java.math.BigDecimal;

public interface OkxService {

    BigDecimal getTotalBalance();
}
