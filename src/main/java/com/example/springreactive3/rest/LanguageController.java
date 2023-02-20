package com.example.springreactive3.rest;

import com.example.springreactive3.domain.Language;
import com.example.springreactive3.repo.LanguageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(produces = "application/json")
public class LanguageController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private LanguageRepository languageRepository;

    public LanguageController(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    @GetMapping("/all")
    public Flux<Language> getAllLanguages() {
        return languageRepository.findAll();
    }

    @GetMapping("/name/{name}")
    public Mono<Language> getByName(@PathVariable("name") String name) {
        return languageRepository.findByName(name);
    }

    @PostMapping(value = "/languages", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Language> create(@RequestBody Mono<Language> newLanguage) {
//        Flux<Language> savedLanguages = languageRepository.saveAll(newLanguage);
//        savedLanguages.next();

        Language forTestLanguage = new Language("Testik11", "rrr", "ddd");
        languageRepository.save(forTestLanguage).subscribe(result -> logger.info("Entity has been saved: {}", result));;

        return null;
    }

    @DeleteMapping("/{name}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable String name) {
        return languageRepository.deleteByName(name);
    }

}
