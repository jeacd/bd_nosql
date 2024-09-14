package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.server.ResponseStatusException;

import com.example.demo.model.Pessoa;
import com.example.demo.repository.PessoaRepository;

@RestController
@CrossOrigin(origins = "*")
public class PessoaController {

    @Autowired
    private PessoaRepository repository;

    @GetMapping("/pessoas")
    public Iterable<Pessoa> listarPessoas() {
        return repository.findAll();
    }


    @PostMapping("/pessoas")
    public Pessoa adicionarPessoa(@RequestBody Pessoa pessoa) {
        return repository.save(pessoa);
    }
}
