package com.crypto.dashboardweb.dao;

import com.crypto.dashboardweb.model.ExchangeAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeAccountRepository extends JpaRepository<ExchangeAccount, Long> {
}
