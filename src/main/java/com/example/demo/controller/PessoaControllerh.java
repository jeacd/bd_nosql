package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.Pessoa;
import com.example.demo.repository.PessoaRepository;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class PessoaController {

    @Autowired
    private PessoaRepository repository;

    // Método para listar todas as pessoas (READ - Get All)
    @GetMapping("/pessoas")
    public Iterable<Pessoa> listarPessoas() {
        return repository.findAll();
    }

    // Método para adicionar uma nova pessoa (CREATE - Post)
    @PostMapping("/pessoas")
    public Pessoa adicionarPessoa(@RequestBody Pessoa pessoa) {
        return repository.save(pessoa);
    }

    // Método para atualizar uma pessoa (UPDATE - Put)
    @PutMapping("/pessoas/{id}")
    public ResponseEntity<Pessoa> atualizarPessoa(@PathVariable String id, @RequestBody Pessoa pessoaAtualizada) {
        Optional<Pessoa> pessoaExistente = repository.findById(id);
        if (pessoaExistente.isPresent()) {
            Pessoa pessoa = pessoaExistente.get();
            pessoa.setNome(pessoaAtualizada.getNome());
            pessoa.setIdade(pessoaAtualizada.getIdade());
            pessoa.setCargo(pessoaAtualizada.getCargo());
            return new ResponseEntity<>(repository.save(pessoa), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Método para deletar uma pessoa (DELETE - Delete by ID)
    @DeleteMapping("/pessoas/{id}")
    public ResponseEntity<Void> excluirPessoa(@PathVariable String id) {
        Optional<Pessoa> pessoa = repository.findById(id);
        if (pessoa.isPresent()) {
            repository.delete(pessoa.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
