package com.example.demo.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Consulta;


@Repository
public interface ConsultaRepository extends ElasticsearchRepository<Consulta, String> {
}