package com.example.demo.model;

import org.springframework.data.elasticsearch.annotations.Document;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Document(indexName = "medico")
@Data
@EqualsAndHashCode(callSuper = true)
public class Medico extends Pessoa {

    private String especialidade;
    private String crm;
}
