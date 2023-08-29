package com.luxoft.training.spring.cloud;

import java.math.BigDecimal;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("AccountService")
public interface AccountServiceClient {
    @PutMapping("/account/checkout/{id}")
    boolean checkout(@PathVariable int id, @RequestParam BigDecimal sum);
}
