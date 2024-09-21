package com.example.demo.model;

import org.springframework.data.elasticsearch.annotations.Document;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Document(indexName = "paciente")
public class Paciente extends Pessoa {
    private String numeroSUS;
    private String dataConsulta;
}
