package com.example.springreactive3.config;

import com.example.springreactive3.domain.Language;
import com.example.springreactive3.repo.LanguageRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommandLineConfig {

    @Bean
    public CommandLineRunner preload(LanguageRepository languageRepository) {
        return args -> {
            languageRepository.save(new Language("Java", "Gosling", "imperative"));
            languageRepository.save(new Language("Scala", "Odersly", "functional"));
            languageRepository.save(new Language("Clojure", "Hitch", "lisp"));
        };
    }

}
