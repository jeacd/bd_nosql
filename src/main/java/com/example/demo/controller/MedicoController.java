package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Medico;
import com.example.demo.repository.MedicoRepository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @GetMapping
    public ResponseEntity<List<Medico>> listarMedicos() {
        Page<Medico> pagina = (Page<Medico>) repository.findAll();
        List<Medico> content = pagina.getContent();
        
        return ResponseEntity.ok(content);        
    }

    @PostMapping
    public ResponseEntity<Medico> adicionarMedico(@RequestBody Medico medico) {
        repository.save(medico);
        return ResponseEntity.ok(medico);
    }

    @PutMapping
    public ResponseEntity<String> atualizarMedico(@RequestBody Medico medico) {
        if (!repository.existsById(medico.getId())) {
            return ResponseEntity.notFound().build();
        }
        repository.save(medico);
        return ResponseEntity.ok("Atualização de dados realizada");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarMedico(@PathVariable String id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.ok("Médico deletado com sucesso");
    }
}
