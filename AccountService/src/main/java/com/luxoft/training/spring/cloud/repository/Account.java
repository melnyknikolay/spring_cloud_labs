package com.luxoft.training.spring.cloud.repository;

import java.math.BigDecimal;

public interface Account {
    Integer getId();
    Integer getClientId();
    BigDecimal getBalance();
}
