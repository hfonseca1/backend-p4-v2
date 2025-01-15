package com.exemplo.biscoitosorte.service;

import com.exemplo.biscoitosorte.entity.FortunePhrase;
import com.exemplo.biscoitosorte.repository.FortunePhraseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class FortunePhraseService {

    @Autowired
    private FortunePhraseRepository repository;

    // Criar uma nova frase
    public FortunePhrase create(FortunePhrase phrase) {
        // Validação para garantir que o conteúdo não seja nulo ou vazio
        if (phrase.getConteudo() == null || phrase.getConteudo().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O conteúdo da frase não pode ser vazio.");
        }
        
        // Verificar se a frase já existe
        if (repository.findByConteudo(phrase.getConteudo()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Frase já cadastrada!");
        }
        
        return repository.save(phrase);
    }

    // Buscar todas as frases
    public List<FortunePhrase> findAll() {
        return repository.findAll();
    }

    // Buscar uma frase pelo ID
    public FortunePhrase findById(Long id) {
        // Caso a frase não exista, lança uma exceção
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Frase não encontrada!"));
    }

    // Atualizar uma frase existente
    public FortunePhrase update(Long id, FortunePhrase phrase) {
        // Verifica se a frase existe antes de atualizar
        FortunePhrase existing = findById(id);
        
        // Atualiza o conteúdo e o autor da frase
        existing.setConteudo(phrase.getConteudo());
        existing.setAutor(phrase.getAutor());
        
        return repository.save(existing);
    }

    // Deletar uma frase pelo ID
    public void delete(Long id) {
        // Verifica se a frase existe antes de tentar excluir
        FortunePhrase existing = findById(id);
        
        // Se a frase existir, deleta
        repository.delete(existing);
    }
}
