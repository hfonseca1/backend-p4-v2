package com.exemplo.biscoitosorte.controller;

import com.exemplo.biscoitosorte.dto.FortunePhraseDto;
import com.exemplo.biscoitosorte.entity.FortunePhrase;
import com.exemplo.biscoitosorte.service.FortunePhraseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/frases")
public class FortunePhraseController {

    @Autowired
    private FortunePhraseService phraseService;

    // Criar uma nova frase
    @PostMapping
    public ResponseEntity<?> create(@RequestBody FortunePhraseDto phraseDto) {
        try {
            // Converte o DTO para a entidade
            FortunePhrase phrase = new FortunePhrase();
            phrase.setConteudo(phraseDto.getConteudo());
            phrase.setAutor(phraseDto.getAutor());

            // Criar a frase utilizando o serviço
            FortunePhrase createdPhrase = phraseService.create(phrase);
            return ResponseEntity.status(201).body(createdPhrase);  // Retorna a frase com código 201 CREATED
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body("Erro ao criar a frase: " + ex.getMessage());
        }
    }

    // Buscar todas as frases
    @GetMapping
    public ResponseEntity<List<FortunePhrase>> findAll() {
        try {
            List<FortunePhrase> phrases = phraseService.findAll();
            return ResponseEntity.ok(phrases);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(500).body(null);
        }
    }

    // Buscar frase pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            FortunePhrase phrase = phraseService.findById(id);
            if (phrase == null) {
                return ResponseEntity.status(404).body("Frase não encontrada com o ID: " + id);  // 404 NOT FOUND
            }
            return ResponseEntity.ok(phrase);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(404).body("Erro ao buscar a frase: " + ex.getMessage());  // 404 NOT FOUND
        }
    }

    // Atualizar uma frase existente
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody FortunePhraseDto phraseDto) {
        try {
            FortunePhrase existingPhrase = phraseService.findById(id);
            if (existingPhrase == null) {
                return ResponseEntity.status(404).body("Frase não encontrada com o ID: " + id);
            }

            // Converte o DTO para a entidade
            existingPhrase.setConteudo(phraseDto.getConteudo());
            existingPhrase.setAutor(phraseDto.getAutor());

            FortunePhrase updatedPhrase = phraseService.update(id, existingPhrase);
            return ResponseEntity.ok(updatedPhrase);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(400).body("Erro ao atualizar a frase: " + ex.getMessage());
        }
    }

    // Deletar uma frase
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            FortunePhrase phrase = phraseService.findById(id);
            if (phrase == null) {
                return ResponseEntity.status(404).build();
            }

            phraseService.delete(id);
            return ResponseEntity.noContent().build();  // 204 NO CONTENT
        } catch (RuntimeException ex) {
            return ResponseEntity.status(404).build();
        }
    }
}
