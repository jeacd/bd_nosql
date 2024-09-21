package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Medico;
import com.example.demo.repository.MedicoRepository;

@RestController
@CrossOrigin(origins = "*")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @GetMapping("/medicos")
    public Iterable<Medico> listarMedicos() {
        return repository.findAll();
    }

    @PostMapping("/medicos")
    public Medico adicionarMedico(@RequestBody Medico medico) {
        return repository.save(medico);
    }

    @PutMapping("/medicos")
    public Medico atualizarMedico(@RequestBody Medico medico) {
        return repository.save(medico);
    }

    @DeleteMapping("/medicos/{id}")
    public ResponseEntity<String> deletarMedico(@PathVariable String id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.ok("Médico deletado com sucesso");
    }
}
