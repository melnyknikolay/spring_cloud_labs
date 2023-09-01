package com.luxoft.training.spring.cloud.integration.card;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "CardService", fallback = CardServiceFallback.class)
public interface CardServiceClient {

    @PostMapping("/card/create")
    String createCard();
}
