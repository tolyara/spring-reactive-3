package com.example.springreactive3.rest;

import com.example.springreactive3.domain.Language;
import com.example.springreactive3.repo.LanguageRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(produces = "application/json")
public class LanguageController {

    private LanguageRepository languageRepository;

    public LanguageController(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    @GetMapping("/all")
    public Flux<Language> getAllLanguages() {
        return languageRepository.findAll();
    }

    @GetMapping("/{name}}")
    public Mono<Language> getByName(@PathVariable("name") String name) {
        return languageRepository.findByName(name);
    }

}
