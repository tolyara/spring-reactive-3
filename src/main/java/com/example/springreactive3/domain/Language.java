package com.example.springreactive3.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collation = "language")
public class Language {

    @Id
    public String id;

    private String name;
    private String creator;
    private String feature;

}
