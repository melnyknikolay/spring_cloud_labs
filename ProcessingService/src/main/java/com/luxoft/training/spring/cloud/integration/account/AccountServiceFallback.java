package com.luxoft.training.spring.cloud.integration.account;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class AccountServiceFallback implements AccountServiceClient{
    @Override
    public boolean checkout(int id, BigDecimal sum) {
        return false;
    }
}
