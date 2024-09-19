package com.example.demo.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.Paciente;

@Repository
public interface PacienteRepository extends ElasticsearchRepository<Paciente, String> {
}
