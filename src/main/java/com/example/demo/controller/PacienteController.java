package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Paciente;
import com.example.demo.repository.PacienteRepository;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    // Listar todos os pacientes (GET /pacientes)
    @GetMapping
    public Iterable<Paciente> listarPacientes() {
        return repository.findAll();
    }

    // Adicionar um novo paciente (POST /pacientes)
    @PostMapping
    public Paciente adicionarPaciente(@RequestBody Paciente paciente) {
        return repository.save(paciente);
    }

    // Atualizar um paciente existente (PUT /pacientes/{id})
    @PutMapping("/{id}")
    public ResponseEntity<Paciente> atualizarPaciente(@PathVariable String id, @RequestBody Paciente pacienteAtualizado) {
        Optional<Paciente> pacienteExistente = repository.findById(id);
        if (pacienteExistente.isPresent()) {
            Paciente paciente = pacienteExistente.get();
            paciente.setNome(pacienteAtualizado.getNome());
            paciente.setIdade(pacienteAtualizado.getIdade());
            paciente.setDiagnostico(pacienteAtualizado.getDiagnostico());
            return ResponseEntity.ok(repository.save(paciente));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Deletar um paciente por ID (DELETE /pacientes/{id})
    @DeleteMapping("/{id}")
public ResponseEntity<String> deletarPaciente(@PathVariable String id) {
    if (!repository.existsById(id)) {
        return ResponseEntity.notFound().build();
    }
    repository.deleteById(id);
    return ResponseEntity.ok("Paciente deletado com sucesso");

    }
}
