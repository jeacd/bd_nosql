package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            // novaConsulta.setPacienteId(paciente.getId());
            // novaConsulta.setMedicoId(medicoSorteado.getId());
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
    public Iterable<Consulta> listarConsultas() {
        return consultaRepository.findAll();
    }    

    @PutMapping
    public Consulta atualizarMedico(@RequestBody Consulta consulta) {
        return consultaRepository.save(consulta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarPaciente(@PathVariable String id) {
        if (!consultaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        consultaRepository.deleteById(id);
        return ResponseEntity.ok("Consulta deletada com sucesso");
    }
}
