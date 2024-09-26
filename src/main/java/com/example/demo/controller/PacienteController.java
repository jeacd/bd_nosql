package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<List<Paciente>> listarPacientes() {
        Page<Paciente> pagina = (Page<Paciente>) repository.findAll();
        List<Paciente> content = pagina.getContent();
        
        return ResponseEntity.ok(content);        
    }

    @PostMapping
    public ResponseEntity<Paciente> adicionarPaciente(@RequestBody Paciente paciente) {
        repository.save(paciente);
        return ResponseEntity.ok(paciente);
    }

    @PutMapping
    public ResponseEntity<String> atualizarPaciente(@RequestBody Paciente paciente) {
        if (!repository.existsById(paciente.getId())) {
            return ResponseEntity.notFound().build();
        }
        repository.save(paciente);
        return ResponseEntity.ok("Atualização de dados realizada");
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
