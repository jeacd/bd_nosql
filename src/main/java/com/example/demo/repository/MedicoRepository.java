package com.example.demo.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Medico;

@Repository
public interface MedicoRepository extends ElasticsearchRepository<Medico, String> {

    List<Medico> findByEspecialidade(String especialidade);
}
