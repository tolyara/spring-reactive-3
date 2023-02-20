package com.example.springreactive3.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "language")
public class Language {

    @Id
    @JsonIgnore
    public String id;

    private String name;
    private String creator;
    private String feature;

    public Language(String name, String creator, String feature) {
        this.name = name;
        this.creator = creator;
        this.feature = feature;
    }
}
