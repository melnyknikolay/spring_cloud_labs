package com.luxoft.training.spring.cloud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("CardService")
public interface CardServiceClient {

    @PostMapping("/card/create")
    String create();
}
