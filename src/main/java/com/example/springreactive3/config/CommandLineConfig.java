package com.example.springreactive3.config;

import com.example.springreactive3.domain.Language;
import com.example.springreactive3.repo.LanguageRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.mapping.Document;

@Configuration
public class CommandLineConfig {

    @Bean
    public CommandLineRunner preload(LanguageRepository languageRepository) {
        System.out.println("Generating test values for collection : " + Language.class.getAnnotation(Document.class).collection());
        return args -> {
            languageRepository.save(new Language("Java", "Gosling", "imperative"));
            languageRepository.save(new Language("Scala", "Odersly", "functional"));
            languageRepository.save(new Language("Clojure", "Hitch", "lisp"));
        };
    }

}
