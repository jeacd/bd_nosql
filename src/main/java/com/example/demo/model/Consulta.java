package com.example.demo.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.Data;

@Document(indexName = "consulta")
@Data
public class Consulta {

    @Id
    private String id;
    
    private String nomeMedico;
    private String nomePaciente;

    private String descricao;  // Especialidade do médico

    @Field(type = FieldType.Date)
    private LocalDate data;
}
