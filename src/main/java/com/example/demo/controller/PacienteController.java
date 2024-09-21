package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Paciente;
import com.example.demo.repository.PacienteRepository;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @GetMapping
    public Iterable<Paciente> listarPacientes() {
        return repository.findAll();
    }

    @PostMapping
    public Paciente adicionarPaciente(@RequestBody Paciente paciente) {
        return repository.save(paciente);
    }

    @PutMapping("/pacientes")
    public Paciente atualizarPaciente(@RequestBody Paciente paciente) {
        return repository.save(paciente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarPaciente(@PathVariable String id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.ok("Paciente deletado com sucesso");

        }
}
