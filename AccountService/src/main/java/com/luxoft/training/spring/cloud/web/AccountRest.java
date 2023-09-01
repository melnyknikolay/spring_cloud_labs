package com.luxoft.training.spring.cloud.web;

import java.math.BigDecimal;
import java.util.List;

import com.luxoft.training.spring.cloud.repository.AccountDAO;
import com.luxoft.training.spring.cloud.repository.AccountEntity;
import com.luxoft.training.spring.cloud.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountRest {
    private final AccountDAO accountDAO;
    private final AccountRepository accountRepository;

    @PostMapping("/create")
    public void create(@RequestParam Integer clientId) {
        accountDAO.create(clientId);
    }

    @PutMapping("/fund/{id}")
    public boolean fund(@PathVariable int id, @RequestParam BigDecimal sum) {
        return accountDAO.addBalance(id, sum.abs());
    }

    @PutMapping("/checkout/{id}")
    public boolean checkout(@PathVariable int id, @RequestParam BigDecimal sum) {
        return accountDAO.addBalance(id, sum.abs().negate());
    }

    @GetMapping("/get/{clientId}")
    public List<AccountEntity> getById(@PathVariable int clientId) {
        return accountRepository.findByClientId(clientId);
    }
}
