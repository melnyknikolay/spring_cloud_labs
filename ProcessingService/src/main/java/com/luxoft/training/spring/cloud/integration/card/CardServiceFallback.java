package com.luxoft.training.spring.cloud.integration.card;

import org.springframework.stereotype.Component;

@Component
public class CardServiceFallback implements CardServiceClient{
    @Override
    public String createCard() {
        return null;
    }
}
