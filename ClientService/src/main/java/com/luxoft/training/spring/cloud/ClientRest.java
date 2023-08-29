package com.luxoft.training.spring.cloud;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientRest {
    private final ClientDAO clientDAO;
    private final ClientRepository clientRepository;

    @PostMapping("/create")
    public ClientEntity create(@RequestParam String name) {
        return clientDAO.create(name);
    }

    @PutMapping("/update/{id}")
    public boolean update(@PathVariable int id, @RequestParam String name) {
        return clientDAO.update(id, name);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        clientRepository.delete(clientRepository.getOne(id));
    }

    @GetMapping("/get")
    public List<ClientEntity> getAll() {
        return clientRepository.findAll();
    }

    @GetMapping("/get/{id}")
    public ClientEntity getById(@PathVariable int id) {
        return clientRepository.findById(id)
            .orElse(null);
    }
}
