package com.example.springreactive3.rest;

import com.example.springreactive3.domain.Language;
import com.example.springreactive3.repo.LanguageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    @PostMapping(value = "/languages", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
//    public Mono<Language> create(@RequestBody Mono<Language> newLanguage) {
    public Mono<Language> create(@RequestBody Language newLanguage) {
//        Flux<Language> savedLanguages = languageRepository.saveAll(newLanguage);
//        Mono<Language> savedLanguage = savedLanguages.next();
//        savedLanguage.subscribe(result -> logger.info("Entity has been saved: {}", result));

        //TODO - fix duplicates in DB
        Mono<Language> savedLanguage = languageRepository.save(newLanguage);
        savedLanguage.subscribe(result -> logger.info("Entity has been saved: {}", result));

//        Language forTestLanguage = new Language("Testik11", "rrr", "ddd");
//        languageRepository.save(forTestLanguage).subscribe(result -> logger.info("Entity has been saved: {}", result));;

        return savedLanguage;
    }

    @DeleteMapping("/{name}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable String name) {
        return languageRepository.deleteByName(name);
    }

    @PatchMapping("/languages/name/{name}")
    public Mono<ResponseEntity<Language>> update(@PathVariable String name, @RequestBody Language language) {
        Mono<ResponseEntity<Language>> result = languageRepository.findByName(name)
                .flatMap(existingLanguage -> {
                    if (language.getCreator() != null)
                        existingLanguage.setCreator(language.getCreator());
                    if (language.getFeature() != null)
                        existingLanguage.setFeature(language.getFeature());
                    return languageRepository.save(existingLanguage);
                }).map(updatedLanguage -> {
                    return new ResponseEntity<>(updatedLanguage, HttpStatus.OK);
                }).defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        return result;
    }

}
