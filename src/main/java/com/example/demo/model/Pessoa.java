package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import lombok.Data;

@Document(indexName = "pessoa")
@Data
public class Pessoa {

    @Id
    private String id;
    private String nome;
    private int idade;
    private String cargo;

}
