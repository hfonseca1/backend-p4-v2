package com.exemplo.biscoitosorte.controller;

import com.exemplo.biscoitosorte.dto.FortuneCookieDto;
import com.exemplo.biscoitosorte.entity.FortuneCookie;
import com.exemplo.biscoitosorte.service.FortuneCookieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/biscoitos")
public class FortuneCookieController {     

    @Autowired
    private FortuneCookieService cookieService;

    // Criar um novo biscoito, associando a uma frase existente
    @PostMapping
    public ResponseEntity<?> create(@RequestBody FortuneCookieDto cookieDto) {
        try {
            // Criar o biscoito utilizando o DTO
            FortuneCookie createdCookie = cookieService.create(cookieDto);
            return ResponseEntity.status(201).body(createdCookie);  // 201 CREATED
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body("Erro ao criar o biscoito: " + ex.getMessage());
        }
    }

    // Buscar todos os biscoitos
    @GetMapping
    public ResponseEntity<List<FortuneCookie>> findAll() {
        try {
            List<FortuneCookie> cookies = cookieService.findAll();
            return ResponseEntity.ok(cookies);  // 200 OK
        } catch (RuntimeException ex) {
            return ResponseEntity.status(500).body(null);  // 500 INTERNAL SERVER ERROR
        }
    }

    // Buscar biscoito pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            FortuneCookie cookie = cookieService.findById(id);
            if (cookie == null) {
                return ResponseEntity.status(404).body("Biscoito não encontrado com o ID: " + id);  // 404 NOT FOUND
            }
            return ResponseEntity.ok(cookie);  // 200 OK
        } catch (RuntimeException ex) {
            return ResponseEntity.status(404).body("Erro ao buscar o biscoito: " + ex.getMessage());  // 404 NOT FOUND
        }
    }

    // Atualizar um biscoito existente
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody FortuneCookieDto cookieDto) {
        try {
            // Busca o biscoito existente
            FortuneCookie existingCookie = cookieService.findById(id);
            if (existingCookie == null) {
                return ResponseEntity.status(404).body("Biscoito não encontrado com o ID: " + id);  // 404 NOT FOUND
            }
    
            // Atualiza o biscoito com os novos dados, incluindo a frase associada
            FortuneCookie updatedCookie = cookieService.update(id, cookieDto);
            return ResponseEntity.ok(updatedCookie);  // 200 OK
        } catch (RuntimeException ex) {
            return ResponseEntity.status(400).body("Erro ao atualizar o biscoito: " + ex.getMessage());  // 400 BAD REQUEST
        }
    }

    // Deletar um biscoito pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            FortuneCookie cookie = cookieService.findById(id);
            if (cookie == null) {
                return ResponseEntity.status(404).build();  // 404 NOT FOUND
            }

            cookieService.delete(id);
            return ResponseEntity.noContent().build();  // 204 NO CONTENT
        } catch (RuntimeException ex) {
            return ResponseEntity.status(404).build();  // 404 NOT FOUND
        }
    }
}
