package com.example.demo.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Pessoa;

@Repository
public interface PessoaRepository extends ElasticsearchRepository<Pessoa, String> {
}
