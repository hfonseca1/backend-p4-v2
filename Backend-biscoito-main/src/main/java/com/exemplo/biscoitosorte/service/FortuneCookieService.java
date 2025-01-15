package com.exemplo.biscoitosorte.service;

import com.exemplo.biscoitosorte.dto.FortuneCookieDto;
import com.exemplo.biscoitosorte.entity.FortuneCookie;
import com.exemplo.biscoitosorte.entity.FortunePhrase;
import com.exemplo.biscoitosorte.repository.FortuneCookieRepository;
import com.exemplo.biscoitosorte.repository.FortunePhraseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class FortuneCookieService {

    @Autowired
    private FortuneCookieRepository cookieRepository;

    @Autowired
    private FortunePhraseRepository phraseRepository;

    // Criar um novo biscoito associado a uma frase
    public FortuneCookie create(FortuneCookieDto cookieDto) {
        // Busca a frase associada ao ID fornecido
        FortunePhrase phrase = phraseRepository.findById(cookieDto.getPhraseId()).orElse(null);
        if (phrase == null) {
            // Se a frase não existir, retornamos um erro 400 Bad Request
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Frase com ID " + cookieDto.getPhraseId() + " não encontrada");
        }

        // Cria um novo biscoito da sorte
        FortuneCookie cookie = new FortuneCookie();
        cookie.setNome(cookieDto.getNome());
        cookie.setFrase(phrase);

        // Salva e retorna o biscoito criado
        FortuneCookie savedCookie = cookieRepository.save(cookie);
        
        // Retorna o biscoito com a frase associada
        return savedCookie;
    }

    // Retorna todos os biscoitos
    public List<FortuneCookie> findAll() {
        return cookieRepository.findAll();
    }

    // Retorna um biscoito pelo ID
    public FortuneCookie findById(Long id) {
        // Caso o biscoito não seja encontrado, lança uma exceção
        return cookieRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Biscoito não encontrado"));
    }

    // Atualiza um biscoito existente
    public FortuneCookie update(Long id, FortuneCookieDto cookieDto) {
        // Busca o biscoito existente
        FortuneCookie existingCookie = findById(id);
        if (existingCookie == null) {
            throw new RuntimeException("Biscoito com ID " + id + " não encontrado");
        }
    
        // Busca a frase pelo ID (do DTO)
        FortunePhrase phrase = phraseRepository.findById(cookieDto.getPhraseId()).orElse(null);
        if (phrase == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Frase com ID " + cookieDto.getPhraseId() + " não encontrada");
        }
    
        // Atualiza o biscoito com os dados do DTO e a nova frase
        existingCookie.setNome(cookieDto.getNome());
        existingCookie.setFrase(phrase);
    
        // Salva e retorna o biscoito atualizado
        return cookieRepository.save(existingCookie);
    }

    // Deleta um biscoito pelo ID
    public void delete(Long id) {
        cookieRepository.deleteById(id);
    }
}