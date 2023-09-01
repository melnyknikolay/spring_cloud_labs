package com.luxoft.training.spring.cloud.web;

import com.luxoft.training.spring.cloud.service.CardNumberGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/card")
@RequiredArgsConstructor
public class CardRest {
    private final CardNumberGenerator cardNumberGenerator;

    @PostMapping("/create")
    public String createCard() {
        return cardNumberGenerator.generate();
    }
}
