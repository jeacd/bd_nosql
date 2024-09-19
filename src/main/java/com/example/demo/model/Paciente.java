package com.example.demo.model;

import org.springframework.data.elasticsearch.annotations.Document;
import lombok.Data;

import lombok.EqualsAndHashCode;
@EqualsAndHashCode(callSuper = false)
@Data
@Document(indexName = "paciente")
public class Paciente extends Pessoa {
    private String numeroProntuario;
    private String historicoMedico;
    private String diagnostico;
}
