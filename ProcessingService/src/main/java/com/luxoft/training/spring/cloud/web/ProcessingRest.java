package com.luxoft.training.spring.cloud.web;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.luxoft.training.spring.cloud.integration.account.AccountServiceClient;
import com.luxoft.training.spring.cloud.integration.card.CardServiceClient;
import com.luxoft.training.spring.cloud.repository.ProcessingEntity;
import com.luxoft.training.spring.cloud.repository.ProcessingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static java.util.stream.Collectors.toMap;


@RestController
@RequestMapping("/processing")
@RequiredArgsConstructor
public class ProcessingRest {
    private final ProcessingRepository repository;
    private final AccountServiceClient accountServiceClient;
    private final CardServiceClient cardServiceClient;


    @PostMapping("/issue/{accountId}")
    public ResponseEntity issue(@PathVariable Integer accountId) {
        var cardNumber = cardServiceClient.createCard();
        if (cardNumber == null){
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("CARD_SERVICE_NOT_AVAILABLE");
        }
        var processingEntity = new ProcessingEntity();
        processingEntity.setCard(cardNumber);
        processingEntity.setAccountId(accountId);
        var saved = repository.save(processingEntity);
        return ResponseEntity.ok(saved);
    }

    @PostMapping("/checkout/{card}")
    public boolean checkout(@PathVariable String card, @RequestParam BigDecimal sum) {
        ProcessingEntity entity = repository.findByCard(card);
        if (Objects.isNull(entity)) {
            return false;
        }
        return accountServiceClient.checkout(entity.getAccountId(), sum);
    }

    @GetMapping("/get")
    public Map<Integer, String> get(@RequestParam List<Integer> accountIds) {
        return repository.findByAccountIdIn(accountIds).stream()
            .collect(toMap(ProcessingEntity::getAccountId, ProcessingEntity::getCard));
    }
}
