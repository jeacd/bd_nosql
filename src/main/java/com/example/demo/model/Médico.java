package com.example.demo.model;

import org.springframework.data.elasticsearch.annotations.Document;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
@Document(indexName = "medico")
public class Medico extends Pessoa {
    private String especialidade;
    private String crm;
}
