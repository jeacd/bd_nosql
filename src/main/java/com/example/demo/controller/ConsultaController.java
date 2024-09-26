package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

import com.example.demo.model.Consulta;
import com.example.demo.model.Medico;
import com.example.demo.model.Paciente;
import com.example.demo.repository.ConsultaRepository;
import com.example.demo.repository.MedicoRepository;
import com.example.demo.repository.PacienteRepository;

@RestController
@RequestMapping("/consultas")
@CrossOrigin(origins = "*")
public class ConsultaController {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @PostMapping
    public ResponseEntity<?> adicionarConsulta(@RequestParam String pacienteId, @RequestParam String especialidade) {
        try {
            Optional<Paciente> pacienteExistente = pacienteRepository.findById(pacienteId);
            if (!pacienteExistente.isPresent()) {
                return ResponseEntity.status(404).body("Paciente não encontrado");
            }

            Paciente paciente = pacienteExistente.get();

            List<Medico> medicos = medicoRepository.findByEspecialidade(especialidade);
            if (medicos.isEmpty()) {
                return ResponseEntity.status(404).body("Nenhum médico encontrado com essa especialidade");
            }
    
            Medico medicoSorteado = medicos.get(new Random().nextInt(medicos.size()));
            
            Consulta novaConsulta = new Consulta();
            novaConsulta.setNomePaciente(paciente.getNome());
            novaConsulta.setNomeMedico(medicoSorteado.getNome());
            novaConsulta.setDescricao(medicoSorteado.getEspecialidade());
            novaConsulta.setData(LocalDate.now());
    
            Consulta consultaSalva = consultaRepository.save(novaConsulta);
    
            return ResponseEntity.ok(consultaSalva);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Erro ao salvar a consulta: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Consulta>> listarConsultas() {
        Page<Consulta> pagina = (Page<Consulta>) consultaRepository.findAll();
        List<Consulta> content = pagina.getContent();
        
        return ResponseEntity.ok(content);        
    }

    @PutMapping
    public ResponseEntity<String> atualizarConsulta(@RequestBody Consulta consulta) {
        if (!consultaRepository.existsById(consulta.getId())) {
            return ResponseEntity.notFound().build();
        }
        consultaRepository.save(consulta);
        return ResponseEntity.ok("Atualização de dados realizada");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarConsulta(@PathVariable String id) {
        if (!consultaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        consultaRepository.deleteById(id);
        return ResponseEntity.ok("Consulta deletada com sucesso");
    }
}
